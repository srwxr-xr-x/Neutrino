package com.frostwizard4.Neutrino.registry;

import com.frostwizard4.Neutrino.entity.*;
import com.frostwizard4.Neutrino.misc.Config;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.TheNetherBiomeCreator;

public class MiscEntityRegistry {
    public static void init() {
        FabricDefaultAttributeRegistry.register(EntityRegistry.RAT, RatEntity.createRatAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.DUCK, DuckEntity.createDuckAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.WITHERLING, WitherlingEntity.createWitherlingAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.ALPACA, AlpacaEntity.createAlpacaAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.DESERT_SERPENT, DesertSerpentEntity.createSerpentAttributes());

        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.EXTREME_HILLS), SpawnGroup.CREATURE, EntityRegistry.ALPACA, 5, 4, 6);
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.DESERT), SpawnGroup.MONSTER, EntityRegistry.DESERT_SERPENT, 5, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.categories(TheNetherBiomeCreator.createSoulSandValley().getCategory()), SpawnGroup.MONSTER, com.frostwizard4.Neutrino.entity.EntityRegistry.WITHERLING, 1, 1, 1);
        if(Config.lines.get(3).endsWith("On")) {
            String ratSpawnRate = Config.lines.get(7);
            BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.MONSTER, com.frostwizard4.Neutrino.entity.EntityRegistry.RAT, Integer.parseInt(ratSpawnRate.replaceAll("[^0-9]", "")), 1, 1);
        }
        if(Config.lines.get(4).endsWith("On")) {
            String duckSpawnRate = Config.lines.get(8);
            BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, com.frostwizard4.Neutrino.entity.EntityRegistry.DUCK,  Integer.parseInt(duckSpawnRate.replaceAll("[^0-9]", "")), 1, 4);
        }

    }
}
