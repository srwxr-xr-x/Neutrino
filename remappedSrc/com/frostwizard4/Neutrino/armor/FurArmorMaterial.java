package com.frostwizard4.Neutrino.armor;

import com.frostwizard4.Neutrino.registry.ItemRegistry;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

public class FurArmorMaterial implements ArmorMaterial {
	private static final int[] BASE_DURABILITY = new int[] {13, 15, 16, 11};
	private static final int[] PROTECTION_VALUES = new int[] {1, 2, 3, 1};
 
	@Override
	public int getDurability(EquipmentSlot slot) {
		return BASE_DURABILITY[slot.getEntitySlotId()] * 10;
	}
 
	@Override
	public int getProtectionAmount(EquipmentSlot slot) {
		return PROTECTION_VALUES[slot.getEntitySlotId()];
	}
 
	@Override
	public int getEnchantability() {
		return 0;
	}
 
	@Override
	public SoundEvent getEquipSound() {
		return SoundEvents.ITEM_ARMOR_EQUIP_ELYTRA;
	}
 
	@Override
	public Ingredient getRepairIngredient() {
		return Ingredient.ofItems(ItemRegistry.ALPACA_FUR);
	}
 
	@Override
	public String getName() {
		// Must be all lowercase
		return "alpaca_fur";
	}
 
	@Override
	public float getToughness() {
		return 0.0F;
	}
 
	@Override
	public float getKnockbackResistance() {
		return 0.0F;
	}
}