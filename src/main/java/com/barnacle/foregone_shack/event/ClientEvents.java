package com.barnacle.foregone_shack.event;

import com.barnacle.foregone_shack.ForegoneShack;
import com.barnacle.foregone_shack.client.model.PopimModel;
import com.barnacle.foregone_shack.client.render.entity.PopimRenderer;
import com.barnacle.foregone_shack.registry.FSEntities;
import com.barnacle.foregone_shack.registry.FSModelLayers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@EventBusSubscriber(modid = ForegoneShack.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {


    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(FSEntities.POPIM.get(), PopimRenderer::new);
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FSModelLayers.POPIM, PopimModel::createBodyLayer);
    }
}