package com.frostwizard4.Neutrino.effects;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;

public class SnakePoisonStatusEffect extends StatusEffect {
  public SnakePoisonStatusEffect() {
    super(StatusEffectCategory.HARMFUL, 6315019);
  }
 
  @Override
  public boolean canApplyUpdateEffect(int duration, int amplifier) {
    return true;
  }

  @Override
  public void applyUpdateEffect(LivingEntity entity, int amplifier) {
    if (entity instanceof PlayerEntity) {
      if(entity.getHealth() - 0.5 > 0) {
        ((PlayerEntity) entity).damage(DamageSource.MAGIC, 0.5F);
      }
    }
  }
}