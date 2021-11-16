package com.frostwizard4.Neutrino.entity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class DesertSerpentModel extends AnimatedGeoModel<DesertSerpentEntity> {
    @Override
    public Identifier getModelLocation(DesertSerpentEntity object)
    {
        return new Identifier("neutrino", "geo/desertserpent.geo.json");
    }

    @Override
    public Identifier getTextureLocation(DesertSerpentEntity object)
    {
        return new Identifier("neutrino", "textures/entity/desert_serpent.png");
    }

    @Override
    public Identifier getAnimationFileLocation(DesertSerpentEntity object)
    {
        return new Identifier("neutrino", "animations/desert_serpent.animation.json");
    }
}