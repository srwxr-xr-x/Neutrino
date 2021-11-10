package com.frostwizard4.Neutrino.entity;

import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RatModel extends AnimatedGeoModel<RatEntity> {
    @Override
    public Identifier getModelLocation(RatEntity object)
    {
        return new Identifier("neutrino", "geo/rat.geo.json");
    }

    @Override
    public Identifier getTextureLocation(RatEntity object)
    {
        if("Burrito".equals(Formatting.strip(object.getName().getString()))) {
            return new Identifier("neutrino", "textures/entity/mouse.png");
        } else {
            return new Identifier("neutrino", "textures/entity/rat_tail.png");
        }
    }

    @Override
    public Identifier getAnimationFileLocation(RatEntity object)
    {
        return new Identifier("neutrino", "animations/rat.animation.json");
    }
}