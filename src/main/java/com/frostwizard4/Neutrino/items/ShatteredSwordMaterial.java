package com.frostwizard4.Neutrino.items;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class ShatteredSwordMaterial implements ToolMaterial {
    public static final ShatteredSwordMaterial INSTANCE = new ShatteredSwordMaterial();

    @Override
    public int getDurability() {
        return 275;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0.75F;
    }

    @Override
    public float getAttackDamage() {
        return 2.0F;
    }

    @Override
    public int getMiningLevel() {
        return 1;
    }

    @Override
    public int getEnchantability() {
        return 3;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.DIAMOND);

    }
}
