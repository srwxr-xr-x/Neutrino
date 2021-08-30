package com.frostwizard4.Neutrino.Items;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class DaggerToolMaterial implements ToolMaterial {

    public static final DaggerToolMaterial INSTANCE = new DaggerToolMaterial();

    @Override
    public int getDurability() {
        return 1500;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 1;
    }

    @Override
    public float getAttackDamage() {
        return 7;
    }

    @Override
    public int getMiningLevel() {
        return 1;
    }

    @Override
    public int getEnchantability() {
        return 12;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.CRYING_OBSIDIAN);
    }
}
