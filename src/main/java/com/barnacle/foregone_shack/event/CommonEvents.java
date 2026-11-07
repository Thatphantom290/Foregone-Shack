package com.barnacle.foregone_shack.event;

import com.barnacle.foregone_shack.ForegoneShack;
import com.barnacle.foregone_shack.entity.release.Popim;
import com.barnacle.foregone_shack.registry.FSEntities;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.entity.EntityAttributeCreationEvent;

@EventBusSubscriber(modid = ForegoneShack.MOD_ID)
public class CommonEvents {

    @SubscribeEvent
    public static void registerEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(FSEntities.POPIM.get(), Popim.registerAttributes().build());
    }
}