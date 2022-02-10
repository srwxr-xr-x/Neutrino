package com.frostwizard4.Neutrino.entity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DuckModel extends AnimatedGeoModel<DuckEntity> {
    @Override
    public Identifier getModelLocation(DuckEntity object)
    {
        return new Identifier("neutrino", "geo/duck.geo.json");
    }

    @Override
    public Identifier getTextureLocation(DuckEntity object)
    {
        return new Identifier("neutrino", "textures/entity/duck.png");
    }

    @Override
    public Identifier getAnimationFileLocation(DuckEntity object)
    {
        return new Identifier("neutrino", "animations/duck.animation.json");
    }
}