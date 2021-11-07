package com.frostwizard4.Neutrino;

import com.frostwizard4.Neutrino.misc.Config;
import com.frostwizard4.Neutrino.misc.NeutrinoFoodComponents;
import com.frostwizard4.Neutrino.misc.WitherPotion;
import com.frostwizard4.Neutrino.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.village.TradeOffers;
import net.minecraft.village.VillagerProfession;
import software.bernie.geckolib3.GeckoLib;

import java.util.List;
import java.util.function.Consumer;

public class NeutrinoMain implements ModInitializer {
    public static final ItemGroup NEUTRINO_GROUP = FabricItemGroupBuilder.create(new Identifier("neutrino", "neutrino_group")).icon(() -> new ItemStack(BlockRegistry.HALF_FULL_BOOKSHELF)).build();
    public static final ItemGroup NEUTRINO_DUNGEONS_GROUP = FabricItemGroupBuilder.create(new Identifier("neutrino", "neutrino_dungeons_group")).icon(() -> new ItemStack(ItemRegistry.LIGHTNING_ROD_ARTIFACT)).build();

    @Override
    public void onInitialize() {
        Config.init();
        NeutrinoFoodComponents.registerFoods();
        GeckoLib.initialize();
        WitherPotion.init();
        BlockRegistry.init();
        ItemRegistry.init();
        MiscEntityRegistry.init();
        LootTableRegister.register();
        SoundRegister.init();
        MiscellaneousRegistry.init();
        StatusEffectRegistry.init();
        VillagerInit.fillTradeData();
    }
}