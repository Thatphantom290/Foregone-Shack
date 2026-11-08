package com.barnacle.foregone_shack;

import com.barnacle.foregone_shack.datagen.client.FSBlockStateProvider;
import com.barnacle.foregone_shack.datagen.client.FSItemModelProvider;
import com.barnacle.foregone_shack.datagen.client.FSLanguageProvider;
import com.barnacle.foregone_shack.datagen.server.FSBlockTagsProvider;
import com.barnacle.foregone_shack.registry.*;
import com.mojang.logging.LogUtils;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.slf4j.Logger;

import java.util.Locale;
import java.util.concurrent.CompletableFuture;

@Mod(ForegoneShack.MOD_ID)
public class ForegoneShack {
    public static final String MOD_ID = "foregone_shack";
    public static final Logger LOGGER = LogUtils.getLogger();

    public static ResourceLocation location(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path.toLowerCase(Locale.ROOT));
    }

    public ForegoneShack(IEventBus modEventBus, ModContainer modContainer) {
        FSEntities.ENTITY_TYPE.register(modEventBus);
        FSItems.ITEM.register(modEventBus);
        FSBlocks.BLOCKS.register(modEventBus);
        FSBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        FSCreativeModeTabs.CREATIVE_MODE_TAB.register(modEventBus);
        FSFeatures.FEATURES.register(modEventBus);

        modEventBus.addListener(this::dataSetup);
    }


    private void dataSetup(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<Provider> provider = event.getLookupProvider();
        ExistingFileHelper helper = event.getExistingFileHelper();

        boolean server = event.includeServer();
        boolean client = event.includeClient();

        FSBlockTagsProvider blockTags = new FSBlockTagsProvider(output, provider, helper);
        generator.addProvider(server, blockTags);

        generator.addProvider(client, new FSBlockStateProvider(output, helper));
        generator.addProvider(client, new FSItemModelProvider(output, helper));
        generator.addProvider(client, new FSLanguageProvider(output));
    }
}
