package com.frostwizard4.Neutrino.registry;

import com.frostwizard4.Neutrino.misc.FarOreDecorator;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.HeightmapDecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;

public class OreRegistry {
    private static final ConfiguredFeature<?, ?> ORE_TANZANITE_OVERWORLD = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,
                    BlockRegistry.TANZANITE.getDefaultState(),
                    3)) // 3
            .range(new RangeDecoratorConfig(
                    // You can also use one of the other height providers if you don't want a uniform distribution
                    UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(16)))) // Inclusive min and max height
            .spreadHorizontally()
            .applyChance(1)
            .decorate(FarOreDecorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR_WG)))
            .repeat(1); // Number of veins per chunk (1)
    public static void init() {
        RegistryKey<ConfiguredFeature<?, ?>> oreTanziniteOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("neutrino", "ore_tanzinite_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreTanziniteOverworld.getValue(), ORE_TANZANITE_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreTanziniteOverworld);

    }
}
