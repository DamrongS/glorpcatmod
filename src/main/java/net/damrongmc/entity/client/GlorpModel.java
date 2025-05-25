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
import org.jetbrains.annotations.NotNull;

public class GlorpModel<T extends GlorpEntity> extends HierarchicalModel<T> {
    // This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(GlorpCat.MODID, "glorp"), "main");
    private final ModelPart Body;
    private final ModelPart Head;
    private final ModelPart LAntenna;
    private final ModelPart LJoint0;
    private final ModelPart LJoint1;
    private final ModelPart LJoint2;
    private final ModelPart LJoint3;
    private final ModelPart LJoint4;
    private final ModelPart RAntenna;
    private final ModelPart RJoint0;
    private final ModelPart RJoint1;
    private final ModelPart RJoint2;
    private final ModelPart RJoint3;
    private final ModelPart RJoint4;
    private final ModelPart Lear;
    private final ModelPart Rear;
    private final ModelPart Tail;
    private final ModelPart Joint0;
    private final ModelPart Joint1;
    private final ModelPart Joint2;
    private final ModelPart LeftArm;
    private final ModelPart RightArm;
    private final ModelPart LeftLeg;
    private final ModelPart LeftToe;
    private final ModelPart RightLeg;
    private final ModelPart RIghtToe;
    private final ModelPart Belly;

    public GlorpModel(ModelPart root) {
        this.Body = root.getChild("Body");
        this.Head = this.Body.getChild("Head");
        this.LAntenna = this.Head.getChild("LAntenna");
        this.LJoint0 = this.LAntenna.getChild("LJoint0");
        this.LJoint1 = this.LAntenna.getChild("LJoint1");
        this.LJoint2 = this.LAntenna.getChild("LJoint2");
        this.LJoint3 = this.LAntenna.getChild("LJoint3");
        this.LJoint4 = this.LAntenna.getChild("LJoint4");
        this.RAntenna = this.Head.getChild("RAntenna");
        this.RJoint0 = this.RAntenna.getChild("RJoint0");
        this.RJoint1 = this.RAntenna.getChild("RJoint1");
        this.RJoint2 = this.RAntenna.getChild("RJoint2");
        this.RJoint3 = this.RAntenna.getChild("RJoint3");
        this.RJoint4 = this.RAntenna.getChild("RJoint4");
        this.Lear = this.Head.getChild("Lear");
        this.Rear = this.Head.getChild("Rear");
        this.Tail = this.Body.getChild("Tail");
        this.Joint0 = this.Tail.getChild("Joint0");
        this.Joint1 = this.Tail.getChild("Joint1");
        this.Joint2 = this.Tail.getChild("Joint2");
        this.LeftArm = this.Body.getChild("LeftArm");
        this.RightArm = this.Body.getChild("RightArm");
        this.LeftLeg = this.Body.getChild("LeftLeg");
        this.LeftToe = this.LeftLeg.getChild("LeftToe");
        this.RightLeg = this.Body.getChild("RightLeg");
        this.RIghtToe = this.RightLeg.getChild("RIghtToe");
        this.Belly = this.Body.getChild("Belly");
    }

    public GlorpModel(ModelPart body, ModelPart head, ModelPart lAntenna, ModelPart lJoint0, ModelPart lJoint1, ModelPart lJoint2, ModelPart lJoint3, ModelPart lJoint4, ModelPart rAntenna, ModelPart rJoint0, ModelPart rJoint1, ModelPart rJoint2, ModelPart rJoint3, ModelPart rJoint4, ModelPart lear, ModelPart rear, ModelPart tail, ModelPart joint0, ModelPart joint1, ModelPart joint2, ModelPart leftArm, ModelPart rightArm, ModelPart leftLeg, ModelPart leftToe, ModelPart rightLeg, ModelPart rIghtToe, ModelPart belly) {
        Body = body;
        Head = head;
        LAntenna = lAntenna;
        LJoint0 = lJoint0;
        LJoint1 = lJoint1;
        LJoint2 = lJoint2;
        LJoint3 = lJoint3;
        LJoint4 = lJoint4;
        RAntenna = rAntenna;
        RJoint0 = rJoint0;
        RJoint1 = rJoint1;
        RJoint2 = rJoint2;
        RJoint3 = rJoint3;
        RJoint4 = rJoint4;
        Lear = lear;
        Rear = rear;
        Tail = tail;
        Joint0 = joint0;
        Joint1 = joint1;
        Joint2 = joint2;
        LeftArm = leftArm;
        RightArm = rightArm;
        LeftLeg = leftLeg;
        LeftToe = leftToe;
        RightLeg = rightLeg;
        RIghtToe = rIghtToe;
        Belly = belly;
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
        this.animate(entity.sitAnimationState, GlorpAnimations.SIT, ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 25f);

        this.Head.yRot = headYaw * ((float)Math.PI / 180F);
        this.Head.xRot = headPitch * ((float)Math.PI / 180F);
    }

    @Override
    public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
        Body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
    }

    @Override
    public @NotNull ModelPart root() {
        return Body;
    }
}