package com.frostwizard4.Neutrino.items;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class DaturaEssence extends Item {
    public DaturaEssence(Settings settings) {
        super(settings);
    }
    @Override
    public boolean hasGlint(ItemStack stack) {
        return true;
    }

}
