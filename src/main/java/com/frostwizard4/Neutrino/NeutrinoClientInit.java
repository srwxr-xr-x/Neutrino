package com.frostwizard4.Neutrino;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.util.Identifier;

import static com.frostwizard4.Neutrino.NeutrinoMain.*;

public class NeutrinoClientInit implements ClientModInitializer {
    @Override
    public void onInitializeClient() {

        BlockRenderLayerMap.INSTANCE.putBlock(GLASS_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GLASS_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DATURA, RenderLayer.getCutout());

        FabricModelPredicateProviderRegistry.register(NeutrinoMain.SOUL_POUCH, new Identifier("filled"), (stack, world, entity, seed) -> {
            if (entity != null) {
                if (((PlayerEntityAccess) entity).neutrino$getSoulPouchCount() == 3000) {
                    return 1.0f;
                } else {
                    return 0.0f;
                }
            } else {
                return 0;
            }
        });
    }
}
