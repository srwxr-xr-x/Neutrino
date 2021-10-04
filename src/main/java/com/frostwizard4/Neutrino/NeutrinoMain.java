package com.frostwizard4.Neutrino;

import com.frostwizard4.Neutrino.Artifacts.*;
import com.frostwizard4.Neutrino.Blocks.GlassDoor;
import com.frostwizard4.Neutrino.Blocks.GlassTrapDoor;
import com.frostwizard4.Neutrino.Enchantments.LifeStealEnchantment;
import com.frostwizard4.Neutrino.Items.*;
import com.frostwizard4.Neutrino.Misc.NeutrinoConfig;
import com.frostwizard4.Neutrino.Slabs.CraftingSlab;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentLevelEntry;
import net.minecraft.item.*;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static com.frostwizard4.Neutrino.Misc.SoundRegister.*;


public class NeutrinoMain implements ModInitializer {

    public static final Block HALF_FULL_BOOKSHELF = new Block(FabricBlockSettings.of(Material.WOOD).strength(1.5F, 1.5F).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES));
    public static final GlassDoor GLASS_DOOR = new GlassDoor(FabricBlockSettings.of(Material.GLASS).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final GlassTrapDoor GLASS_TRAPDOOR = new GlassTrapDoor(FabricBlockSettings.of(Material.GLASS).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block DUNGEONS_POT = new Block(FabricBlockSettings.of(Material.STONE).strength(1F, 2.2F).sounds(BlockSoundGroup.STONE).nonOpaque().materialColor(DyeColor.BLACK));

    public static final ItemGroup NEUTRINO_GROUP = FabricItemGroupBuilder.create(new Identifier("neutrino", "neutrino_group")).icon(() -> new ItemStack(HALF_FULL_BOOKSHELF)).build();
    public static final ItemGroup NEUTRINO_DUNGEONS_GROUP = FabricItemGroupBuilder.create(new Identifier("neutrino", "neutrino_dungeons_group")).icon(() -> new ItemStack(NeutrinoMain.LIGHTNING_ROD_ARTIFACT)).build();

    public static final SlabBlock DIRT_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).strength(1.5f, 1.5f).sounds(BlockSoundGroup.GRASS));
    public static final SlabBlock GRAVEL_SLAB = new SlabBlock(FabricBlockSettings.of(Material.AGGREGATE).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GRAVEL));
    public static final SlabBlock SAND_SLAB = new SlabBlock(FabricBlockSettings.of(Material.AGGREGATE).strength(0.3f, 0.3f).sounds(BlockSoundGroup.SAND));
    public static final CraftingSlab CRAFTING_SLAB = new CraftingSlab(FabricBlockSettings.of(Material.WOOD).strength(1.5f, 1.5f).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES));

    public static final EnchantersTomeArtifact ENCHANTERS_TOME = new EnchantersTomeArtifact(new FabricItemSettings().group(NEUTRINO_DUNGEONS_GROUP).rarity(Rarity.RARE));
    public static final HarvesterArtifact HARVESTER = new HarvesterArtifact(new FabricItemSettings().group(NEUTRINO_DUNGEONS_GROUP).rarity(Rarity.RARE));
    public static final LightningRodArtifact LIGHTNING_ROD_ARTIFACT = new LightningRodArtifact(new FabricItemSettings().group(NEUTRINO_DUNGEONS_GROUP).rarity(Rarity.RARE));
    public static final UpdraftTomeArtifact UPDRAFT_TOME = new UpdraftTomeArtifact(new FabricItemSettings().group(NEUTRINO_DUNGEONS_GROUP).rarity(Rarity.RARE));
    public static final SoulHealerArtifact SOUL_HEALER = new SoulHealerArtifact(new FabricItemSettings().group(NEUTRINO_DUNGEONS_GROUP).rarity(Rarity.RARE));
    public static final SoulPouchItem SOUL_POUCH = new SoulPouchItem(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.EPIC));
    public static final GoatHorn GOAT_HORN = new GoatHorn(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.COMMON));
    public static final Enchantment LIFE_STEAL = Registry.register(Registry.ENCHANTMENT, new Identifier("neutrino", "life_steal"), new LifeStealEnchantment());
    public static final ToolItem RUSTY_SWORD = new SwordItem(RustySwordMaterial.INSTANCE, 1, -1.0F, new Item.Settings().group(NEUTRINO_GROUP));
    public static final ToolItem SHATTERED_SWORD = new SwordItem(ShatteredSwordMaterial.INSTANCE, 2, -1.5F, new Item.Settings().group(NEUTRINO_GROUP));
    public static final EmptyStaff EMPTY_STAFF = new EmptyStaff(new FabricItemSettings().group(NEUTRINO_GROUP));
    public static final EvokersStaff EVOKERS_STAFF = new EvokersStaff(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.EPIC));
    public static final Item GRAY_JEWEL = new Item(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.RARE));
    public static final VampiricStaff VAMPIRIC_STAFF = new VampiricStaff(ToolMaterials.STONE, 1, 3, new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.RARE));

    public static final NeutrinoConfig nConfig;
    static {
        AutoConfig.register(NeutrinoConfig.class, GsonConfigSerializer::new);
        nConfig = AutoConfig.getConfigHolder(NeutrinoConfig.class).getConfig();
    }

    //TODO Add Staff Variants of the EMPTY_STAFF
    @Override
    public void onInitialize() {
        VillagerInit.fillTradeData();
        NeutrinoFoodComponents.registerFoods();

        Registry.register(Registry.BLOCK, new Identifier("neutrino", "half_full_bookshelf"), HALF_FULL_BOOKSHELF);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "half_full_bookshelf"), new BlockItem(HALF_FULL_BOOKSHELF, new FabricItemSettings().group(NEUTRINO_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier("neutrino", "dungeons_pot"), DUNGEONS_POT);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "dungeons_pot"), new BlockItem(DUNGEONS_POT, new FabricItemSettings().group(NEUTRINO_GROUP)));

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

        Registry.register(Registry.ITEM, new Identifier("neutrino", "enchanters_tome"), ENCHANTERS_TOME);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "harvester"), HARVESTER);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "lightning_rod_artifact"), LIGHTNING_ROD_ARTIFACT);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "updraft_tome"), UPDRAFT_TOME);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "soul_healer"), SOUL_HEALER);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "soul_pouch"), SOUL_POUCH);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "goat_horn"), GOAT_HORN);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "rusty_sword"), RUSTY_SWORD);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "shattered_sword"), SHATTERED_SWORD);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "empty_staff"), EMPTY_STAFF);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "evokers_staff"), EVOKERS_STAFF);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "gray_jewel"), GRAY_JEWEL);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "vampiric_staff"), VAMPIRIC_STAFF);

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.BASTION_TREASURE_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.SOUL_POUCH)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(14));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.WOODLAND_MANSION_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.SOUL_HEALER)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(2));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.HERO_OF_THE_VILLAGE_CLERIC_GIFT_GAMEPLAY.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoFoodComponents.DEATH_CAP_MUSHROOM)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(4));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.STRONGHOLD_LIBRARY_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.ENCHANTERS_TOME)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(4));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.HERO_OF_THE_VILLAGE_LIBRARIAN_GIFT_GAMEPLAY.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.UPDRAFT_TOME)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(6));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.ABANDONED_MINESHAFT_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.HARVESTER)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(9));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.END_CITY_TREASURE_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.LIGHTNING_ROD_ARTIFACT)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(7));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.VILLAGE_WEAPONSMITH_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(NeutrinoMain.GOAT_HORN)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(4));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.JUNGLE_TEMPLE_CHEST.equals(id) || LootTables.DESERT_PYRAMID_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.RUSTY_SWORD)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(3));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.BASTION_BRIDGE_CHEST.equals(id) || LootTables.END_CITY_TREASURE_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.SHATTERED_SWORD)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(5));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.SIMPLE_DUNGEON_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.GRAY_JEWEL)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(5));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.END_CITY_TREASURE_CHEST.equals(id) || LootTables.WOODLAND_MANSION_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoFoodComponents.ENCHANTED_DIAMOND_APPLE)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(5));
                table.pool(poolBuilder);
            }
        });

        Registry.register(Registry.SOUND_EVENT, ENCHANTERS_TOME_ACTIVATE_ID, ENCHANTERS_TOME_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, HARVESTER_ACTIVATE_ID, HARVESTER_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, LIGHTNING_ROD_ACTIVATE_ID, LIGHTNING_ROD_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, UPDRAFT_TOME_ACTIVATE_ID, UPDRAFT_TOME_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, SOUL_HEALER_ACTIVATE_ID, SOUL_HEALER_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, WAR_HORN_USE_ID, WAR_HORN_USE);

    }
}