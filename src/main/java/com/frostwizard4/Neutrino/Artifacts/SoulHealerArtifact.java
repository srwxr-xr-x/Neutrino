package com.frostwizard4.Neutrino.Artifacts;

import com.frostwizard4.Neutrino.PlayerEntityAccess;
import com.frostwizard4.Neutrino.Misc.SoundRegister;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

public class SoulHealerArtifact extends Item {
    public SoulHealerArtifact(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (((PlayerEntityAccess) playerEntity).neutrino$getPowerCount() >= 150) {
            //Play Healer Sound
            playerEntity.playSound(SoundRegister.SOUL_HEALER_ACTIVATE, 1.0F, 1.0F);
            //Heal
            world.getClosestPlayer(playerEntity, 15).addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 200, 2));
            ((PlayerEntityAccess) playerEntity).neutrino$setPowerCount(0);
        } else {
            if (world.isClient()) {
                MinecraftClient.getInstance().inGameHud.addChatMessage(MessageType.GAME_INFO, Text.of("Not enough souls!"), UUID.randomUUID());
            }
        }
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.neutrino.soul_healer.tooltip").formatted(Formatting.AQUA));
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.neutrino.soul_healer.tooltip2"));
        } else {
            tooltip.add(new TranslatableText("item.neutrino.soul_healer.shiftdown").formatted(Formatting.DARK_GRAY));
        }
    }
}
