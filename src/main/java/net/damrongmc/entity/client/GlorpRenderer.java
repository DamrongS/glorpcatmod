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
    private final MaidGlorpModel<GlorpEntity> maidModel;
    private final FatGlorpModel<GlorpEntity> fatModel;

    public GlorpRenderer(EntityRendererProvider.Context context) {
        super(context, new GlorpModel<>(context.bakeLayer(GlorpModel.LAYER_LOCATION)), 0.25f);

        // Cache baked models once instead of baking every frame
        this.normalModel = new GlorpModel<>(context.bakeLayer(GlorpModel.LAYER_LOCATION));
        this.maidModel = new MaidGlorpModel<>(context.bakeLayer(MaidGlorpModel.LAYER_LOCATION));
        this.fatModel = new FatGlorpModel<>(context.bakeLayer(FatGlorpModel.LAYER_LOCATION));
    }

    @Override
    public ResourceLocation getTextureLocation(GlorpEntity entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }

    @Override
    public void render(GlorpEntity entity, float entityYaw, float partialTicks, PoseStack poseStack,
                       MultiBufferSource buffer, int packedLight) {

        if(entity.getVariant() == GlorpVariant.NORMAL) {
            this.model = normalModel;
        } else if(entity.getVariant() == GlorpVariant.FAT) {
            this.model = fatModel;
        } else if(entity.getVariant() == GlorpVariant.MAID) {
            this.model = maidModel;
        }

        // Scale if baby
        if (entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}