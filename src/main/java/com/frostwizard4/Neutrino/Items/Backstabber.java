package com.frostwizard4.Neutrino.Items;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

import static com.frostwizard4.Neutrino.SoundRegister.HARVESTER_ACTIVATE;

public class Backstabber extends SwordItem {
    public Backstabber(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
}

