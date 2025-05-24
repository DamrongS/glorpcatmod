package net.damrongmc.event;

import net.damrongmc.entity.ModEntities;
import net.damrongmc.entity.client.GlorpModel;
import net.damrongmc.entity.custom.GlorpEntity;
import net.damrongmc.glorpcat.GlorpCat;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = GlorpCat.MODID, bus = EventBusSubscriber.Bus.MOD)

public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(GlorpModel.LAYER_LOCATION, GlorpModel::createBodyLayer);
    }

    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.GLORP.get(), GlorpEntity.createAttributes().build());
    }

}
