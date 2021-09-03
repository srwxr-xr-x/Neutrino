package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.NeutrinoMain;
import com.frostwizard4.Neutrino.PlayerEntityAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameHud;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.MessageType;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityAccess {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    private float neutrino$boomPowerCounter = 0;
    MinecraftClient client = MinecraftClient.getInstance();
    InGameHud hud = new InGameHud(client);

    @Override
    public float neutrino$getPowerCount() {
        return neutrino$boomPowerCounter;
    }

    public void neutrino$setPowerCount(float newValue) {
        neutrino$boomPowerCounter = newValue;
    }

    @Shadow
    protected abstract boolean isOnSoulSpeedBlock();

    @Shadow
    public abstract Iterable<ItemStack> getItemsHand();

    @Inject(at = @At("HEAD"), method = "tick()V")
    private void neutrino$checkHolding(CallbackInfo ci) {
        if(getMainHandStack().isOf(NeutrinoMain.HARVESTER) || getMainHandStack().isOf(NeutrinoMain.LIGHTNING_ROD_ARTIFACT)) {
            hud.addChatMessage(MessageType.GAME_INFO, Text.of("Power Level: " + (int)neutrino$boomPowerCounter / 10), UUID.randomUUID());
        }
        if (isOnSoulSpeedBlock()) {
            if (getMainHandStack().isOf(NeutrinoMain.HARVESTER) || getMainHandStack().isOf(NeutrinoMain.LIGHTNING_ROD_ARTIFACT)) {
                if (neutrino$boomPowerCounter >= 1500) {
                    neutrino$boomPowerCounter = 1500;
                    hud.addChatMessage(MessageType.GAME_INFO, Text.of("Power Level: " + (int)neutrino$boomPowerCounter / 10), UUID.randomUUID());

                } else {
                    neutrino$boomPowerCounter++;
                    hud.addChatMessage(MessageType.GAME_INFO, Text.of("Power Level: " + (int)neutrino$boomPowerCounter / 10), UUID.randomUUID());
                }
            }
        }
    }
}

