package com.barnacle.foregone_shack;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(ForegoneShack.MODID)
public class ForegoneShack {
    public static final String MODID = "foregone_shack";
    public static final Logger LOGGER = LogUtils.getLogger();

    public ForegoneShack(IEventBus modEventBus, ModContainer modContainer) {
    }
}
