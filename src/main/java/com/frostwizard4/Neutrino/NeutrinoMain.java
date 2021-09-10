package com.frostwizard4.Neutrino;

import com.frostwizard4.Neutrino.Artifacts.*;
import com.frostwizard4.Neutrino.Blocks.GlassDoor;
import com.frostwizard4.Neutrino.Blocks.GlassTrapDoor;
import com.frostwizard4.Neutrino.Items.Backstabber;
import com.frostwizard4.Neutrino.Items.DaggerToolMaterial;
import com.frostwizard4.Neutrino.Items.GoatHorn;
import com.frostwizard4.Neutrino.Slabs.CraftingSlab;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeEffects;
import net.minecraft.world.biome.GenerationSettings;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CaveSurfaceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;
import org.jetbrains.annotations.NotNull;

import static com.frostwizard4.Neutrino.SoundRegister.*;
import static net.minecraft.world.gen.feature.ConfiguredFeatures.MOSS_VEGETATION;


public class NeutrinoMain implements ModInitializer {

    public static final Block HALF_FULL_BOOKSHELF = new Block(FabricBlockSettings.of(Material.WOOD).strength(1.5F, 1.5F).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES));


    public static final GlassDoor GLASS_DOOR = new GlassDoor(FabricBlockSettings.of(Material.GLASS).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final GlassTrapDoor GLASS_TRAPDOOR = new GlassTrapDoor(FabricBlockSettings.of(Material.GLASS).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque());

    public static final ItemGroup NEUTRINO_GROUP = FabricItemGroupBuilder.create(new Identifier("neutrino", "neutrino_group")).icon(() -> new ItemStack(HALF_FULL_BOOKSHELF)).build();
    public static final ItemGroup NEUTRINO_DUNGEONS_GROUP = FabricItemGroupBuilder.create(new Identifier("neutrino", "neutrino_dungeons_group")).icon(() -> new ItemStack(NeutrinoMain.LIGHTNING_ROD_ARTIFACT)).build();

    public static final SlabBlock DIRT_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).strength(1.5f, 1.5f).sounds(BlockSoundGroup.GRASS));
    public static final SlabBlock GRAVEL_SLAB = new SlabBlock(FabricBlockSettings.of(Material.AGGREGATE).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GRAVEL));
    public static final SlabBlock SAND_SLAB = new SlabBlock(FabricBlockSettings.of(Material.AGGREGATE).strength(0.3f, 0.3f).sounds(BlockSoundGroup.SAND));
    public static final CraftingSlab CRAFTING_SLAB = new CraftingSlab(FabricBlockSettings.of(Material.WOOD).strength(1.5f, 1.5f).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES));

    public static final Backstabber BACKSTABBER = new Backstabber(DaggerToolMaterial.INSTANCE, 4, 7, new FabricItemSettings().group(NEUTRINO_DUNGEONS_GROUP).fireproof().rarity(Rarity.RARE));
    public static final EnchantersTomeArtifact ENCHANTERS_TOME = new EnchantersTomeArtifact(new FabricItemSettings().group(NEUTRINO_DUNGEONS_GROUP).rarity(Rarity.RARE));
    public static final HarvesterArtifact HARVESTER = new HarvesterArtifact(new FabricItemSettings().group(NEUTRINO_DUNGEONS_GROUP).rarity(Rarity.RARE));
    public static final DeathCapArtifact DEATH_CAP_MUSHROOM = new DeathCapArtifact(new FabricItemSettings().group(NEUTRINO_DUNGEONS_GROUP).rarity(Rarity.UNCOMMON));
    public static final LightningRodArtifact LIGHTNING_ROD_ARTIFACT = new LightningRodArtifact(new FabricItemSettings().group(NEUTRINO_DUNGEONS_GROUP).rarity(Rarity.RARE));
    public static final UpdraftTomeArtifact UPDRAFT_TOME = new UpdraftTomeArtifact(new FabricItemSettings().group(NEUTRINO_DUNGEONS_GROUP).rarity(Rarity.RARE));
    public static final SoulHealerArtifact SOUL_HEALER = new SoulHealerArtifact(new FabricItemSettings().group(NEUTRINO_DUNGEONS_GROUP).rarity(Rarity.RARE));
    public static final SoulPouchItem SOUL_POUCH = new SoulPouchItem(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.EPIC));
    public static final GoatHorn GOAT_HORN = new GoatHorn(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.COMMON));

    /*
    public static final Block DUST_BLOCK = new MossBlock(FabricBlockSettings.of(Material.MOSS_BLOCK).strength(0.3f, 0.3f).sounds(BlockSoundGroup.MOSS_BLOCK));
    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<FC, ?> configuredFeature) {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new Identifier("neutrino", id), configuredFeature);
    }
    private static final ConfiguredSurfaceBuilder<TernarySurfaceConfig> DUST_CAVE_BUILDER = SurfaceBuilder.DEFAULT.withConfig(SurfaceBuilder.GRASS_CONFIG);
    public static final RegistryKey<Biome> DUST_CAVE_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("neutrino", "dust_cave"));


    public static final ConfiguredFeature<VegetationPatchFeatureConfig, ?> DUST_PATCH = register("dust_patch", Feature.VEGETATION_PATCH.configure(new VegetationPatchFeatureConfig(BlockTags.MOSS_REPLACEABLE.getId(), new SimpleBlockStateProvider(DUST_BLOCK.getDefaultState()), () -> MOSS_VEGETATION, VerticalSurfaceType.FLOOR, ConstantIntProvider.create(1), 0.0F, 5, 0.8F, UniformIntProvider.create(4, 7), 0.3F)));
    public static final ConfiguredFeature<?, ?> DUST_CAVES_VEGETATION = register("dust_caves_vegetation", DUST_PATCH.decorate(Decorator.CAVE_SURFACE.configure(new CaveSurfaceDecoratorConfig(VerticalSurfaceType.FLOOR, 12)).range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.getBottom(), YOffset.fixed(60))))).spreadHorizontally()).repeat(40);

    public static void addDustCavesDecoration(GenerationSettings.@NotNull Builder builder) {
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.LUSH_CAVES_CEILING_VEGETATION);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.CAVE_VINES);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.LUSH_CAVES_CLAY);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, DUST_CAVES_VEGETATION);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.ROOTED_AZALEA_TREES);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.SPORE_BLOSSOM);
        builder.feature(GenerationStep.Feature.VEGETAL_DECORATION, ConfiguredFeatures.CLASSIC_VINES_CAVE_FEATURE);
    }
    private static final Biome DUST_CAVE = createDustCave();

    public static Biome createDustCave() {
        SpawnSettings.Builder builder = new SpawnSettings.Builder();
        DefaultBiomeFeatures.addBatsAndMonsters(builder);
        GenerationSettings.Builder builder2 = (new GenerationSettings.Builder()).surfaceBuilder(DUST_CAVE_BUILDER);
        DefaultBiomeFeatures.addDefaultUndergroundStructures(builder2);
        builder2.structureFeature(ConfiguredStructureFeatures.RUINED_PORTAL);
        DefaultBiomeFeatures.addLandCarvers(builder2);
        DefaultBiomeFeatures.addDefaultLakes(builder2);
        DefaultBiomeFeatures.addAmethystGeodes(builder2);
        DefaultBiomeFeatures.addDungeons(builder2);
        DefaultBiomeFeatures.addPlainsTallGrass(builder2);
        DefaultBiomeFeatures.addMineables(builder2);
        DefaultBiomeFeatures.addDefaultOres(builder2);
        DefaultBiomeFeatures.addClayOre(builder2);
        DefaultBiomeFeatures.addDefaultDisks(builder2);
        NeutrinoMain.addDustCavesDecoration(builder2);
        return (new Biome.Builder())
                .precipitation(Biome.Precipitation.RAIN)
                .category(Biome.Category.UNDERGROUND)
                .depth(0.1F)
                .scale(0.2F)
                .temperature(0.5F)
                .downfall(0.5F)
                .effects((new BiomeEffects.Builder())
                        .waterColor(4159204)
                        .waterFogColor(329011)
                        .fogColor(12638463)
                        .skyColor(0x77adff).build())
                .spawnSettings(builder.build())
                .generationSettings(builder2.build())
                .build();
    }//Dust Cave Code
    */
    //TODO, add Invisible Item Frames
    //TODO, add more items from Minecraft Dungeons
    @Override
    public void onInitialize() {

        Registry.register(Registry.BLOCK, new Identifier("neutrino", "half_full_bookshelf"), HALF_FULL_BOOKSHELF);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "half_full_bookshelf"), new BlockItem(HALF_FULL_BOOKSHELF, new FabricItemSettings().group(NEUTRINO_GROUP)));


        Registry.register(Registry.ITEM, new Identifier("neutrino", "glass_door"), new BlockItem(GLASS_DOOR, new FabricItemSettings().group(NEUTRINO_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier("neutrino", "glass_door"), GLASS_DOOR);

        Registry.register(Registry.ITEM, new Identifier("neutrino", "glass_trapdoor"), new BlockItem(GLASS_TRAPDOOR, new FabricItemSettings().group(NEUTRINO_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier("neutrino", "glass_trapdoor"), GLASS_TRAPDOOR);

        Registry.register(Registry.ITEM, new Identifier("neutrino", "dirt_slab"), new BlockItem(DIRT_SLAB, new FabricItemSettings().group(NEUTRINO_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier("neutrino", "dirt_slab"), DIRT_SLAB);

        Registry.register(Registry.ITEM, new Identifier("neutrino", "gravel_slab"), new BlockItem(GRAVEL_SLAB, new FabricItemSettings().group(NEUTRINO_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier("neutrino", "gravel_slab"), GRAVEL_SLAB);

        Registry.register(Registry.ITEM, new Identifier("neutrino", "sand_slab"), new BlockItem(SAND_SLAB, new FabricItemSettings().group(NEUTRINO_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier("neutrino", "sand_slab"), SAND_SLAB);

        Registry.register(Registry.ITEM, new Identifier("neutrino", "crafting_slab"), new BlockItem(CRAFTING_SLAB, new FabricItemSettings().group(NEUTRINO_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier("neutrino", "crafting_slab"), CRAFTING_SLAB);

        /*
        Registry.register(Registry.ITEM, new Identifier("neutrino", "dust_block"), new BlockItem(DUST_BLOCK, new FabricItemSettings().group(NEUTRINO_GROUP)));
        Registry.register(Registry.BLOCK, new Identifier("neutrino", "dust_block"), DUST_BLOCK);
        */
        Registry.register(Registry.ITEM, new Identifier("neutrino", "backstabber"), BACKSTABBER);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "enchanters_tome"), ENCHANTERS_TOME);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "harvester"), HARVESTER);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "death_cap_mushroom"), DEATH_CAP_MUSHROOM);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "lightning_rod_artifact"), LIGHTNING_ROD_ARTIFACT);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "updraft_tome"), UPDRAFT_TOME);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "soul_healer"), SOUL_HEALER);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "soul_pouch"), SOUL_POUCH);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "goat_horn"), GOAT_HORN);

        /*
        Registry.register(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, new Identifier("neutrino", "dust_cave"), DUST_CAVE_BUILDER);
        Registry.register(BuiltinRegistries.BIOME, DUST_CAVE_KEY.getValue(), DUST_CAVE);
        */
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.BASTION_TREASURE_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.SOUL_POUCH));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.WOODLAND_MANSION_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.SOUL_HEALER));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.HERO_OF_THE_VILLAGE_CLERIC_GIFT_GAMEPLAY.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.DEATH_CAP_MUSHROOM));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.STRONGHOLD_LIBRARY_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.ENCHANTERS_TOME));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.HERO_OF_THE_VILLAGE_LIBRARIAN_GIFT_GAMEPLAY.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.UPDRAFT_TOME));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.ABANDONED_MINESHAFT_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.HARVESTER));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.END_CITY_TREASURE_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.LIGHTNING_ROD_ARTIFACT));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.VILLAGE_WEAPONSMITH_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.GOAT_HORN));
                table.pool(poolBuilder);
            }
        });

        /*
        OverworldBiomes.addContinentalBiome(DUST_CAVE_KEY, OverworldClimate.TEMPERATE, 2D);
        OverworldBiomes.addContinentalBiome(DUST_CAVE_KEY, OverworldClimate.COOL, 2D);
        */
        Registry.register(Registry.SOUND_EVENT, ENCHANTERS_TOME_ACTIVATE_ID, ENCHANTERS_TOME_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, DAGGER_STAB_ID, DAGGER_STAB);
        Registry.register(Registry.SOUND_EVENT, HARVESTER_ACTIVATE_ID, HARVESTER_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, LIGHTNING_ROD_ACTIVATE_ID, LIGHTNING_ROD_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, UPDRAFT_TOME_ACTIVATE_ID, UPDRAFT_TOME_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, SOUL_HEALER_ACTIVATE_ID, SOUL_HEALER_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, WAR_HORN_USE_ID, WAR_HORN_USE);

    }
}

