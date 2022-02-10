package com.frostwizard4.Neutrino.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class JeweledBoots extends ArmorItem {
    public JeweledBoots(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
