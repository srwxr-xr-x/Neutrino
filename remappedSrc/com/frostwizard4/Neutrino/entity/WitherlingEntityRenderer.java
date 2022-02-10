package com.frostwizard4.Neutrino.entity;

import net.minecraft.client.render.entity.EntityRendererFactory;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class WitherlingEntityRenderer extends GeoEntityRenderer<WitherlingEntity>
{
    public WitherlingEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx, new WitherlingModel());
        this.shadowRadius = 0.4F;
    }
}