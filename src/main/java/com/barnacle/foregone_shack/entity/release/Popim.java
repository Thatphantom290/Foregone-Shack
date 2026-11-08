package com.barnacle.foregone_shack.entity.release;

import com.barnacle.foregone_shack.client.render.variants.PopimVariant;
import com.barnacle.foregone_shack.registry.FSItems;
import net.minecraft.Util;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ServerLevelAccessor;
import org.jetbrains.annotations.Nullable;
import com.barnacle.foregone_shack.entity.base.FSMob;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class Popim extends FSMob {

    private static final EntityDataAccessor<Integer> VARIANT = SynchedEntityData.defineId(Popim.class, EntityDataSerializers.INT);

    public Popim(EntityType<? extends Popim> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void registerGoals() {this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Player.class, 16.0F, 1.0D, 1.3D));

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1.0D));

        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder registerAttributes() {
        return Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 3.0D)
                .add(Attributes.MOVEMENT_SPEED, 0.25D)
                .add(Attributes.FOLLOW_RANGE, 16.0D);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIANT, 0);
    }

    private int getTypeVariant() {
        return this.entityData.get(VARIANT);
    }

    public PopimVariant getVariant() {
        return PopimVariant.byId(this.getTypeVariant() & 255);
    }

    private void setVariant(PopimVariant variant) {
        this.entityData.set(VARIANT, variant.getId() & 255);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag compound) {
        super.addAdditionalSaveData(compound);
        compound.putInt("Variant", this.getTypeVariant());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag compound) {
        super.readAdditionalSaveData(compound);
        this.entityData.set(VARIANT, compound.getInt("Variant"));
    }

    @Override
    public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance difficulty,
                                        MobSpawnType spawnType, @Nullable SpawnGroupData spawnGroupData) {
        PopimVariant variant = Util.getRandom(PopimVariant.values(), this.random);
        this.setVariant(variant);
        return super.finalizeSpawn(level, difficulty, spawnType, spawnGroupData);
    }

    @Override
    public void playAmbientSound() {
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.SILVERFISH_AMBIENT, this.getSoundSource(), 0.3F, 0.5F);

        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.SILVERFISH_STEP, this.getSoundSource(), 0.1F, 0.1F);
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSource) {
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.SILVERFISH_HURT, this.getSoundSource(), 0.6F, 0.8F);
        return SoundEvents.STONE_HIT;
    }

    @Override
    protected SoundEvent getDeathSound() {
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(),
                SoundEvents.SILVERFISH_DEATH, this.getSoundSource(), 0.3F, 0.3F);

        return SoundEvents.STONE_BREAK;
    }

    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Item item = stack.getItem();
        if (item == Items.AIR && this.getTarget() == null) {
            ItemStack dropStack = new ItemStack(FSItems.BLUE_POPIM.get()); new ItemStack(FSItems.ORANGE_POPIM.get()); new ItemStack(FSItems.RED_POPIM.get());
            ItemEntity itementity = new ItemEntity(this.level(), this.getX(), this.getY() + 1.0D, this.getZ(), dropStack);
            this.level().addFreshEntity(itementity);
            this.discard();
            return InteractionResult.SUCCESS;
        } else {
            return super.mobInteract(player, hand);
        }
    }

    @Override
    public boolean isFood(ItemStack itemStack) {
        return false;
    }

    @Override
    public @Nullable AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return null;
    }
}
