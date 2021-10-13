package com.frostwizard4.Neutrino.entity;

import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;

public class EntityRegistry {
    public static final EntityType<RatEntity> RAT = buildEntity(RatEntity::new,
            .6F, 0.4F, SpawnGroup.MONSTER);


    public static <T extends Entity> EntityType<T> buildEntity(EntityType.EntityFactory<T> entity,
                                                               float width, float height, SpawnGroup group) {
        if (FabricLoader.getInstance().isDevelopmentEnvironment()) {
            return NeutrinoEntityRegistryBuilder.<T>createBuilder(new Identifier("neutrino", "rat")).entity(entity)
                    .category(group).dimensions(EntityDimensions.changing(width, height)).build();
        }
        return null;
    }

}