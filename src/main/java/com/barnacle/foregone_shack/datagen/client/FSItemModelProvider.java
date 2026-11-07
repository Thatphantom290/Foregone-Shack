package com.barnacle.foregone_shack.datagen.client;

import com.barnacle.foregone_shack.ForegoneShack;
import com.barnacle.foregone_shack.registry.FSItems;
import com.platypushasnohat.sinew.datagen.client.SinewItemModelProvider;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.DeferredSpawnEggItem;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class FSItemModelProvider extends SinewItemModelProvider {

    public FSItemModelProvider(PackOutput output, ExistingFileHelper helper) {
            super(output, ForegoneShack.MOD_ID, helper);
    }

    @Override
    protected void registerModels() {
        this.generatedItem(
                FSItems.RED_POPIM,
                FSItems.BLUE_POPIM,
                FSItems.ORANGE_POPIM
        );

        for (Item item : BuiltInRegistries.ITEM) {
            if (item instanceof DeferredSpawnEggItem && BuiltInRegistries.ITEM.getKey(item).getNamespace().equals(ForegoneShack.MOD_ID)) {
                this.withExistingParent(name(item), "item/template_spawn_egg");
            }
        }
    }
}