package com.barnacle.foregone_shack.block;

import com.barnacle.foregone_shack.registry.FSBlocks;
import com.barnacle.foregone_shack.registry.FSItems;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

public class PopimMoundSoilBlock extends Block {

    public PopimMoundSoilBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        boolean isPopimItem = stack.is(FSItems.BLUE_POPIM.get()) ||
                stack.is(FSItems.ORANGE_POPIM.get()) ||
                stack.is(FSItems.RED_POPIM.get()) ||
                stack.is(FSItems.BLACK_POPIM.get());

        if (isPopimItem) {
            if (!level.isClientSide()) {
                level.setBlock(pos, FSBlocks.POPIM_MOUND.get().defaultBlockState(), 3);

                if (!player.getAbilities().instabuild) {
                    stack.shrink(1);
                }

                level.playSound(null, pos, SoundEvents.BEEHIVE_WORK, SoundSource.BLOCKS, 1.0F, 1.0F);
            }

            return ItemInteractionResult.sidedSuccess(level.isClientSide());
        }

        return super.useItemOn(stack, state, level, pos, player, hand, hitResult);
    }
}