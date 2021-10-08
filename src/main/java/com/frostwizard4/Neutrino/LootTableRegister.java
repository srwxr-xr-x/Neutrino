package com.frostwizard4.Neutrino;

import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.entry.EmptyEntry;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;

public class LootTableRegister {

    public static void register() {

        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.BASTION_TREASURE_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.SOUL_POUCH)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(14));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.WOODLAND_MANSION_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.SOUL_HEALER)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(2));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.HERO_OF_THE_VILLAGE_CLERIC_GIFT_GAMEPLAY.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoFoodComponents.DEATH_CAP_MUSHROOM)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(4));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.STRONGHOLD_LIBRARY_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.ENCHANTERS_TOME)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(4));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.HERO_OF_THE_VILLAGE_LIBRARIAN_GIFT_GAMEPLAY.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.UPDRAFT_TOME)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(6));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.ABANDONED_MINESHAFT_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.HARVESTER)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(9));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.END_CITY_TREASURE_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.LIGHTNING_ROD_ARTIFACT)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(7));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.VILLAGE_WEAPONSMITH_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1.0F))
                        .with(ItemEntry.builder(NeutrinoMain.GOAT_HORN)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(4));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.JUNGLE_TEMPLE_CHEST.equals(id) || LootTables.DESERT_PYRAMID_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.RUSTY_SWORD)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(3));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.BASTION_BRIDGE_CHEST.equals(id) || LootTables.END_CITY_TREASURE_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.SHATTERED_SWORD)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(5));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.SIMPLE_DUNGEON_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoMain.GRAY_JEWEL)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(5));
                table.pool(poolBuilder);
            }
        });
        LootTableLoadingCallback.EVENT.register((resourceManager, lootManager, id, table, setter) -> {
            if (LootTables.END_CITY_TREASURE_CHEST.equals(id) || LootTables.WOODLAND_MANSION_CHEST.equals(id)) {
                FabricLootPoolBuilder poolBuilder = FabricLootPoolBuilder.builder()
                        .rolls(ConstantLootNumberProvider.create(1))
                        .with(ItemEntry.builder(NeutrinoFoodComponents.ENCHANTED_DIAMOND_APPLE)
                                .weight(1))
                        .with(EmptyEntry.Serializer().weight(5));
                table.pool(poolBuilder);
            }
        });
    }
}
