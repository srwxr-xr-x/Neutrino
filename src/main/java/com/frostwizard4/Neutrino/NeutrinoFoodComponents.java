package com.frostwizard4.Neutrino;

import com.frostwizard4.Neutrino.artifacts.DeathCapArtifact;
import com.frostwizard4.Neutrino.items.DiamondApple;
import com.frostwizard4.Neutrino.items.DiamondCarrot;
import com.frostwizard4.Neutrino.items.EnchantedDiamondApple;
import com.frostwizard4.Neutrino.items.WoodApple;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

public class NeutrinoFoodComponents {

    public static final Item DIAMOND_APPLE = new DiamondApple(new Item.Settings()
            .group(ItemGroup.FOOD)
            .rarity(Rarity.RARE)
            .food(new FoodComponent.Builder()
                    .hunger(8)
                    .saturationModifier(1.2F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1000, 2), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 0), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1200, 1), 1.0F)
                    .alwaysEdible()
                    .build()
            )
    );
    public static final Item ENCHANTED_DIAMOND_APPLE = new EnchantedDiamondApple(new Item.Settings()
            .group(ItemGroup.FOOD)
            .rarity(Rarity.EPIC)
            .food(new FoodComponent.Builder()
                    .hunger(8)
                    .saturationModifier(1.3F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1400, 2), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1000, 3), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 6000, 1), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.FIRE_RESISTANCE, 6000, 2), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 2400, 3), 1.0F)
                    .alwaysEdible()
                    .build()
            )
    );
    public static final Item DIAMOND_CARROT = new DiamondCarrot(new Item.Settings()
            .group(ItemGroup.FOOD)
            .rarity(Rarity.EPIC)
            .food(new FoodComponent.Builder()
                    .hunger(8)
                    .saturationModifier(1.2F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.REGENERATION, 1000, 2), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.NIGHT_VISION, 1200, 0), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 1000, 1), 1.0F)
                    .alwaysEdible()
                    .build()
            )
    );
    public static final Item WOOD_APPLE = new WoodApple(new Item.Settings()
            .group(ItemGroup.FOOD)
            .rarity(Rarity.COMMON)
            .food(new FoodComponent.Builder()
                    .hunger(8)
                    .saturationModifier(1.2F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1000, 2), 1.0F)
                    .alwaysEdible()
                    .build()
            )
    );
    public static final Item STONE_APPLE = new WoodApple(new Item.Settings()
            .group(ItemGroup.FOOD)
            .rarity(Rarity.UNCOMMON)
            .food(new FoodComponent.Builder()
                    .hunger(8)
                    .saturationModifier(1.2F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.HASTE, 1000, 2), 1.0F)
                    .statusEffect(new StatusEffectInstance(StatusEffects.ABSORPTION, 1200, 0), 1.0F)
                    .alwaysEdible()
                    .build()
            )
    );
    public static final DeathCapArtifact DEATH_CAP_MUSHROOM = new DeathCapArtifact(new Item.Settings()
            .group(ItemGroup.FOOD)
            .rarity(Rarity.UNCOMMON)
            .food(new FoodComponent.Builder()
                    .hunger(8)
                    .saturationModifier(1.2F)
                    .alwaysEdible()
                    .build()
            )
    );

    public static void registerFoods() {
        Registry.register(Registry.ITEM, new Identifier("neutrino", "diamond_apple"), DIAMOND_APPLE);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "enchanted_diamond_apple"), ENCHANTED_DIAMOND_APPLE);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "diamond_carrot"), DIAMOND_CARROT);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "wood_apple"), WOOD_APPLE);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "stone_apple"), STONE_APPLE);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "death_cap_mushroom"), DEATH_CAP_MUSHROOM);

    }
}