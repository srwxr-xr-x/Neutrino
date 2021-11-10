package com.frostwizard4.Neutrino.entity;

import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AlpacaModel extends AnimatedGeoModel<AlpacaEntity> {
    @Override
    public Identifier getModelLocation(AlpacaEntity object)
    {
        return new Identifier("neutrino", "geo/alpaca.geo.json");
    }

    @Override
    public Identifier getTextureLocation(AlpacaEntity object)
    {
        return new Identifier("neutrino", "textures/entity/alpaca.png");
    }

    @Override
    public Identifier getAnimationFileLocation(AlpacaEntity object)
    {
        return new Identifier("neutrino", "animations/alpaca.animation.json");
    }
}