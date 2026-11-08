package com.barnacle.foregone_shack.entity.base;

import com.platypushasnohat.sinew.entity.base.AnimatedTamableAnimal;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.level.Level;

public abstract class FSMob extends AnimatedTamableAnimal {

    protected static final EntityDataAccessor<Integer> IDLE_STATE = SynchedEntityData.defineId(FSMob.class, EntityDataSerializers.INT);
    protected static final EntityDataAccessor<Boolean> RUNNING = SynchedEntityData.defineId(FSMob.class, EntityDataSerializers.BOOLEAN);

    protected int idleAnimationCooldown;

    protected FSMob(EntityType<? extends FSMob> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(IDLE_STATE, 0);
        builder.define(RUNNING, false);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compoundTag) {
        super.addAdditionalSaveData(compoundTag);
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compoundTag) {
        super.readAdditionalSaveData(compoundTag);
    }

    public boolean isRunning() {
        return this.entityData.get(RUNNING);
    }
    public void setRunning(boolean running) {
        this.entityData.set(RUNNING, running);
    }

    public void setupAnimationStates() {
    }

    @Override
    public void calculateEntityAnimation(boolean flying) {
        float pos = (float) Mth.length(this.getX() - this.xo, this.getY() - this.yo, this.getZ() - this.zo);
        float speed = Math.min(pos * this.getWalkAnimationSpeed(), 1.0F);
        this.walkAnimation.update(speed, 0.4F);
    }

    public float getWalkAnimationSpeed() {
        return this.isBaby() ? 5.0F : 10.0F;
    }

    public void setIdleAnimationCooldown(int animationCooldown) {
        this.idleAnimationCooldown = animationCooldown;
    }

    public int getIdleAnimationCooldown() {
        return this.idleAnimationCooldown;
    }

    public int getIdleAnimationCooldown(int idleState) {
        return 0;
    }
}
