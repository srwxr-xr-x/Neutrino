package com.frostwizard4.Neutrino.Items;

import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

public class RustySwordMaterial implements ToolMaterial {
    public static final RustySwordMaterial INSTANCE = new RustySwordMaterial();

    @Override
    public int getDurability() {
        return 225;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return 0.5F;
    }

    @Override
    public float getAttackDamage() {
        return 1.5F;
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
        return Ingredient.ofItems(Items.IRON_NUGGET);

    }
}
