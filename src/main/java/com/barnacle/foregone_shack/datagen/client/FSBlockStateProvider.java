package com.barnacle.foregone_shack.datagen.client;

import com.barnacle.foregone_shack.ForegoneShack;
import com.barnacle.foregone_shack.registry.FSBlocks;
import com.platypushasnohat.sinew.datagen.client.SinewBlockStateProvider;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class FSBlockStateProvider extends SinewBlockStateProvider {

    public FSBlockStateProvider(PackOutput output, ExistingFileHelper helper) {
        super(output, ForegoneShack.MOD_ID, helper);
    }

    @Override
    protected void registerStatesAndModels() {
        this.block(FSBlocks.POPIM_MOUND_SOIL);
        this.block(FSBlocks.POPIM_MOUND);
    }
}