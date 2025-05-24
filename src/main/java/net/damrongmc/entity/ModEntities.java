package net.damrongmc.entity;

import net.damrongmc.entity.custom.GlorpEntity;
import net.damrongmc.glorpcat.GlorpCat;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES =
            DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, GlorpCat.MODID);

    public static final Supplier<EntityType<GlorpEntity>> GLORP =
            ENTITY_TYPES.register("glorp", () -> EntityType.Builder.of(GlorpEntity::new, MobCategory.CREATURE)
                    .sized(0.75f, 0.35f).build("glorp"));

    public static void register(IEventBus eventBus) {
        ENTITY_TYPES.register(eventBus);
    }
}
