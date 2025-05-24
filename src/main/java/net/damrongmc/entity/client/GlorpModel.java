package net.damrongmc.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.damrongmc.entity.custom.GlorpEntity;
import net.damrongmc.glorpcat.GlorpCat;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class GlorpModel<T extends GlorpEntity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION =
            new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(GlorpCat.MODID, "glorp"), "main");

    private final ModelPart Body;
    private final ModelPart Head;

    public GlorpModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Head = this.Body.getChild("Head");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create(), PartPose.offset(0.0F, 13.8333F, 0.1667F));

        PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(27, 28).addBox(-2.0F, -3.125F, -4.125F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 12).addBox(-3.0F, -5.125F, -3.125F, 6.0F, 5.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(24, 5).addBox(-4.0F, -3.125F, -2.125F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(24, 12).addBox(3.0F, -3.125F, -2.125F, 1.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.7083F, -0.0417F));

        PartDefinition LAntenna = Head.addOrReplaceChild("LAntenna", CubeListBuilder.create(), PartPose.offset(1.5F, -5.125F, 1.375F));

        PartDefinition LJoint0 = LAntenna.addOrReplaceChild("LJoint0", CubeListBuilder.create().texOffs(16, 37).addBox(-1.0F, -2.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.0F, -0.5F));

        PartDefinition LJoint1 = LAntenna.addOrReplaceChild("LJoint1", CubeListBuilder.create().texOffs(20, 37).addBox(-1.0F, -3.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.0F, -0.5F));

        PartDefinition LJoint2 = LAntenna.addOrReplaceChild("LJoint2", CubeListBuilder.create().texOffs(24, 37).addBox(-1.0F, -4.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.0F, -0.5F));

        PartDefinition LJoint3 = LAntenna.addOrReplaceChild("LJoint3", CubeListBuilder.create().texOffs(28, 37).addBox(-1.0F, -5.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.0F, -0.5F));

        PartDefinition LJoint4 = LAntenna.addOrReplaceChild("LJoint4", CubeListBuilder.create().texOffs(34, 11).addBox(-1.0F, -6.0F, -1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.0F, -0.5F));

        PartDefinition RAntenna = Head.addOrReplaceChild("RAntenna", CubeListBuilder.create(), PartPose.offset(-1.5F, -5.125F, 1.375F));

        PartDefinition RJoint0 = RAntenna.addOrReplaceChild("RJoint0", CubeListBuilder.create().texOffs(4, 38).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));

        PartDefinition RJoint1 = RAntenna.addOrReplaceChild("RJoint1", CubeListBuilder.create().texOffs(0, 38).addBox(-8.0F, -11.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 9.0F, -1.5F));

        PartDefinition RJoint2 = RAntenna.addOrReplaceChild("RJoint2", CubeListBuilder.create().texOffs(36, 37).addBox(-8.0F, -12.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 9.0F, -1.5F));

        PartDefinition RJoint3 = RAntenna.addOrReplaceChild("RJoint3", CubeListBuilder.create().texOffs(32, 37).addBox(-8.0F, -13.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 9.0F, -1.5F));

        PartDefinition RJoint4 = RAntenna.addOrReplaceChild("RJoint4", CubeListBuilder.create().texOffs(34, 14).addBox(-8.0F, -14.0F, 0.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(7.5F, 9.0F, -1.5F));

        PartDefinition Lear = Head.addOrReplaceChild("Lear", CubeListBuilder.create(), PartPose.offset(2.0F, -4.125F, -0.125F));

        PartDefinition Lear_r1 = Lear.addOrReplaceChild("Lear_r1", CubeListBuilder.create().texOffs(34, 5).addBox(0.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F));

        PartDefinition Rear = Head.addOrReplaceChild("Rear", CubeListBuilder.create().texOffs(34, 8).addBox(-1.0F, -2.0F, 0.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -4.125F, -0.125F));

        PartDefinition Tail = Body.addOrReplaceChild("Tail", CubeListBuilder.create(), PartPose.offset(0.0F, 5.1667F, 2.8333F));

        PartDefinition Joint0 = Tail.addOrReplaceChild("Joint0", CubeListBuilder.create().texOffs(24, 19).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition Joint1 = Tail.addOrReplaceChild("Joint1", CubeListBuilder.create().texOffs(32, 19).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 2.0F));

        PartDefinition Joint2 = Tail.addOrReplaceChild("Joint2", CubeListBuilder.create().texOffs(32, 31).addBox(-1.0F, -1.0F, 0.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));

        PartDefinition LeftArm = Body.addOrReplaceChild("LeftArm", CubeListBuilder.create().texOffs(8, 30).addBox(0.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -0.8333F, -0.1667F));

        PartDefinition RightArm = Body.addOrReplaceChild("RightArm", CubeListBuilder.create().texOffs(0, 30).addBox(-2.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -0.8333F, -0.1667F));

        PartDefinition LeftLeg = Body.addOrReplaceChild("LeftLeg", CubeListBuilder.create().texOffs(24, 31).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 6.1667F, -0.1667F));

        PartDefinition LeftToe = LeftLeg.addOrReplaceChild("LeftToe", CubeListBuilder.create().texOffs(32, 35).addBox(-5.0F, 8.0F, -2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -5.0F, 0.0F));

        PartDefinition RightLeg = Body.addOrReplaceChild("RightLeg", CubeListBuilder.create().texOffs(16, 31).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 6.1667F, -0.1667F));

        PartDefinition RIghtToe = RightLeg.addOrReplaceChild("RIghtToe", CubeListBuilder.create().texOffs(34, 17).addBox(-9.0F, 8.0F, -2.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -5.0F, 0.0F));

        PartDefinition Belly = Body.addOrReplaceChild("Belly", CubeListBuilder.create().texOffs(24, 0).addBox(-2.0F, -8.8333F, -2.1667F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(0, 23).addBox(-3.0F, -7.8333F, -2.1667F, 6.0F, 2.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-3.0F, -5.8333F, -3.1667F, 6.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 64, 64);
    }

    @Override
    public void setupAnim(GlorpEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(netHeadYaw, headPitch);

        this.animateWalk(GlorpAnimations.WALK, limbSwing, limbSwingAmount, 2f, 2.5f);
        this.animate(entity.idleAnimationState, GlorpAnimations.IDLE, ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 25f);

        this.Head.yRot = headYaw * ((float)Math.PI / 180F);
        this.Head.xRot = headPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public ModelPart root() {
        return Body;
    }
}