package com.barnacle.foregone_shack.registry;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class FSFoodProperties {
    public static final FoodProperties POPIM = new FoodProperties.Builder().nutrition(3).saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 1600), 300f).build();
}
