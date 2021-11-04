package com.frostwizard4.Neutrino.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DesertSerpentEntityRenderer extends GeoEntityRenderer<DesertSerpentEntity>
{
    public DesertSerpentEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new DesertSerpentModel());
        this.shadowRadius = 0.5F;
    }
}