package com.frostwizard4.Neutrino.registry;

import com.frostwizard4.Neutrino.NeutrinoMain;
import com.google.common.collect.ImmutableMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
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
