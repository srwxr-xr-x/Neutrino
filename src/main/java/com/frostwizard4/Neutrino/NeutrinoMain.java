package com.frostwizard4.Neutrino;

import com.frostwizard4.Neutrino.Artifacts.*;
import com.frostwizard4.Neutrino.Blocks.GlassDoor;
import com.frostwizard4.Neutrino.Blocks.GlassTrapDoor;
import com.frostwizard4.Neutrino.Items.Backstabber;
import com.frostwizard4.Neutrino.Items.DaggerToolMaterial;
import com.frostwizard4.Neutrino.Items.GoatHorn;
import com.frostwizard4.Neutrino.Slabs.CraftingSlab;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static com.frostwizard4.Neutrino.SoundRegister.*;
import static net.minecraft.util.registry.Registry.register;


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

    //TODO, add Invisible Item Frames
    //TODO, add more items from Minecraft Dungeons

    @Override
    public void onInitialize() {

        register(Registry.BLOCK, new Identifier("neutrino", "half_full_bookshelf"), HALF_FULL_BOOKSHELF);
        register(Registry.ITEM, new Identifier("neutrino", "half_full_bookshelf"), new BlockItem(HALF_FULL_BOOKSHELF, new FabricItemSettings().group(NEUTRINO_GROUP)));

        register(Registry.ITEM, new Identifier("neutrino", "glass_door"), new BlockItem(GLASS_DOOR, new FabricItemSettings().group(NEUTRINO_GROUP)));
        register(Registry.BLOCK, new Identifier("neutrino", "glass_door"), GLASS_DOOR);

        register(Registry.ITEM, new Identifier("neutrino", "glass_trapdoor"), new BlockItem(GLASS_TRAPDOOR, new FabricItemSettings().group(NEUTRINO_GROUP)));
        register(Registry.BLOCK, new Identifier("neutrino", "glass_trapdoor"), GLASS_TRAPDOOR);

        register(Registry.ITEM, new Identifier("neutrino", "dirt_slab"), new BlockItem(DIRT_SLAB, new FabricItemSettings().group(NEUTRINO_GROUP)));
        register(Registry.BLOCK, new Identifier("neutrino", "dirt_slab"), DIRT_SLAB);

        register(Registry.ITEM, new Identifier("neutrino", "gravel_slab"), new BlockItem(GRAVEL_SLAB, new FabricItemSettings().group(NEUTRINO_GROUP)));
        register(Registry.BLOCK, new Identifier("neutrino", "gravel_slab"), GRAVEL_SLAB);

        register(Registry.ITEM, new Identifier("neutrino", "sand_slab"), new BlockItem(SAND_SLAB, new FabricItemSettings().group(NEUTRINO_GROUP)));
        register(Registry.BLOCK, new Identifier("neutrino", "sand_slab"), SAND_SLAB);

        register(Registry.ITEM, new Identifier("neutrino", "crafting_slab"), new BlockItem(CRAFTING_SLAB, new FabricItemSettings().group(NEUTRINO_GROUP)));
        register(Registry.BLOCK, new Identifier("neutrino", "crafting_slab"), CRAFTING_SLAB);

        register(Registry.ITEM, new Identifier("neutrino", "backstabber"), BACKSTABBER);
        register(Registry.ITEM, new Identifier("neutrino", "enchanters_tome"), ENCHANTERS_TOME);
        register(Registry.ITEM, new Identifier("neutrino", "harvester"), HARVESTER);
        register(Registry.ITEM, new Identifier("neutrino", "death_cap_mushroom"), DEATH_CAP_MUSHROOM);
        register(Registry.ITEM, new Identifier("neutrino", "lightning_rod_artifact"), LIGHTNING_ROD_ARTIFACT);
        register(Registry.ITEM, new Identifier("neutrino", "updraft_tome"), UPDRAFT_TOME);
        register(Registry.ITEM, new Identifier("neutrino", "soul_healer"), SOUL_HEALER);
        register(Registry.ITEM, new Identifier("neutrino", "soul_pouch"), SOUL_POUCH);
        register(Registry.ITEM, new Identifier("neutrino", "goat_horn"), GOAT_HORN);


        FabricModelPredicateProviderRegistry.register(NeutrinoMain.SOUL_POUCH, new Identifier("filled"), (stack, world, entity, seed) -> {if (entity != null) {if (((PlayerEntityAccess) entity).neutrino$getSoulPouchCount() == 3000) {return 1.0f;} else {return 0.0f;}} else {return 0;}});

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

        register(Registry.SOUND_EVENT, ENCHANTERS_TOME_ACTIVATE_ID, ENCHANTERS_TOME_ACTIVATE);
        register(Registry.SOUND_EVENT, DAGGER_STAB_ID, DAGGER_STAB);
        register(Registry.SOUND_EVENT, HARVESTER_ACTIVATE_ID, HARVESTER_ACTIVATE);
        register(Registry.SOUND_EVENT, LIGHTNING_ROD_ACTIVATE_ID, LIGHTNING_ROD_ACTIVATE);
        register(Registry.SOUND_EVENT, UPDRAFT_TOME_ACTIVATE_ID, UPDRAFT_TOME_ACTIVATE);
        register(Registry.SOUND_EVENT, SOUL_HEALER_ACTIVATE_ID, SOUL_HEALER_ACTIVATE);
        register(Registry.SOUND_EVENT, WAR_HORN_USE_ID, WAR_HORN_USE);

        BlockRenderLayerMap.INSTANCE.putBlock(GLASS_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GLASS_TRAPDOOR, RenderLayer.getCutout());
    }
}

