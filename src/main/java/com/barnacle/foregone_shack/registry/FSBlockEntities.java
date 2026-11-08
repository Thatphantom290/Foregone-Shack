package com.barnacle.foregone_shack.registry;

import com.barnacle.foregone_shack.entity.block.PopimMoundBlockEntity;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class FSBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, "foregone_shack");

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<PopimMoundBlockEntity>> POPIM_MOUND = BLOCK_ENTITIES.register("popim_mound", () -> BlockEntityType.Builder.of(PopimMoundBlockEntity::new, FSBlocks.POPIM_MOUND.get()).build(null));
}