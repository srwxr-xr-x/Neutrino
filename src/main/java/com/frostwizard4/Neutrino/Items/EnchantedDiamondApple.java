package com.frostwizard4.Neutrino.Items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class EnchantedDiamondApple extends Item {
    public EnchantedDiamondApple(Item.Settings settings) {
        super(settings);
    }

    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}