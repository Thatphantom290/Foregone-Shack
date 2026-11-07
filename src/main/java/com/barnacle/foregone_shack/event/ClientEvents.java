package com.barnacle.foregone_shack.event;

import com.barnacle.foregone_shack.ForegoneShack;
import com.barnacle.foregone_shack.client.model.PopimModel;
import com.barnacle.foregone_shack.registry.FSEntities;
import com.barnacle.foregone_shack.registry.FSModelLayers;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

import static com.barnacle.foregone_shack.client.render.entity.FSMobRenderer.createRenderer;

@EventBusSubscriber(modid = ForegoneShack.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {


    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(FSEntities.POPIM.get(), createRenderer(FSModelLayers.POPIM, PopimModel::new, 0.2F));
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(FSModelLayers.POPIM, PopimModel::createBodyLayer);
    }
}