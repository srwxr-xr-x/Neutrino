package com.frostwizard4.Neutrino;

import com.frostwizard4.Neutrino.Artifacts.*;
import com.frostwizard4.Neutrino.Blocks.GlassDoor;
import com.frostwizard4.Neutrino.Blocks.GlassTrapDoor;
import com.frostwizard4.Neutrino.Items.Backstabber;
import com.frostwizard4.Neutrino.Items.DaggerToolMaterial;
import com.frostwizard4.Neutrino.Slabs.CraftingSlab;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.SlabBlock;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.LightningEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemFrameItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
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

        Registry.register(Registry.SOUND_EVENT, SoundRegister.ENCHANTERS_TOME_ACTIVATE_ID, ENCHANTERS_TOME_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, SoundRegister.DAGGER_STAB_ID, DAGGER_STAB);
        Registry.register(Registry.SOUND_EVENT, SoundRegister.HARVESTER_ACTIVATE_ID, HARVESTER_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, SoundRegister.LIGHTNING_ROD_ACTIVATE_ID, LIGHTNING_ROD_ACTIVATE);
        Registry.register(Registry.SOUND_EVENT, SoundRegister.UPDRAFT_TOME_ACTIVATE_ID, UPDRAFT_TOME_ACTIVATE);


        BlockRenderLayerMap.INSTANCE.putBlock(GLASS_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GLASS_TRAPDOOR, RenderLayer.getCutout());

    }
}

