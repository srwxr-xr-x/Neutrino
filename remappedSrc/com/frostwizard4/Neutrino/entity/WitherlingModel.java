package com.frostwizard4.Neutrino.entity;

import net.minecraft.util.Identifier;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class WitherlingModel extends AnimatedGeoModel<WitherlingEntity> {
    @Override
    public Identifier getModelLocation(WitherlingEntity object)
    {
        return new Identifier("neutrino", "geo/witherling.geo.json");
    }

    @Override
    public Identifier getTextureLocation(WitherlingEntity object)
    {
        return new Identifier("neutrino", "textures/entity/witherling.png");
    }

    @Override
    public Identifier getAnimationFileLocation(WitherlingEntity object)
    {
        return new Identifier("neutrino", "animations/witherling.animation.json");
    }
}