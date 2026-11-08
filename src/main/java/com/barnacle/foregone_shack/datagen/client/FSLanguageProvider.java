package com.barnacle.foregone_shack.datagen.client;

import com.barnacle.foregone_shack.ForegoneShack;
import com.barnacle.foregone_shack.registry.FSBlocks;
import com.barnacle.foregone_shack.registry.FSCreativeModeTabs;
import com.barnacle.foregone_shack.registry.FSEntities;
import com.barnacle.foregone_shack.registry.FSItems;
import com.platypushasnohat.sinew.datagen.client.SinewLanguageProvider;
import net.minecraft.data.PackOutput;

public class FSLanguageProvider extends SinewLanguageProvider {

    public FSLanguageProvider(PackOutput output) {
        super(output, ForegoneShack.MOD_ID);
    }

    @Override
    protected void addTranslations() {
        this.addCreativeTab(FSCreativeModeTabs.FOREGONE_SHACK_TAB.get(), "Foregone Shack");

        FSBlocks.BLOCK_TRANSLATIONS.forEach(this::forBlock);
        FSItems.ITEM_TRANSLATIONS.forEach(this::forItem);
        FSEntities.ENTITY_TRANSLATIONS.forEach(this::forEntity);

        this.add(FSBlocks.POPIM_MOUND.get(), "Popim Mound");
    }
}