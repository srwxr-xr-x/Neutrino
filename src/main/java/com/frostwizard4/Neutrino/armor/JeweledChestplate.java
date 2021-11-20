package com.frostwizard4.Neutrino.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class JeweledChestplate extends ArmorItem {
    public JeweledChestplate(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
