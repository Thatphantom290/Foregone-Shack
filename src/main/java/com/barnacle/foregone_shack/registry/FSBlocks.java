package com.barnacle.foregone_shack.registry;

import com.barnacle.foregone_shack.ForegoneShack;
import com.barnacle.foregone_shack.block.PopimMoundBlock;
import com.barnacle.foregone_shack.block.PopimMoundSoilBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class FSBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(ForegoneShack.MOD_ID);

    public static List<DeferredBlock<? extends Block>> BLOCK_TRANSLATIONS = new ArrayList<>();

    public static final DeferredHolder<Block, PopimMoundBlock> POPIM_MOUND = BLOCKS.register("popim_mound", () -> new PopimMoundBlock(BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.SOUL_SOIL).destroyTime(1)));
    public static final DeferredBlock<Block> POPIM_MOUND_SOIL = registerBlock("popim_mound_soil", () -> new PopimMoundSoilBlock(BlockBehaviour.Properties.of().strength(0.2f).sound(SoundType.SOUL_SOIL).destroyTime(1)));


    private static <B extends Block> DeferredBlock<B> registerBlock(String name, Supplier<? extends B> supplier) {
        DeferredBlock<B> block = BLOCKS.register(name, supplier);
        FSItems.ITEM.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        BLOCK_TRANSLATIONS.add(block);
        return block;
    }

    private static <B extends Block> DeferredBlock<B> registerBlockNoLang(String name, Supplier<? extends B> supplier) {
        DeferredBlock<B> block = BLOCKS.register(name, supplier);
        FSItems.ITEM.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

}
