package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.registry.StatusEffectRegistry;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.item.ItemStack;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MilkBucketItem.class)
public abstract class MilkBucketItemMixin {
    private boolean neutrino$addEffect = false;
    @Inject(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;clearStatusEffects()Z"))
    private void neutrino$checkEffect(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        neutrino$addEffect = user.hasStatusEffect(StatusEffectRegistry.SNAKE_POISON);
    }
    @Inject(method = "finishUsing", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;clearStatusEffects()Z", shift = At.Shift.AFTER))
    private void neutrino$setEffect(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        if(neutrino$addEffect) {
            user.addStatusEffect(new StatusEffectInstance(StatusEffectRegistry.SNAKE_POISON, 100000));
        }
    }
}
