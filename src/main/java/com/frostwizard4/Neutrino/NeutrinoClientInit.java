package com.frostwizard4.Neutrino;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.CrossbowItem;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import static com.frostwizard4.Neutrino.NeutrinoMain.GLASS_DOOR;
import static com.frostwizard4.Neutrino.NeutrinoMain.GLASS_TRAPDOOR;

public class NeutrinoClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(GLASS_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GLASS_TRAPDOOR, RenderLayer.getCutout());

        FabricModelPredicateProviderRegistry.register(NeutrinoMain.SOUL_POUCH, new Identifier("filled"), (stack, world, entity, seed) -> {
            if (entity != null) {
                if (((PlayerEntityAccess) entity).neutrino$getSoulPouchCount() == 3000) {
                    return 1.0f;
                } else {
                    return 0.0f;
                }
            } else {
                return 0;}
        });

        FabricModelPredicateProviderRegistry.register(NeutrinoMain.DIAMOND_CROSSBOW, new Identifier("pull"), (stack, world, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                return CrossbowItem.isCharged(stack) ? 0.0F : (float)(stack.getMaxUseTime() - entity.getItemUseTimeLeft()) / (float)CrossbowItem.getPullTime(stack);
            }
        });
        FabricModelPredicateProviderRegistry.register(NeutrinoMain.DIAMOND_CROSSBOW, new Identifier("pulling"), (stack, world, entity, seed) -> {
            return entity != null && entity.isUsingItem() && entity.getActiveItem() == stack && !CrossbowItem.isCharged(stack) ? 1.0F : 0.0F;
        });
        FabricModelPredicateProviderRegistry.register(NeutrinoMain.DIAMOND_CROSSBOW, new Identifier("charged"), (stack, world, entity, seed) -> {
            return entity != null && CrossbowItem.isCharged(stack) ? 1.0F : 0.0F;
        });
        FabricModelPredicateProviderRegistry.register(NeutrinoMain.DIAMOND_CROSSBOW, new Identifier("firework"), (stack, world, entity, seed) -> {
            return entity != null && CrossbowItem.isCharged(stack) && CrossbowItem.hasProjectile(stack, Items.FIREWORK_ROCKET) ? 1.0F : 0.0F;
        });
    }
}
