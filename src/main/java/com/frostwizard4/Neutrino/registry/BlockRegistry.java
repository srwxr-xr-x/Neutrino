package com.frostwizard4.Neutrino.registry;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.frostwizard4.Neutrino.NeutrinoMain.*;

public class BlockRegistry {
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
    }

}
