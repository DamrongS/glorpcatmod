package net.damrongmc.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.damrongmc.entity.custom.GlorpEntity;
import net.damrongmc.glorpcat.GlorpCat;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class GlorpRenderer extends MobRenderer<GlorpEntity, GlorpModel<GlorpEntity>> {
    public GlorpRenderer(EntityRendererProvider.Context context) {
        super(context, new GlorpModel<>(context.bakeLayer(GlorpModel.LAYER_LOCATION)), 0.25f);
    }

    @Override
    public ResourceLocation getTextureLocation(GlorpEntity entity) {
        return ResourceLocation.fromNamespaceAndPath(GlorpCat.MODID, "textures/entity/glorp/glorp_texture.png");
    }

    @Override
    public void render(GlorpEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        if(entity.isBaby()) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}