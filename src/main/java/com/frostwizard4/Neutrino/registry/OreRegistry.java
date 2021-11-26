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
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import org.lwjgl.system.CallbackI;

public class OreRegistry {
    private static final FarOreDecorator FAR_ORE_DECORATOR = new FarOreDecorator(NopeDecoratorConfig.CODEC);
    private static final ConfiguredFeature<?, ?> ORE_TANZANITE_OVERWORLD = Feature.ORE
            .configure(new OreFeatureConfig(
                    OreFeatureConfig.Rules.STONE_ORE_REPLACEABLES,
                    BlockRegistry.TANZANITE.getDefaultState(),
                    3))
            .range(new RangeDecoratorConfig(
                    UniformHeightProvider.create(YOffset.aboveBottom(0), YOffset.fixed(16))))
            .decorate(FAR_ORE_DECORATOR.configure(NopeDecoratorConfig.INSTANCE))
            .spreadHorizontally()
            .repeat(1);
    public static void init() {
        RegistryKey<ConfiguredFeature<?, ?>> oreTanziniteOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY,
                new Identifier("neutrino", "ore_tanzinite_overworld"));
        Registry.register(Registry.DECORATOR, new Identifier("neutrino", "far_ore_decorator"), FAR_ORE_DECORATOR);
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreTanziniteOverworld.getValue(), ORE_TANZANITE_OVERWORLD);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreTanziniteOverworld);
    }
}
