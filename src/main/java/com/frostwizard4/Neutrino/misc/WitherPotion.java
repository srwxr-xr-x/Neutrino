
package com.frostwizard4.Neutrino.misc;

import com.frostwizard4.Neutrino.mixin.BrewingRecipeRegistryAccessor;
import com.frostwizard4.Neutrino.registry.ItemRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.potion.Potion;
import net.minecraft.potion.Potions;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

public final class WitherPotion {

    private WitherPotion() {}

    private static final Collection<RecipeToInit> RECIPES = new ArrayList<RecipeToInit>();


    public static final Potion WITHER = register("wither", new Potion(new StatusEffectInstance(StatusEffects.WITHER, 45 * 20)), ItemRegistry.WITHERING_HEART, Potions.AWKWARD);
    public static final Potion LONG_WITHER = register("long_wither", new Potion(new StatusEffectInstance(StatusEffects.WITHER, 90 * 20)));
    public static final Potion STRONG_WITHER = register("strong_wither", new Potion(new StatusEffectInstance(StatusEffects.WITHER, 450, 1)));

    public static void init() {
        RECIPES.forEach(RecipeToInit::init);
    }

    private static Potion register(String id, Potion potion) {
        return Registry.register(Registry.POTION, new Identifier("neutrino", id), potion);
    }

    private static Potion register(String id, Potion potion, Item ingredient, Potion from) {
        RECIPES.add(new RecipeToInit(from, ingredient, potion));
        return Registry.register(Registry.POTION, new Identifier("neutrino", id), potion);
    }

    private static void mapPotions(Potion in, Item ingredient, Potion result) {
        Identifier potionInId = Registry.POTION.getId(in);
        Identifier potionOutId = Registry.POTION.getId(result);
        Optional<Potion> inLong = Registry.POTION.getOrEmpty(new Identifier(potionInId.getNamespace(), "long_" + potionInId.getPath()));
        Optional<Potion> inStrong = Registry.POTION.getOrEmpty(new Identifier(potionInId.getNamespace(), "strong_" + potionInId.getPath()));
        Optional<Potion> outLong = Registry.POTION.getOrEmpty(new Identifier(potionOutId.getNamespace(), "long_" + potionOutId.getPath()));
        Optional<Potion> outStrong = Registry.POTION.getOrEmpty(new Identifier(potionOutId.getNamespace(), "strong_" + potionOutId.getPath()));
        if(outLong.isPresent() && inLong.isPresent()) {
            BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(inLong.get(), ingredient, outLong.get());
        }
        if(outStrong.isPresent() && inStrong.isPresent()) {
            BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(inStrong.get(), ingredient, outStrong.get());
        }
        BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(in, ingredient, result);
    }

    private static void variantRecipes(Potion potion) {
        Identifier id = Registry.POTION.getId(potion);
        Optional<Potion> lengthy = Registry.POTION.getOrEmpty(new Identifier(id.getNamespace(), "long_" + id.getPath()));
        Optional<Potion> strong = Registry.POTION.getOrEmpty(new Identifier(id.getNamespace(), "strong_" + id.getPath()));
        if(lengthy.isPresent()) {
            BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(potion, Items.REDSTONE, lengthy.get());
        }
        if(strong.isPresent()) {
            BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(potion, Items.GLOWSTONE_DUST, strong.get());
        }
    }

    private static class RecipeToInit {

        private final Potion in;
        private final Item ingredient;
        private final Potion result;

        private RecipeToInit(Potion in, Item ingredient, Potion result) {
            this.in = in;
            this.ingredient = ingredient;
            this.result = result;
        }

        public void init() {
            mapPotions(in, ingredient, result);
            variantRecipes(result);
        }
    }
}
