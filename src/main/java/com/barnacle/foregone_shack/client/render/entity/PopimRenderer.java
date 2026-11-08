package com.barnacle.foregone_shack.client.render.entity;

import com.barnacle.foregone_shack.ForegoneShack;
import com.barnacle.foregone_shack.client.model.PopimModel;
import com.barnacle.foregone_shack.client.render.variants.PopimVariant;
import com.barnacle.foregone_shack.entity.release.Popim;
import com.barnacle.foregone_shack.registry.FSModelLayers;
import com.google.common.collect.Maps;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.Util;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

import java.util.Map;

public class PopimRenderer extends MobRenderer<Popim, PopimModel> {
    private static final Map<PopimVariant, ResourceLocation> LOCATION_BY_VARIANT =
            Util.make(Maps.newEnumMap(PopimVariant.class), map -> {
                map.put(PopimVariant.RED,
                        ResourceLocation.fromNamespaceAndPath(ForegoneShack.MOD_ID, "textures/entity/popim/popim_red.png"));
                map.put(PopimVariant.ORANGE,
                        ResourceLocation.fromNamespaceAndPath(ForegoneShack.MOD_ID, "textures/entity/popim/popim_orange.png"));
                map.put(PopimVariant.BLUE,
                        ResourceLocation.fromNamespaceAndPath(ForegoneShack.MOD_ID, "textures/entity/popim/popim_blue.png"));
            });


    public PopimRenderer(EntityRendererProvider.Context context) {
        super(context, new PopimModel(context.bakeLayer(FSModelLayers.POPIM)), 0.3f);
    }

    @Override
    public ResourceLocation getTextureLocation(Popim entity) {
        return LOCATION_BY_VARIANT.get(entity.getVariant());
    }


    @Override
    public void render(Popim entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    }
}