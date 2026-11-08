package com.barnacle.foregone_shack.client.model;

import com.barnacle.foregone_shack.entity.release.Popim;
import com.platypushasnohat.sinew.client.model.entity.SinewEntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class PopimModel extends SinewEntityModel<Popim> {
    private final ModelPart root;
    private final ModelPart body;

    public PopimModel(ModelPart root) {
        this.root = root.getChild("root");
        this.body = this.root.getChild("body");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition root = partdefinition.addOrReplaceChild("root", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = root.addOrReplaceChild("body", CubeListBuilder.create().texOffs(-8, 0).addBox(-2.5F, -0.3F, -4.0F, 5.0F, 0.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 10, 8);
    }

    @Override
    public ModelPart root() {
        return root;
    }

    @Override
    protected void setupAnimations(Popim popim, float v, float v1, float v2, float v3, float v4, float v5) {
    }
}
