package com.frostwizard4.Neutrino.artifacts;

import com.frostwizard4.Neutrino.NeutrinoMain;
import com.frostwizard4.Neutrino.PlayerEntityAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;
import java.util.UUID;

import static com.frostwizard4.Neutrino.registry.SoundRegister.LIGHTNING_ROD_ACTIVATE;

public class LightningRodArtifact extends Item {
    public LightningRodArtifact(Settings settings) {
        super(settings);
    }

    public void summonLightning(PlayerEntity playerEntity, World world) {
        for (Entity e : world.getOtherEntities(playerEntity, Box.of(playerEntity.getPos(), 10, 10, 10))) {
            if (e instanceof MobEntity && (playerEntity.distanceTo(e) < 10)) {
                LightningEntity lightningEntity = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
                lightningEntity.setPos(e.getX(), e.getY(), e.getZ());
                world.spawnEntity(lightningEntity);
            }
        }
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        if (((PlayerEntityAccess) playerEntity).neutrino$getPowerCount() >= 150) {
            //Play Sound
            playerEntity.playSound(LIGHTNING_ROD_ACTIVATE, 1.0F, 1.0F);
            //Summon Lightning
            if (world.isRaining() && !world.isClient()) {
                if (world.isThundering() && !world.isClient()) {
                        summonLightning(playerEntity, world);
                }
                summonLightning(playerEntity, world);
            }
            if(!world.isClient()) {
                summonLightning(playerEntity, world);
            }
            //Set 2 second Cooldown
            playerEntity.getItemCooldownManager().set(NeutrinoMain.LIGHTNING_ROD_ARTIFACT, 40);
            ((PlayerEntityAccess) playerEntity).neutrino$setPowerCount(((PlayerEntityAccess) playerEntity).neutrino$getPowerCount() - 150);
        } else {
            if(world.isClient()) {
                MinecraftClient.getInstance().inGameHud.addChatMessage(MessageType.GAME_INFO, Text.of("Not enough souls!"), UUID.randomUUID());
            }
        }
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.neutrino.lightning_rod.tooltip").formatted(Formatting.AQUA));
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.neutrino.lightning_rod.tooltip2"));
            tooltip.add(new TranslatableText("item.neutrino.lightning_rod.tooltip3"));

        } else {
            tooltip.add(new TranslatableText("item.neutrino.lightning_rod.shiftdown").formatted(Formatting.DARK_GRAY));
        }
    }
}
