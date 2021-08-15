package com.frostwizard4.Neutrino;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;

import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;

import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class NeutrinoMain implements ModInitializer {

    // an instance of our new item
    public static final NeutrinoCompendium COMPENDIUM = new NeutrinoCompendium(new FabricItemSettings().group(ItemGroup.MISC).maxCount(1));
    public static final Block HALF_FULL_BOOKSHELF = new Block(FabricBlockSettings.of(Material.WOOD).strength(1.5F,1.5F).sounds(BlockSoundGroup.WOOD).breakByTool(FabricToolTags.AXES));
    public static final GlassDoor GLASS_DOOR = new GlassDoor(FabricBlockSettings.of(Material.GLASS).strength(0.3f,0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final GlassTrapDoor GLASS_TRAPDOOR = new GlassTrapDoor(FabricBlockSettings.of(Material.GLASS).strength(0.3f,0.3f).sounds(BlockSoundGroup.GLASS).nonOpaque());



    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier("neutrino", "compendium"), COMPENDIUM);

        Registry.register(Registry.BLOCK, new Identifier("neutrino", "half_full_bookshelf"), HALF_FULL_BOOKSHELF);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "half_full_bookshelf"), new BlockItem(HALF_FULL_BOOKSHELF, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));

        Registry.register(Registry.ITEM, new Identifier("neutrino", "glass_door"), new BlockItem(GLASS_DOOR, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("neutrino", "glass_door"), GLASS_DOOR);

        Registry.register(Registry.ITEM, new Identifier("neutrino", "glass_trapdoor"), new BlockItem(GLASS_TRAPDOOR, new FabricItemSettings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.BLOCK, new Identifier("neutrino", "glass_trapdoor"), GLASS_TRAPDOOR);

        BlockRenderLayerMap.INSTANCE.putBlock(NeutrinoMain.GLASS_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(NeutrinoMain.GLASS_TRAPDOOR, RenderLayer.getCutout());



    }
}

