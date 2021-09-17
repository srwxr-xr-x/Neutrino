package com.frostwizard4.Neutrino;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.MessageType;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.UUID;

public class CheckHolding {
    public static void sendChatMessage(float neutrino$boomPowerCounter) {
        MinecraftClient.getInstance().inGameHud.addChatMessage(MessageType.GAME_INFO, Text.of("Soul Level: " + (int) neutrino$boomPowerCounter / 10), UUID.randomUUID());

    }
    public static void sendBundleChatMessage(float neutrino$soulPouchCounter) {
        MinecraftClient.getInstance().inGameHud.addChatMessage(MessageType.GAME_INFO, Text.of("Soul Pouch Level: " + (int) neutrino$soulPouchCounter / 10), UUID.randomUUID());
    }
}
