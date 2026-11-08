package com.barnacle.foregone_shack.item;

import com.barnacle.foregone_shack.client.render.variants.PopimVariant;
import com.barnacle.foregone_shack.entity.release.Popim;
import com.barnacle.foregone_shack.registry.FSEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;

public class PopimItem extends Item {
    private final PopimVariant variant; // Store the variant in the item itself

    public PopimItem(Properties properties, PopimVariant variant) {
        super(properties);
        this.variant = variant;
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        if (context.getLevel().isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        ServerLevel serverLevel = (ServerLevel) context.getLevel();
        BlockPos clickedPos = context.getClickedPos();
        Direction direction = context.getClickedFace();
        BlockPos spawnPos = clickedPos.relative(direction);
        ItemStack itemStack = context.getItemInHand();

        Popim popim = FSEntities.POPIM.get().create(serverLevel);
        if (popim != null) {
            popim.setVariant(this.variant);

            popim.moveTo(
                    spawnPos.getX() + 0.5D,
                    spawnPos.getY(),
                    spawnPos.getZ() + 0.5D,
                    context.getRotation(),
                    0.0F
            );

            serverLevel.addFreshEntity(popim);

            if (!context.getPlayer().getAbilities().instabuild) {
                itemStack.shrink(1);
            }

            return InteractionResult.CONSUME;
        }

        return InteractionResult.PASS;
    }
}