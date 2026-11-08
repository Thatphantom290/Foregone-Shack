package com.barnacle.foregone_shack.registry;

import com.barnacle.foregone_shack.worldgen.feature.PopimMoundFeature;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FSFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(Registries.FEATURE, "foregone_shack");

    public static final DeferredHolder<Feature<?>, PopimMoundFeature> POPIM_MOUND_FEATURE =
            FEATURES.register("popim_mound", () -> new PopimMoundFeature(NoneFeatureConfiguration.CODEC));
}