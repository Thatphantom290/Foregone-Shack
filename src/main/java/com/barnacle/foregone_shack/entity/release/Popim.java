package com.barnacle.foregone_shack.entity.release;

import com.barnacle.foregone_shack.ForegoneShack;
import com.barnacle.foregone_shack.entity.base.FSMob;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class Popim extends FSMob {

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
                .add(Attributes.MOVEMENT_SPEED, 0.29D)
                .add(Attributes.FOLLOW_RANGE, 16.0D);
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

    @Override
    public ResourceLocation fallbackVariantTexture() {
        return ForegoneShack.location("textures/entity/popim/popim_red.png");
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
