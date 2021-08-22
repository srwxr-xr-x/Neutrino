package com.frostwizard4.Neutrino;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;

import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;

import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static net.minecraft.util.registry.Registry.register;


public class NeutrinoMain implements ModInitializer {

    // an instance of our new item
    public static final Block HALF_FULL_BOOKSHELF = new Block(FabricBlockSettings.of(Material.WOOD).strength(1.5F,1.5F).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES));
    public static final GlassDoor GLASS_DOOR = new GlassDoor(FabricBlockSettings.of(Material.GLASS).strength(0.3f,0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final GlassTrapDoor GLASS_TRAPDOOR = new GlassTrapDoor(FabricBlockSettings.of(Material.GLASS).strength(0.3f,0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque());

    public static final SlabBlock DIRT_SLAB = new SlabBlock(FabricBlockSettings.of(Material.SOIL).strength(1.5f,1.5f).sounds(BlockSoundGroup.GRASS));
    public static final SlabBlock GRAVEL_SLAB = new SlabBlock(FabricBlockSettings.of(Material.AGGREGATE).strength(0.3f,0.3f).sounds(BlockSoundGroup.GRAVEL));
    public static final SlabBlock SAND_SLAB = new SlabBlock(FabricBlockSettings.of(Material.AGGREGATE).strength(0.3f,0.3f).sounds(BlockSoundGroup.SAND));
    public static final CraftingSlab CRAFTING_SLAB = new CraftingSlab(FabricBlockSettings.of(Material.WOOD).strength(1.5f, 1.5f).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES));

    @Override
    public void onInitialize() {

        register(Registry.BLOCK, new Identifier("neutrino", "half_full_bookshelf"), HALF_FULL_BOOKSHELF);
        register(Registry.ITEM, new Identifier("neutrino", "half_full_bookshelf"), new BlockItem(HALF_FULL_BOOKSHELF, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        register(Registry.ITEM, new Identifier("neutrino", "glass_door"), new BlockItem(GLASS_DOOR, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        register(Registry.BLOCK, new Identifier("neutrino", "glass_door"), GLASS_DOOR);

        register(Registry.ITEM, new Identifier("neutrino", "glass_trapdoor"), new BlockItem(GLASS_TRAPDOOR, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        register(Registry.BLOCK, new Identifier("neutrino", "glass_trapdoor"), GLASS_TRAPDOOR);

        register(Registry.ITEM, new Identifier("neutrino", "dirt_slab"), new BlockItem(DIRT_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        register(Registry.BLOCK, new Identifier("neutrino", "dirt_slab"), DIRT_SLAB);

        register(Registry.ITEM, new Identifier("neutrino", "gravel_slab"), new BlockItem(GRAVEL_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        register(Registry.BLOCK, new Identifier("neutrino", "gravel_slab"), GRAVEL_SLAB);

        register(Registry.ITEM, new Identifier("neutrino", "sand_slab"), new BlockItem(SAND_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        register(Registry.BLOCK, new Identifier("neutrino", "sand_slab"), SAND_SLAB);

        register(Registry.ITEM, new Identifier("neutrino", "crafting_slab"), new BlockItem(CRAFTING_SLAB, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        register(Registry.BLOCK, new Identifier("neutrino", "crafting_slab"), CRAFTING_SLAB);

        BlockRenderLayerMap.INSTANCE.putBlock(GLASS_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GLASS_TRAPDOOR, RenderLayer.getCutout());
    }
}

