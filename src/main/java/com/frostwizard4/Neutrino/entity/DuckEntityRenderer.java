package com.frostwizard4.Neutrino.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class DuckEntityRenderer extends GeoEntityRenderer<DuckEntity>
{
    public DuckEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new DuckModel());
        this.shadowRadius = 0.5F;
    }
}