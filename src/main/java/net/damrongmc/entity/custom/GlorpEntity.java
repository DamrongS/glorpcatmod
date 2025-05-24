package net.damrongmc.entity.custom;

import net.damrongmc.entity.ModEntities;
import net.damrongmc.glorpcat.item.ModItems;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class GlorpEntity extends TamableAnimal {

    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public GlorpEntity(EntityType<? extends TamableAnimal> entityType, Level level) {
        super(entityType, level);
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

    @Override
    public boolean isOwnedBy(LivingEntity entity) {
        return entity == this.getOwner();
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
        this.goalSelector.addGoal(2, new PanicGoal(this, 1.5));
        this.goalSelector.addGoal(3, new TemptGoal(this, 1.25, stack -> stack.is(ModItems.MYSTERIOUS_FUNGAL_TREAT.get()), false));
        // Add more goals as needed
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GLORP.get(), GlorpEntity.createAttributes().build());
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes() // This creates the base attributes for any Mob.
                .add(Attributes.MAX_HEALTH, 20.0) // Health of 20
                .add(Attributes.MOVEMENT_SPEED, 0.25) // Movement speed
                .add(Attributes.ATTACK_DAMAGE, 4.0); // Attack damage
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return stack.is(ModItems.MYSTERIOUS_FUNGAL_TREAT.get());
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
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
                if (this.random.nextInt(3) == 0) { // 1 in 3 chance to succeed
                    this.tame(player);
                    this.level().broadcastEntityEvent(this, (byte) 7); // Success particles (heart)
                    return InteractionResult.SUCCESS;
                } else {
                    this.level().broadcastEntityEvent(this, (byte) 6); // Failure particles (smoke)
                }
            }
            return InteractionResult.CONSUME;
        }
        return super.mobInteract(player, hand);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob otherParent) {
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

        if (this.level().isClientSide) {
            this.setupAnimationStates();
        }
    }
}
