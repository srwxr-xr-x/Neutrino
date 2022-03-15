package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.registry.ItemRegistry;
import com.frostwizard4.Neutrino.registry.NeutrinoModelLoader;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.hud.InGameOverlayRenderer;
import net.minecraft.client.util.math.MatrixStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(InGameOverlayRenderer.class)
public abstract class InGameOverlayRendererMixin {

    @Inject(method = "renderOverlays", at = @At("HEAD"))
    private static void canRepair(MinecraftClient client, MatrixStack matrices, CallbackInfo ci) {
        if (client.player != null && !client.player.isSpectator()) {
            if (client.player.getInventory().contains(ItemRegistry.COPPER_TALISMAN.getDefaultStack())) {
                NeutrinoModelLoader.renderElectricityOverlay(client, matrices);
            }
        }
    }
}