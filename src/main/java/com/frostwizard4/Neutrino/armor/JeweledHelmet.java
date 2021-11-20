package com.frostwizard4.Neutrino.armor;

import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class JeweledHelmet extends ArmorItem {
    public JeweledHelmet(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }
    public boolean hasGlint(ItemStack stack) {
        return true;
    }
}
