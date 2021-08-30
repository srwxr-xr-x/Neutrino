package com.frostwizard4.Neutrino.Items;

import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

import static com.frostwizard4.Neutrino.SoundRegister.HARVESTER_ACTIVATE;

public class Backstabber extends SwordItem {
    public Backstabber(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }
    @Override
    public boolean onClicked(net.minecraft.item.ItemStack stack, net.minecraft.item.ItemStack otherStack, net.minecraft.screen.slot.Slot slot, net.minecraft.util.ClickType clickType, net.minecraft.entity.player.PlayerEntity player, net.minecraft.inventory.StackReference cursorStackReference) {
        player.playSound(HARVESTER_ACTIVATE, 1.0F, 1.0F);
        return true;
    }
}

