package net.damrongmc.entity.custom;

import net.damrongmc.entity.GlorpVariant;
import net.damrongmc.entity.ModEntities;
import net.damrongmc.glorpcat.item.ModItems;
import net.minecraft.Util;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class GlorpEntity extends TamableAnimal {

    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState walkAnimationState = new AnimationState();
    public final AnimationState sitAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;
    public boolean isSitting = false;

    private static final EntityDataAccessor<Integer> VARIANT =
            SynchedEntityData.defineId(GlorpEntity.class, EntityDataSerializers.INT);

    public GlorpEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void setOrderedToSit(boolean sit) {
        super.setOrderedToSit(sit);
        this.setInSittingPose(sit);
    }

    @Nullable
    @Override
    public LivingEntity getOwner() {
        try {
            UUID uuid = this.getOwnerUUID();
            return uuid == null ? null : this.level().getPlayerByUUID(uuid);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void toggleSit() {
        this.isSitting = !this.isSitting; // Toggle the sitting state

        if (this.isSitting) {
            // Stop the idle animation and start the sitting animation
            this.idleAnimationState.stop();
            this.sitAnimationState.start(this.tickCount); // Start the sitting animation
        } else {
            // Stop the sitting animation and start the idle animation
            this.sitAnimationState.stop();
            this.idleAnimationState.start(this.tickCount); // Start the idle animation
        }

        // Optional: Notify the client of the state change (for rendering)
        this.setOrderedToSit(this.isSitting); // Update the sitting status for AI goals
    }

    @Override
    public boolean isOwnedBy(@NotNull LivingEntity entity) {
        return entity == this.getOwner();
    }

    @Override
    protected void dropCustomDeathLoot(@NotNull ServerLevel level, @NotNull DamageSource source, boolean recentlyHit) {
        super.dropCustomDeathLoot(level, source, recentlyHit);

        // Example: Drop random quantity with looting bonus
        this.spawnAtLocation(ModItems.GLORPCORE.get(), 1); // Replace with your actual item
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 7) {
            // Spawn heart particles for taming success
            for (int i = 0; i < 7; ++i) {
                double offsetX = this.random.nextGaussian() * 0.02;
                double offsetY = this.random.nextGaussian() * 0.02;
                double offsetZ = this.random.nextGaussian() * 0.02;
                this.level().addParticle(ParticleTypes.HEART,
                        this.getRandomX(1.0),
                        this.getRandomY() + 0.5,
                        this.getRandomZ(1.0),
                        offsetX, offsetY, offsetZ);
            }
        } else if (id == 6) {
            // Spawn smoke particles for taming failure
            for (int i = 0; i < 7; ++i) {
                double offsetX = this.random.nextGaussian() * 0.02;
                double offsetY = this.random.nextGaussian() * 0.02;
                double offsetZ = this.random.nextGaussian() * 0.02;
                this.level().addParticle(ParticleTypes.SMOKE,
                        this.getRandomX(1.0),
                        this.getRandomY() + 0.5,
                        this.getRandomZ(1.0),
                        offsetX, offsetY, offsetZ);
            }
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new LeapAtTargetGoal(this, 0.4F));
        this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 1.0D, true));
        this.goalSelector.addGoal(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 2.0F));
        this.goalSelector.addGoal(5, new TemptGoal(this, 1.1D, Ingredient.of(ModItems.MYSTERIOUS_FUNGAL_TREAT.get()), false));
        this.goalSelector.addGoal(6, new RandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(7, new LookAtPlayerGoal(this, Player.class, 10.0F));
        this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));

        // Add more goals as needed
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes() // This creates the base attributes for any Mob.
                .add(Attributes.MAX_HEALTH, 20.0) // Health of 20
                .add(Attributes.MOVEMENT_SPEED, 0.15) // Movement speed
                .add(Attributes.FOLLOW_RANGE, 24D)
                .add(Attributes.ATTACK_DAMAGE, 4.0); // Attack damage
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ModItems.MYSTERIOUS_FUNGAL_TREAT.get());
    }

    @Override
    public @NotNull InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack itemInHand = player.getItemInHand(hand);

        // Define your taming item, e.g., MYSTERIOUS_FUNGAL_TREAT
        if (itemInHand.is(ModItems.MYSTERIOUS_FUNGAL_TREAT.get())) {
            // Entity already tamed
            if (this.isTame() && this.isOwnedBy(player)) {
                this.setOrderedToSit(!this.isOrderedToSit()); // Toggle sitting when tamed
                this.setInSittingPose(this.isOrderedToSit());
                return InteractionResult.SUCCESS;
            }
            // Try to tame the entity
            if (!this.level().isClientSide) {
                if (this.random.nextInt(5) == 0) { // 1 in 3 chance to succeed
                    this.tame(player);
                    this.level().broadcastEntityEvent(this, (byte) 7); // Success particles (heart)
                    return InteractionResult.SUCCESS;
                } else {
                    this.level().broadcastEntityEvent(this, (byte) 6); // Failure particles (smoke)
                    return InteractionResult.CONSUME;
                }
            }
            return InteractionResult.CONSUME;
        } else {
            if (this.isTame() && this.isOwnedBy(player)) {
                this.toggleSit();
                return InteractionResult.SUCCESS;

            }
        }
        return super.mobInteract(player, hand);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
        GlorpEntity baby = ModEntities.GLORP.get().create(level);
        assert baby != null;
        baby.setVariant(this.getVariant());
        return ModEntities.GLORP.get().create(level);
    }

    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 8 * 20; // 8 seconds
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if (isSitting) {
            if (!this.sitAnimationState.isStarted()) {
                this.sitAnimationState.start(this.tickCount); // Ensure sitting animation is active
            }
        } else {
            if (!this.idleAnimationState.isStarted()) {
                this.idleAnimationState.start(this.tickCount); // Ensure idle animation is active
            }
        }
    }

    /* VARIANT */

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
    }

    private int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    public GlorpVariant getVariant() {
        return GlorpVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(GlorpVariant variant) {
        this.entityData.set(VARIANT, variant.getId() & 255);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(VARIANT, compound.getInt("Variant"));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        GlorpVariant variant = Util.getRandom(GlorpVariant.values(), this.random);
        this.setVariant(variant);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    public boolean isSitting() {
        return this.isSitting;
    }
}
