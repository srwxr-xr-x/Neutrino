package com.frostwizard4.Neutrino;

import com.frostwizard4.Neutrino.artifacts.*;
import com.frostwizard4.Neutrino.blocks.*;
import com.frostwizard4.Neutrino.items.*;
import com.frostwizard4.Neutrino.misc.LifeStealEnchantment;
import com.frostwizard4.Neutrino.misc.NeutrinoConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static com.frostwizard4.Neutrino.misc.SoundRegister.*;


public class NeutrinoMain implements ModInitializer {

    public static final Block HALF_FULL_BOOKSHELF = new Block(FabricBlockSettings.of(Material.WOOD).strength(1.5F, 1.5F).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES));
    public static final GlassDoor GLASS_DOOR = new GlassDoor(FabricBlockSettings.of(Material.GLASS).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final GlassTrapDoor GLASS_TRAPDOOR = new GlassTrapDoor(FabricBlockSettings.of(Material.GLASS).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block DUNGEONS_POT = new Block(FabricBlockSettings.of(Material.STONE).strength(1F, 2.2F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block SWORD_SHRINE = new Block(FabricBlockSettings.of(Material.STONE).strength(1.5F, 0.8F).nonOpaque().breakByTool(FabricToolTags.PICKAXES));
    public static final Block SHATTERED_SWORD_SHRINE = new Block(FabricBlockSettings.of(Material.STONE).strength(1.5F, 0.8F).nonOpaque().breakByTool(FabricToolTags.PICKAXES));
    public static final DaturaFlower DATURA = new DaturaFlower(StatusEffects.WITHER, 8, FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).nonOpaque());
    public static final ScarecrowBlock SCARECROW = new ScarecrowBlock(FabricBlockSettings.of(Material.WOOD).strength(1.5F, 1.5F).sounds(BlockSoundGroup.WOOD).nonOpaque().breakByTool(FabricToolTags.AXES));

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
    public static final DaturaEssence DATURA_ESSENCE = new DaturaEssence(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.COMMON));

    public static final NeutrinoConfig nConfig;
    static {
        AutoConfig.register(NeutrinoConfig.class, GsonConfigSerializer::new);
        nConfig = AutoConfig.getConfigHolder(NeutrinoConfig.class).getConfig();
    }

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

        Registry.register(Registry.BLOCK, new Identifier("neutrino", "sword_shrine"), SWORD_SHRINE);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "sword_shrine"), new BlockItem(SWORD_SHRINE, new FabricItemSettings().group(NEUTRINO_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier("neutrino", "shattered_sword_shrine"), SHATTERED_SWORD_SHRINE);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "shattered_sword_shrine"), new BlockItem(SHATTERED_SWORD_SHRINE, new FabricItemSettings().group(NEUTRINO_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier("neutrino", "datura"), DATURA);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "datura"), new BlockItem(DATURA, new FabricItemSettings().group(NEUTRINO_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier("neutrino", "scarecrow"), SCARECROW);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "scarecrow"), new BlockItem(SCARECROW, new FabricItemSettings().group(NEUTRINO_GROUP)));

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
        Registry.register(Registry.ITEM, new Identifier("neutrino", "datura_essence"), DATURA_ESSENCE);

        LootTableRegister.register();

        Registry.register(Registry.SOUND_EVENT, ENCHANTERS_TOME_ACTIVATE_ID, ENCHANTERS_TOME_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, HARVESTER_ACTIVATE_ID, HARVESTER_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, LIGHTNING_ROD_ACTIVATE_ID, LIGHTNING_ROD_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, UPDRAFT_TOME_ACTIVATE_ID, UPDRAFT_TOME_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, SOUL_HEALER_ACTIVATE_ID, SOUL_HEALER_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, WAR_HORN_USE_ID, WAR_HORN_USE);


        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {
            if (entity.getEntityWorld().isClient) {
                return ActionResult.PASS;
            }
            for (String id : NeutrinoConfig.attackExcludedEntities) {
                Entity attacker = player.getAttacker();
                if (attacker == null)
                    break;
                Identifier loc = EntityType.getId(attacker.getType());
                if (loc == null)
                    break;
                int starIndex = id.indexOf('*');
                if (starIndex != -1) {
                    if (loc.toString().contains(id.substring(0, starIndex))) {
                        return ActionResult.PASS;
                    }
                } else if (loc.toString().equals(id)) {
                    return ActionResult.PASS;
                }

            }

            if(NeutrinoMain.nConfig.isIFramesOff()) {
                entity.timeUntilRegen = 0;
            } else if(NeutrinoMain.nConfig.isIFramesHalf()) {
                entity.timeUntilRegen = 5;
            }
            return ActionResult.PASS;
        });
    }
}