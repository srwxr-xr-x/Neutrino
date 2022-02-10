package com.frostwizard4.Neutrino.registry;

import com.frostwizard4.Neutrino.armor.*;
import com.frostwizard4.Neutrino.artifacts.*;
import com.frostwizard4.Neutrino.items.*;
import com.frostwizard4.Neutrino.misc.DaturaBlade;
import com.frostwizard4.Neutrino.misc.DaturaToolMaterial;
import com.frostwizard4.Neutrino.misc.LifeStealEnchantment;
import com.frostwizard4.Neutrino.misc.MinecraftDungeonsArtifactsConfig;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import net.minecraft.util.registry.Registry;

import static com.frostwizard4.Neutrino.NeutrinoMain.NEUTRINO_DUNGEONS_GROUP;
import static com.frostwizard4.Neutrino.NeutrinoMain.NEUTRINO_GROUP;

public class ItemRegistry {
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
    public static final Item DUCK_FEATHER = new Item(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.COMMON));
    public static final Item WITHERING_HEART = new Item(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.EPIC));
    public static final Item WITHERING_HEART_FRAGMENT = new Item(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.RARE));
    public static final Item ALPACA_FUR = new Item(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.COMMON));
    public static final ArmorMaterial FUR_ARMOR_MATERIAL = new FurArmorMaterial();
    public static final Item ALPACA_FUR_SWEATER = new ArmorItem(FUR_ARMOR_MATERIAL, EquipmentSlot.CHEST, new Item.Settings().group(NEUTRINO_GROUP));
    public static final Item WISHBONE = new WishboneItem(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.RARE));
    public static final Item BROKEN_CLOCK = new BrokenClock(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.COMMON));
    public static final Item BROKEN_COMPASS = new Item(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.COMMON));
    public static final ToolItem DATURA_BLADE = new DaturaBlade(DaturaToolMaterial.INSTANCE, 1, 0.5F, new Item.Settings().group(NEUTRINO_GROUP));
    public static final AntivenomItem ANTIVENOM = new AntivenomItem(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.RARE).recipeRemainder(ItemRegistry.EMPTY_DROPPER_BOTTLE));
    public static final Item EMPTY_DROPPER_BOTTLE = new Item(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.COMMON));
    public static final Item SNAKE_FANG = new Item(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.COMMON));
    public static final Item TANZANITE_CRYSTAL = new Item(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.UNCOMMON));
    public static final Item TANZANITE_GEMSTONE = new Item(new FabricItemSettings().group(NEUTRINO_GROUP).rarity(Rarity.EPIC));
    public static final Item JEWELED_DIAMOND_CHESTPLATE = new JeweledChestplate(new JeweledArmorMaterial(), EquipmentSlot.CHEST, new Item.Settings().group(NEUTRINO_GROUP).rarity(Rarity.RARE));
    public static final Item JEWELED_DIAMOND_HELMET = new JeweledHelmet(new JeweledArmorMaterial(), EquipmentSlot.HEAD, new Item.Settings().group(NEUTRINO_GROUP).rarity(Rarity.RARE));
    public static final Item JEWELED_DIAMOND_BOOTS = new JeweledBoots(new JeweledArmorMaterial(), EquipmentSlot.FEET, new Item.Settings().group(NEUTRINO_GROUP).rarity(Rarity.RARE));
    public static final Item JEWELED_DIAMOND_LEGGINGS = new JeweledLeggings(new JeweledArmorMaterial(), EquipmentSlot.LEGS, new Item.Settings().group(NEUTRINO_GROUP).rarity(Rarity.RARE));
    public static final Item MUSIC_DISC_PARADISE = new MusicDiscItem(1, SoundRegister.MUSIC_DISC_PARADISE, new Item.Settings().maxCount(1).group(ItemGroup.MISC).rarity(Rarity.RARE));

    public static void init() {
        if(MinecraftDungeonsArtifactsConfig.lines.get(1).endsWith("On")) {
            Registry.register(Registry.ITEM, new Identifier("neutrino", "enchanters_tome"), ENCHANTERS_TOME);
        }
        if(MinecraftDungeonsArtifactsConfig.lines.get(2).endsWith("On")) {
            Registry.register(Registry.ITEM, new Identifier("neutrino", "harvester"), HARVESTER);
        }
        if(MinecraftDungeonsArtifactsConfig.lines.get(3).endsWith("On")) {
            Registry.register(Registry.ITEM, new Identifier("neutrino", "lightning_rod_artifact"), LIGHTNING_ROD_ARTIFACT);
        }
        if(MinecraftDungeonsArtifactsConfig.lines.get(4).endsWith("On")) {
            Registry.register(Registry.ITEM, new Identifier("neutrino", "updraft_tome"), UPDRAFT_TOME);
        }
        if(MinecraftDungeonsArtifactsConfig.lines.get(5).endsWith("On")) {
            Registry.register(Registry.ITEM, new Identifier("neutrino", "soul_healer"), SOUL_HEALER);
        }
        if(MinecraftDungeonsArtifactsConfig.lines.get(6).endsWith("On")) {
            Registry.register(Registry.ITEM, new Identifier("neutrino", "soul_pouch"), SOUL_POUCH);
        }
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
        Registry.register(Registry.ITEM, new Identifier("neutrino", "alpaca_fur"), ALPACA_FUR);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "alpaca_fur_sweater"), ALPACA_FUR_SWEATER);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "wishbone"), WISHBONE);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "broken_clock"), BROKEN_CLOCK);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "broken_compass"), BROKEN_COMPASS);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "datura_blade"), DATURA_BLADE);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "antivenom"), ANTIVENOM);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "empty_dropper_bottle"), EMPTY_DROPPER_BOTTLE);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "snake_fang"), SNAKE_FANG);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "tanzanite_crystal"), TANZANITE_CRYSTAL);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "tanzanite_gemstone"), TANZANITE_GEMSTONE);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "jeweled_diamond_helmet"), JEWELED_DIAMOND_HELMET);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "jeweled_diamond_chestplate"), JEWELED_DIAMOND_CHESTPLATE);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "jeweled_diamond_leggings"), JEWELED_DIAMOND_LEGGINGS);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "jeweled_diamond_boots"), JEWELED_DIAMOND_BOOTS);
        Registry.register(Registry.ITEM, new Identifier("neutrino", "music_disc_paradise"), MUSIC_DISC_PARADISE);
    }
}
