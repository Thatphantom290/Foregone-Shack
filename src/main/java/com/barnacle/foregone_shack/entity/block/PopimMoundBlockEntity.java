package com.barnacle.foregone_shack.entity.block;

import com.barnacle.foregone_shack.entity.release.Popim;
import com.barnacle.foregone_shack.registry.FSBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.Tag;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public class PopimMoundBlockEntity extends BlockEntity {
    private final List<Occupant> storedPopims = new ArrayList<>();
    private static final int MAX_OCCUPANTS = 6;

    public PopimMoundBlockEntity(BlockPos pos, BlockState state) {
        super(FSBlockEntities.POPIM_MOUND.get(), pos, state);
    }

    public record Occupant(CompoundTag entityData, int ticksInMound, int minTicksToStay) {}

    public boolean isFull() {
        return this.storedPopims.size() >= MAX_OCCUPANTS;
    }

    public void tryEnterMound(Popim popim) {
        if (this.isFull() || this.level == null || this.level.isClientSide()) return;

        CompoundTag tag = new CompoundTag();
        popim.save(tag);

        this.storedPopims.add(new Occupant(tag, 0, 400));
        popim.discard();
        setChanged();
    }

    public void releaseAllPopims() {
        if (this.level == null || this.level.isClientSide() || this.storedPopims.isEmpty()) {
            return;
        }

        for (Occupant occupant : this.storedPopims) {
            Optional<Entity> entityOpt = EntityType.create(occupant.entityData(), this.level);
            if (entityOpt.isPresent() && entityOpt.get() instanceof Popim popim) {
                popim.moveTo(
                        this.worldPosition.getX() + 0.5,
                        this.worldPosition.getY() + 0.5,
                        this.worldPosition.getZ() + 0.5,
                        level.random.nextFloat() * 360F,
                        0.0F
                );
                this.level.addFreshEntity(popim);
            }
        }

        this.storedPopims.clear();
        this.setChanged();
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, PopimMoundBlockEntity mound) {
        if (mound.storedPopims.isEmpty()) {
            return;
        }

        List<Occupant> updatedList = new ArrayList<>();
        boolean modified = false;

        for (Occupant occupant : mound.storedPopims) {
            int newTicks = occupant.ticksInMound() + 1;

            if (newTicks >= occupant.minTicksToStay()) {
                Optional<Entity> entityOpt = EntityType.create(occupant.entityData(), level);
                if (entityOpt.isPresent() && entityOpt.get() instanceof Popim popim) {
                    popim.moveTo(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, level.random.nextFloat() * 360F, 0.0F);
                    level.addFreshEntity(popim);
                    modified = true;
                }
            } else {
                updatedList.add(new Occupant(occupant.entityData(), newTicks, occupant.minTicksToStay()));
            }
        }

        if (modified || updatedList.size() != mound.storedPopims.size()) {
            mound.storedPopims.clear();
            mound.storedPopims.addAll(updatedList);
            mound.setChanged();
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.saveAdditional(tag, registries);
        ListTag list = new ListTag();
        for (Occupant occupant : storedPopims) {
            CompoundTag occTag = new CompoundTag();
            occTag.put("EntityData", occupant.entityData());
            occTag.putInt("TicksInMound", occupant.ticksInMound());
            occTag.putInt("MinTicksToStay", occupant.minTicksToStay());
            list.add(occTag);
        }
        tag.put("Occupants", list);
    }

    @Override
    protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
        super.loadAdditional(tag, registries);
        storedPopims.clear();
        ListTag list = tag.getList("Occupants", Tag.TAG_COMPOUND);
        for (int i = 0; i < list.size(); i++) {
            CompoundTag occTag = list.getCompound(i);
            storedPopims.add(new Occupant(
                    occTag.getCompound("EntityData"),
                    occTag.getInt("TicksInMound"),
                    occTag.getInt("MinTicksToStay")
            ));
        }
    }
}