package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.registry.ItemRegistry;
import net.minecraft.item.ElytraItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Wearable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ElytraItem.class)
public class ElytraItemMixin extends Item implements Wearable {

    public ElytraItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "canRepair", at = @At("RETURN"), cancellable = true)
    public void canRepair(ItemStack stack, ItemStack ingredient, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(cir.getReturnValue() || ingredient.isOf(ItemRegistry.DATURA_ESSENCE));
    }
}
