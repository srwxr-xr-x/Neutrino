package com.frostwizard4.Neutrino;

import com.frostwizard4.Neutrino.entity.RatEntity;
import com.frostwizard4.Neutrino.misc.Config;
import com.frostwizard4.Neutrino.misc.MinecraftDungeonsArtifactsConfig;
import com.frostwizard4.Neutrino.misc.NeutrinoFoodComponents;
import com.frostwizard4.Neutrino.misc.WitherPotion;
import com.frostwizard4.Neutrino.registry.*;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.command.v1.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.EnchantingTableBlock;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.GeckoLib;

import static net.minecraft.server.command.CommandManager.argument;
import static net.minecraft.server.command.CommandManager.literal;


public class NeutrinoMain implements ModInitializer {
    public static final ItemGroup NEUTRINO_GROUP = FabricItemGroupBuilder.create(new Identifier("neutrino", "neutrino_group")).icon(() -> new ItemStack(BlockRegistry.HALF_FULL_BOOKSHELF)).build();

    public static final ItemGroup NEUTRINO_DUNGEONS_GROUP = FabricItemGroupBuilder.create(new Identifier("neutrino", "neutrino_dungeons_group")).icon(() -> new ItemStack(ItemRegistry.LIGHTNING_ROD_ARTIFACT)).build();

    @Override
    public void onInitialize() {
        Config.init();
        MinecraftDungeonsArtifactsConfig.init();
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
        CommandRegistry.init();
        OreRegistry.init();
    }
}