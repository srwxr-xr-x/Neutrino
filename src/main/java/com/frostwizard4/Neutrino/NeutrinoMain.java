package com.frostwizard4.Neutrino;

import com.frostwizard4.Neutrino.misc.NeutrinoConfig;
import com.frostwizard4.Neutrino.misc.NeutrinoFoodComponents;
import com.frostwizard4.Neutrino.misc.WitherPotion;
import com.frostwizard4.Neutrino.registry.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.GeckoLib;

public class NeutrinoMain implements ModInitializer {
    public static final ItemGroup NEUTRINO_GROUP = FabricItemGroupBuilder.create(new Identifier("neutrino", "neutrino_group")).icon(() -> new ItemStack(BlockRegistry.HALF_FULL_BOOKSHELF)).build();
    public static final ItemGroup NEUTRINO_DUNGEONS_GROUP = FabricItemGroupBuilder.create(new Identifier("neutrino", "neutrino_dungeons_group")).icon(() -> new ItemStack(ItemRegistry.LIGHTNING_ROD_ARTIFACT)).build();
    public static final NeutrinoConfig nConfig;
    static {
        AutoConfig.register(NeutrinoConfig.class, GsonConfigSerializer::new);
        nConfig = AutoConfig.getConfigHolder(NeutrinoConfig.class).getConfig();
    }

    @Override
    public void onInitialize() {
        VillagerInit.fillTradeData();
        NeutrinoFoodComponents.registerFoods();
        GeckoLib.initialize();
        WitherPotion.init();
        BlockRegistry.init();
        ItemRegistry.init();
        MiscEntityRegistry.init();
        LootTableRegister.register();
        SoundRegister.init();
        MiscellaneousRegistry.init();
    }
}