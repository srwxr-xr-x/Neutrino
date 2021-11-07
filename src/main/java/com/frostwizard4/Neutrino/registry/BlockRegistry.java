package com.frostwizard4.Neutrino.registry;

import com.frostwizard4.Neutrino.blocks.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.frostwizard4.Neutrino.NeutrinoMain.NEUTRINO_GROUP;

public class BlockRegistry {
    public static final SlabBlock DIRT_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).strength(1.5f, 1.5f).sounds(BlockSoundGroup.GRASS));
    public static final SandSlab GRAVEL_SLAB = new SandSlab(FabricBlockSettings.of(Material.AGGREGATE).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GRAVEL));
    public static final SandSlab SAND_SLAB = new SandSlab(FabricBlockSettings.of(Material.AGGREGATE).strength(0.3f, 0.3f).sounds(BlockSoundGroup.SAND));
    public static final CraftingSlab CRAFTING_SLAB = new CraftingSlab(FabricBlockSettings.of(Material.WOOD).strength(1.5f, 1.5f).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES));

    public static final Block HALF_FULL_BOOKSHELF = new Block(FabricBlockSettings.of(Material.WOOD).strength(1.5F, 1.5F).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES));
    public static final GlassDoor GLASS_DOOR = new GlassDoor(FabricBlockSettings.of(Material.GLASS).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final GlassTrapDoor GLASS_TRAPDOOR = new GlassTrapDoor(FabricBlockSettings.of(Material.GLASS).strength(0.3f, 0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block DUNGEONS_POT = new Block(FabricBlockSettings.of(Material.STONE).strength(1F, 2.2F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block SWORD_SHRINE = new SwordShrine(FabricBlockSettings.of(Material.STONE).strength(1.5F, 0.8F).nonOpaque().breakByTool(FabricToolTags.PICKAXES));
    public static final Block SHATTERED_SWORD_SHRINE = new ShatteredSwordShrine(FabricBlockSettings.of(Material.STONE).strength(1.5F, 0.8F).nonOpaque().breakByTool(FabricToolTags.PICKAXES));
    public static final DaturaFlower DATURA = new DaturaFlower(StatusEffects.WITHER, 8, FabricBlockSettings.of(Material.PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).nonOpaque());
    public static final ScarecrowBlock SCARECROW = new ScarecrowBlock(FabricBlockSettings.of(Material.WOOD).strength(1.5F, 1.5F).sounds(BlockSoundGroup.WOOD).nonOpaque().breakByTool(FabricToolTags.AXES));
    public static final Block ALPACA_COLLECTIBLE = new AlpacaBlock(FabricBlockSettings.of(Material.WOOL).strength(0.5F, 0.5F).nonOpaque().breakByTool(FabricToolTags.SHEARS));
    public static final Block BONSAI_TREE = new BonsaiBlock(FabricBlockSettings.of(Material.WOOD).strength(0.5F, 0.5F).nonOpaque().breakByTool(FabricToolTags.AXES));

    public static void init() {
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

        Registry.register(Registry.BLOCK, new Identifier("neutrino", "alpaca_collectible"), ALPACA_COLLECTIBLE);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "alpaca_collectible"), new BlockItem(ALPACA_COLLECTIBLE, new FabricItemSettings().group(NEUTRINO_GROUP)));

        Registry.register(Registry.BLOCK, new Identifier("neutrino", "bonsai_tree"), BONSAI_TREE);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "bonsai_tree"), new BlockItem(BONSAI_TREE, new FabricItemSettings().group(NEUTRINO_GROUP)));

    }

}
