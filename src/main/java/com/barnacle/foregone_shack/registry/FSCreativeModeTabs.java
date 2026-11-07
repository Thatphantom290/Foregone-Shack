package com.barnacle.foregone_shack.registry;

import com.barnacle.foregone_shack.ForegoneShack;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class FSCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TAB =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, ForegoneShack.MOD_ID);

    public static final Supplier<CreativeModeTab> FOREGONE_SHACK_TAB = CREATIVE_MODE_TAB.register("foregone_shack_tab",
            () -> CreativeModeTab.builder().icon(() -> {
                        long cycle = (System.currentTimeMillis() / 1000) % 3;
                        if (cycle == 0) return new ItemStack(FSItems.RED_POPIM.get());
                        if (cycle == 1) return new ItemStack(FSItems.ORANGE_POPIM.get());
                        return new ItemStack(FSItems.BLUE_POPIM.get());
                    })
                    .title(Component.translatable("creativetab.foregone_shack.foregone_shack_tab"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(FSItems.RED_POPIM);
                        output.accept(FSItems.ORANGE_POPIM);
                        output.accept(FSItems.BLUE_POPIM);
                        output.accept(FSItems.POPIM_SPAWN_EGG);
                    }).build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TAB.register(eventBus);
    }
}