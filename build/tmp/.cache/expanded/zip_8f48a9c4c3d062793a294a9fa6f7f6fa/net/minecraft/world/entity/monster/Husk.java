package net.minecraft.world.entity.monster;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class Husk extends Zombie {
   public Husk(EntityType<? extends Husk> p_32889_, Level p_32890_) {
      super(p_32889_, p_32890_);
   }

   public static boolean checkHuskSpawnRules(EntityType<Husk> p_218997_, ServerLevelAccessor p_218998_, MobSpawnType p_218999_, BlockPos p_219000_, RandomSource p_219001_) {
      return checkMonsterSpawnRules(p_218997_, p_218998_, p_218999_, p_219000_, p_219001_) && (p_218999_ == MobSpawnType.SPAWNER || p_218998_.canSeeSky(p_219000_));
   }

   protected boolean isSunSensitive() {
      return false;
   }

   protected SoundEvent getAmbientSound() {
      return SoundEvents.HUSK_AMBIENT;
   }

   protected SoundEvent getHurtSound(DamageSource p_32903_) {
      return SoundEvents.HUSK_HURT;
   }

   protected SoundEvent getDeathSound() {
      return SoundEvents.HUSK_DEATH;
   }

   protected SoundEvent getStepSound() {
      return SoundEvents.HUSK_STEP;
   }

   public boolean doHurtTarget(Entity p_32892_) {
      boolean flag = super.doHurtTarget(p_32892_);
      if (flag && this.getMainHandItem().isEmpty() && p_32892_ instanceof LivingEntity) {
         float f = this.level().getCurrentDifficultyAt(this.blockPosition()).getEffectiveDifficulty();
         ((LivingEntity)p_32892_).addEffect(new MobEffectInstance(MobEffects.HUNGER, 140 * (int)f), this);
      }

      return flag;
   }

   protected boolean convertsInWater() {
      return true;
   }

   protected void doUnderWaterConversion() {
      this.convertToZombieType(EntityType.ZOMBIE);
      if (!this.isSilent()) {
         this.level().levelEvent((Player)null, 1041, this.blockPosition(), 0);
      }

   }

   protected ItemStack getSkull() {
      return ItemStack.EMPTY;
   }
}