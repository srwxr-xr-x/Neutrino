package com.frostwizard4.Neutrino.registry;

import com.frostwizard4.Neutrino.entity.DuckEntity;
import com.frostwizard4.Neutrino.entity.RatEntity;
import com.frostwizard4.Neutrino.entity.WitherlingEntity;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.DefaultBiomeCreator;

import static com.frostwizard4.Neutrino.NeutrinoMain.*;

public class MiscEntityRegistry {
    public static void init() {
        FabricDefaultAttributeRegistry.register(com.frostwizard4.Neutrino.entity.EntityRegistry.RAT, RatEntity.createRatAttributes());
        FabricDefaultAttributeRegistry.register(com.frostwizard4.Neutrino.entity.EntityRegistry.DUCK, DuckEntity.createDuckAttributes());
        FabricDefaultAttributeRegistry.register(com.frostwizard4.Neutrino.entity.EntityRegistry.WITHERLING, WitherlingEntity.createWitherlingAttributes());

        BiomeModifications.addSpawn(BiomeSelectors.categories(DefaultBiomeCreator.createSoulSandValley().getCategory()), SpawnGroup.MONSTER, com.frostwizard4.Neutrino.entity.EntityRegistry.WITHERLING, 1, 1, 1);
        if(nConfig.isRatOn()) {
            BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.MONSTER, com.frostwizard4.Neutrino.entity.EntityRegistry.RAT, 5, 1, 1);
        }
        if(nConfig.isDuckOn()) {
            BiomeModifications.addSpawn(BiomeSelectors.foundInOverworld(), SpawnGroup.CREATURE, com.frostwizard4.Neutrino.entity.EntityRegistry.DUCK, 100, 1, 4);
        }

    }
}
