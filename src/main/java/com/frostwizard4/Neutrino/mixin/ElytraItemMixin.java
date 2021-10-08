package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.NeutrinoMain;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(ElytraItem.class)
public class ElytraItemMixin extends Item implements Wearable {

    public ElytraItemMixin(Settings settings) {
        super(settings);
    }

    @Override
    public boolean canRepair(ItemStack stack, ItemStack ingredient) {
        return ingredient.isOf(Items.PHANTOM_MEMBRANE) || ingredient.isOf(NeutrinoMain.DATURA_ESSENCE);
    }
}
