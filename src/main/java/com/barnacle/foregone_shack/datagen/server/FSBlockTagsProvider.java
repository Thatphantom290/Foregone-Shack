package com.barnacle.foregone_shack.datagen.server;

import com.barnacle.foregone_shack.ForegoneShack;
import com.barnacle.foregone_shack.registry.FSBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;

public class FSBlockTagsProvider extends BlockTagsProvider {

    public FSBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper helper) {
        super(output, provider, ForegoneShack.MOD_ID, helper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {

        this.tag(BlockTags.MINEABLE_WITH_SHOVEL).add(
                FSBlocks.POPIM_MOUND.get(),
                FSBlocks.POPIM_MOUND_SOIL.get()
        );
    }
}