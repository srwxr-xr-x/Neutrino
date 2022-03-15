package com.frostwizard4.Neutrino.registry;

import com.frostwizard4.Neutrino.entity.*;
import com.frostwizard4.Neutrino.mixin.GetCategoryInvoker;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.TheNetherBiomeCreator;

import static com.frostwizard4.Neutrino.misc.ConfigHolder.config;

public class MiscEntityRegistry {
    public static void init() {
        FabricDefaultAttributeRegistry.register(EntityRegistry.RAT, RatEntity.createRatAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.DUCK, DuckEntity.createDuckAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.WITHERLING, WitherlingEntity.createWitherlingAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.ALPACA, AlpacaEntity.createAlpacaAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.DESERT_SERPENT, DesertSerpentEntity.createSerpentAttributes());

        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.EXTREME_HILLS), SpawnGroup.CREATURE, EntityRegistry.ALPACA, 5, 4, 6);
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.DESERT), SpawnGroup.MONSTER, EntityRegistry.DESERT_SERPENT, 5, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.categories(((GetCategoryInvoker)(Object)TheNetherBiomeCreator.createSoulSandValley()).invokeGetCategory()), SpawnGroup.MONSTER, EntityRegistry.WITHERLING, 1, 1, 1);
        if(config.ratSpawning) {
            String ratSpawnRate = String.valueOf(config.ratSpawnRate);
            BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.MONSTER, EntityRegistry.RAT, Integer.parseInt(ratSpawnRate.replaceAll("[^0-9]", "")), 1, 1);
        }
        if(config.duckSpawning) {
            String duckSpawnRate = String.valueOf(config.duckSpawnRate);
            BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, EntityRegistry.DUCK,  Integer.parseInt(duckSpawnRate.replaceAll("[^0-9]", "")), 1, 4);
        }

    }
}
