package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.PlayerEntityAccess;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityDeathMixin extends Entity {
    @Shadow public abstract void setSprinting(boolean sprinting);

    public LivingEntityDeathMixin(EntityType<?> type, World world) {
        super(type,world);
    }

    @Inject(method = "onDeath", at = @At("HEAD"))
    protected void onDeathCalled(DamageSource source, CallbackInfo ci) {
        Entity attacker = source.getAttacker();

        if(attacker instanceof PlayerEntity) {
            ((PlayerEntityAccess) attacker).neutrino$setPowerCount(((PlayerEntityAccess) attacker).neutrino$getPowerCount() + 1000);
        }
    }
}