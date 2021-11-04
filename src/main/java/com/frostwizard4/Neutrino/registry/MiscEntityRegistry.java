package com.frostwizard4.Neutrino.registry;

import com.frostwizard4.Neutrino.entity.*;
import com.frostwizard4.Neutrino.misc.Config;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.DefaultBiomeCreator;

public class MiscEntityRegistry {
    public static void init() {
        FabricDefaultAttributeRegistry.register(EntityRegistry.RAT, RatEntity.createRatAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.DUCK, DuckEntity.createDuckAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.WITHERLING, WitherlingEntity.createWitherlingAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.ALPACA, AlpacaEntity.createAlpacaAttributes());
        FabricDefaultAttributeRegistry.register(EntityRegistry.DESERT_SERPENT, DesertSerpentEntity.createSerpentAttributes());

        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.EXTREME_HILLS), SpawnGroup.CREATURE, EntityRegistry.ALPACA, 5, 4, 6);
        BiomeModifications.addSpawn(BiomeSelectors.categories(Biome.Category.DESERT), SpawnGroup.MONSTER, EntityRegistry.DESERT_SERPENT, 5, 1, 1);
        BiomeModifications.addSpawn(BiomeSelectors.categories(DefaultBiomeCreator.createSoulSandValley().getCategory()), SpawnGroup.MONSTER, com.frostwizard4.Neutrino.entity.EntityRegistry.WITHERLING, 1, 1, 1);
        if(Config.lines.get(3).endsWith("On")) {
            BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.MONSTER, com.frostwizard4.Neutrino.entity.EntityRegistry.RAT, 5, 1, 1);
        }
        if(Config.lines.get(4).endsWith("On")) {
            BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, com.frostwizard4.Neutrino.entity.EntityRegistry.DUCK, 10, 1, 4);
        }

    }
}
