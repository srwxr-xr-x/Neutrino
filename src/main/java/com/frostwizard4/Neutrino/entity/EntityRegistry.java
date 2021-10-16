package com.frostwizard4.Neutrino.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.Identifier;

public class EntityRegistry {
    public static final EntityType<RatEntity> RAT = buildEntity(RatEntity::new, RatEntity.class,
            .6F, 0.4F, SpawnGroup.MONSTER);
    public static final EntityType<DuckEntity> DUCK = buildEntity(DuckEntity::new, DuckEntity.class,
            .6F, 0.5F, SpawnGroup.CREATURE);

    public static <T extends Entity> EntityType<T> buildEntity(EntityType.EntityFactory<T> entity, Class<T> entityClass, float width, float height, SpawnGroup group) {
        String name = entityClass.getSimpleName().toLowerCase();

        return NeutrinoEntityRegistryBuilder.<T>createBuilder(new Identifier("neutrino", name)).entity(entity).category(group).dimensions(EntityDimensions.changing(width, height)).build();
    }

}