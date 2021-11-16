package com.frostwizard4.Neutrino.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AlpacaEntityRenderer extends GeoEntityRenderer<AlpacaEntity>
{
    public AlpacaEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new AlpacaModel());
        this.shadowRadius = 0.7F;
    }
}