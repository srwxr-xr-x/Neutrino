package com.frostwizard4.Neutrino;

import com.frostwizard4.Neutrino.entity.*;
import com.frostwizard4.Neutrino.misc.EmberParticle;
import com.frostwizard4.Neutrino.registry.ItemRegistry;
import com.frostwizard4.Neutrino.registry.ParticleRegistry;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.event.client.ClientSpriteRegistryCallback;
import net.fabricmc.fabric.api.object.builder.v1.client.model.FabricModelPredicateProviderRegistry;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

import static com.frostwizard4.Neutrino.registry.BlockRegistry.*;

public class NeutrinoClientInit implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(GLASS_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GLASS_TRAPDOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DATURA, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BONSAI_TREE, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ALARM_BLOCK, RenderLayer.getCutout());

        FabricModelPredicateProviderRegistry.register(ItemRegistry.SOUL_POUCH, new Identifier("filled"), (stack, world, entity, seed) -> {
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
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.RAT, RatEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.DUCK, DuckEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.WITHERLING, WitherlingEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.ALPACA, AlpacaEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(EntityRegistry.DESERT_SERPENT, DesertSerpentEntityRenderer::new);

        ClientSpriteRegistryCallback.event(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE).register(((atlasTexture, registry) -> {
            registry.register(new Identifier("neutrino", "particle/ember"));
        }));

        ParticleFactoryRegistry.getInstance().register(ParticleRegistry.EMBER, EmberParticle.Factory::new);

    }
}
