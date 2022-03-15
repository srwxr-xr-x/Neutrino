package com.frostwizard4.Neutrino.registry;

import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.map.MapIcon;
import net.minecraft.tag.ConfiguredStructureFeatureTags;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import net.minecraft.world.gen.feature.StructureFeature;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class VillagerInit {

    public static void fillTradeData() {

        TradeOffers.Factory[] weaponsmithLevel1 = new TradeOffers.Factory[]{new TradeOffers.BuyForOneEmeraldFactory(Items.COAL, 15, 16, 2), new TradeOffers.SellItemFactory(new ItemStack(Items.IRON_AXE), 3, 1, 12, 1, 0.2F), new TradeOffers.SellEnchantedToolFactory(Items.IRON_SWORD, 2, 3, 1)};
        TradeOffers.Factory[] weaponsmithLevel2 = new TradeOffers.Factory[]{new TradeOffers.BuyForOneEmeraldFactory(Items.IRON_INGOT, 4, 12, 10), new TradeOffers.SellItemFactory(new ItemStack(Items.BELL), 36, 1, 12, 5, 0.2F)};
        TradeOffers.Factory[] weaponsmithLevel3 = new TradeOffers.Factory[]{new SellSwordFactory(40, 2, 3), new SellShatteredSwordFactory(45, 2, 3)};
        TradeOffers.Factory[] weaponsmithLevel4 = new TradeOffers.Factory[]{new TradeOffers.BuyForOneEmeraldFactory(Items.DIAMOND, 1, 12, 30), new TradeOffers.BuyForOneEmeraldFactory(Items.FLINT, 24, 12, 20), new TradeOffers.SellEnchantedToolFactory(Items.DIAMOND_AXE, 12, 3, 15, 0.2F)};
        TradeOffers.Factory[] weaponsmithLevel5 = new TradeOffers.Factory[]{new TradeOffers.SellEnchantedToolFactory(Items.DIAMOND_SWORD, 8, 3, 30, 0.2F)};

        TradeOffers.PROFESSION_TO_LEVELED_TRADE.put(VillagerProfession.WEAPONSMITH, VillagerInit.toIntMap(ImmutableMap.of(1, weaponsmithLevel1, 2, weaponsmithLevel2, 3, weaponsmithLevel3, 4, weaponsmithLevel4, 5, weaponsmithLevel5)));
        TradeOffers.Factory[] cartographerLevel1 = new TradeOffers.Factory[]{new TradeOffers.BuyForOneEmeraldFactory(Items.PAPER, 24, 16, 2), new TradeOffers.SellItemFactory(Items.MAP, 7, 1, 1)};
        TradeOffers.Factory[] cartographerLevel2 = new TradeOffers.Factory[]{new TradeOffers.BuyForOneEmeraldFactory(Items.GLASS_PANE, 11, 16, 10), new TradeOffers.SellMapFactory(13, ConfiguredStructureFeatureTags.ON_OCEAN_EXPLORER_MAPS, "filled_map.monument", MapIcon.Type.MONUMENT, 12, 5)};
        TradeOffers.Factory[] cartographerLevel3 = new TradeOffers.Factory[]{new TradeOffers.BuyForOneEmeraldFactory(Items.COMPASS, 1, 12, 20), new TradeOffers.SellMapFactory(14, ConfiguredStructureFeatureTags.ON_WOODLAND_EXPLORER_MAPS, "filled_map.mansion", MapIcon.Type.MANSION, 12, 10)};
        TradeOffers.Factory[] cartographerLevel4 = new TradeOffers.Factory[]{new TradeOffers.SellItemFactory(Items.ITEM_FRAME, 7, 1, 15), new TradeOffers.SellItemFactory(Items.WHITE_BANNER, 3, 1, 15), new TradeOffers.SellItemFactory(Items.BLUE_BANNER, 3, 1, 15), new TradeOffers.SellItemFactory(Items.LIGHT_BLUE_BANNER, 3, 1, 15), new TradeOffers.SellItemFactory(Items.RED_BANNER, 3, 1, 15), new TradeOffers.SellItemFactory(Items.PINK_BANNER, 3, 1, 15), new TradeOffers.SellItemFactory(Items.GREEN_BANNER, 3, 1, 15), new TradeOffers.SellItemFactory(Items.LIME_BANNER, 3, 1, 15), new TradeOffers.SellItemFactory(Items.GRAY_BANNER, 3, 1, 15), new TradeOffers.SellItemFactory(Items.BLACK_BANNER, 3, 1, 15), new TradeOffers.SellItemFactory(Items.PURPLE_BANNER, 3, 1, 15), new TradeOffers.SellItemFactory(Items.MAGENTA_BANNER, 3, 1, 15), new TradeOffers.SellItemFactory(Items.CYAN_BANNER, 3, 1, 15), new TradeOffers.SellItemFactory(Items.BROWN_BANNER, 3, 1, 15), new TradeOffers.SellItemFactory(Items.YELLOW_BANNER, 3, 1, 15), new TradeOffers.SellItemFactory(Items.ORANGE_BANNER, 3, 1, 15), new TradeOffers.SellItemFactory(Items.LIGHT_GRAY_BANNER, 3, 1, 15)};
        TradeOffers.Factory[] cartographerLevel5 = new TradeOffers.Factory[]{new TradeOffers.SellItemFactory(Items.GLOBE_BANNER_PATTERN, 8, 1, 30), new BuyForFortyEmeraldFactory(BlockRegistry.ALPACA_COLLECTIBLE, 1, 2, 50)};
        TradeOffers.PROFESSION_TO_LEVELED_TRADE.put(VillagerProfession.CARTOGRAPHER, VillagerInit.toIntMap(ImmutableMap.of(1, cartographerLevel1, 2, cartographerLevel2, 3, cartographerLevel3, 4, cartographerLevel4, 5, cartographerLevel5)));

    }

    public static class BuyForFortyEmeraldFactory implements TradeOffers.Factory {
        private final Item buy;
        private final int price;
        private final int maxUses;
        private final int experience;
        private final float multiplier;

        public BuyForFortyEmeraldFactory(ItemConvertible item, int price, int maxUses, int experience) {
            this.buy = item.asItem();
            this.price = price;
            this.maxUses = maxUses;
            this.experience = experience;
            this.multiplier = 0.05F;
        }

        public TradeOffer create(Entity entity, Random random) {
            ItemStack itemStack = new ItemStack(this.buy, this.price);
            return new TradeOffer(itemStack, new ItemStack(Items.EMERALD, 40), this.maxUses, this.experience, this.multiplier);
        }
    }

    public static class SellSwordFactory implements TradeOffers.Factory {
        private final int price;
        private final int maxUses;
        private final int experience;

        public SellSwordFactory(int price, int maxUses, int experience) {
            this.price = price;
            this.maxUses = maxUses;
            this.experience = experience;
        }

        @Nullable
        public TradeOffer create(Entity entity, Random random) {
            int rnd = (int)(Math.random() * (30 - 10 + 1)) + 10;
            return new TradeOffer(new ItemStack(Items.EMERALD, this.price), new ItemStack(ItemRegistry.RUSTY_SWORD), EnchantmentHelper.enchant(random, new ItemStack(Items.IRON_SWORD), rnd * 2, false), this.maxUses, this.experience, 0.2F);
        }
    }

    public static class SellShatteredSwordFactory implements TradeOffers.Factory {
        private final int price;
        private final int maxUses;
        private final int experience;

        public SellShatteredSwordFactory(int price, int maxUses, int experience) {
            this.price = price;
            this.maxUses = maxUses;
            this.experience = experience;
        }

        @Nullable
        public TradeOffer create(Entity entity, Random random) {
            int rnd = (int)(Math.random() * (30 - 10 + 1)) + 10;
            return new TradeOffer(new ItemStack(Items.EMERALD, this.price), new ItemStack(ItemRegistry.SHATTERED_SWORD), EnchantmentHelper.enchant(random, new ItemStack(Items.DIAMOND_SWORD), rnd * 2, false), this.maxUses, this.experience, 0.2F);
        }
    }

    private static Int2ObjectMap<TradeOffers.Factory[]> toIntMap(ImmutableMap<Integer, TradeOffers.Factory[]> trades) {
        return new Int2ObjectOpenHashMap<>(trades);
    }

}
