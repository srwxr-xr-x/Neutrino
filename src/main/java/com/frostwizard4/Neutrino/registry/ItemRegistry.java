package com.frostwizard4.Neutrino.registry;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.frostwizard4.Neutrino.NeutrinoMain.*;

public class ItemRegistry {
    public static void init() {
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
        Registry.register(Registry.ITEM, new Identifier("neutrino", "duck_feather"), DUCK_FEATHER);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "withering_heart"), WITHERING_HEART);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "withering_heart_fragment"), WITHERING_HEART_FRAGMENT);

    }
}
