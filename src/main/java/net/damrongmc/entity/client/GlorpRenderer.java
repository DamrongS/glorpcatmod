package net.damrongmc.entity.client;

import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.damrongmc.entity.GlorpVariant;
import net.damrongmc.entity.ModEntities;
import net.damrongmc.entity.custom.GlorpEntity;
import net.damrongmc.glorpcat.GlorpCat;
import net.minecraft.Util;
import net.minecraft.client.animation.AnimationDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class GlorpRenderer extends MobRenderer<GlorpEntity, GlorpModel<GlorpEntity>> {
    public static final Map<GlorpVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(GlorpVariant.class), map -> {
                map.put(GlorpVariant.NORMAL,
                        ResourceLocation.fromNamespaceAndPath(GlorpCat.MODID, "textures/entity/glorp/glorp_texture.png"));
                map.put(GlorpVariant.FAT,
                        ResourceLocation.fromNamespaceAndPath(GlorpCat.MODID, "textures/entity/glorp/fat_glorp_texture.png"));
                map.put(GlorpVariant.MAID,
                        ResourceLocation.fromNamespaceAndPath(GlorpCat.MODID, "textures/entity/glorp/maid_glorp_texture.png"));
            });

    private final GlorpModel<GlorpEntity> normalModel;
    private final GlorpModel<GlorpEntity> maidModel;
    private final GlorpModel<GlorpEntity> fatModel;
    private final EntityRendererProvider.Context context;

    public GlorpRenderer(EntityRendererProvider.Context context) {
        super(context, new GlorpModel<>(context.bakeLayer(GlorpModel.LAYER_LOCATION)), 0.25f);
        this.context = context;

        // Cache baked models once instead of baking every frame
        this.normalModel = new GlorpModel<>(context.bakeLayer(GlorpModel.LAYER_LOCATION));
        this.maidModel = new GlorpModel<>(context.bakeLayer(GlorpModel.LAYER_LOCATION));
        this.fatModel = new GlorpModel<>(context.bakeLayer(FatGlorpModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(GlorpEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(GlorpEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight) {

        // Switch model based on variant
        switch (entity.getVariant()) {
            case NORMAL -> this.model = normalModel;
            case FAT -> this.model = fatModel;
            case MAID -> this.model = maidModel;
        }

        // Scale if baby
        if (entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        }

        // Sitting animation
        if (entity.isSitting()) {
            entity.sitAnimationState.SIT(entity.tickCount);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}