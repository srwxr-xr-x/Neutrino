//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.frostwizard4.Neutrino.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EntityType.EntityFactory;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class NeutrinoEntityRegistryBuilder<E extends Entity> {
    private static Identifier name;
    private EntityFactory<E> entityFactory;
    private SpawnGroup category;
    private int trackingDistance;
    private int updateIntervalTicks;
    private boolean alwaysUpdateVelocity;
    private int primaryColor;
    private int secondaryColor;
    private EntityDimensions dimensions;

    public NeutrinoEntityRegistryBuilder() {
    }

    public static <E extends Entity> NeutrinoEntityRegistryBuilder<E> createBuilder(Identifier nameIn) {
        name = nameIn;
        return new NeutrinoEntityRegistryBuilder<>();
    }

    public NeutrinoEntityRegistryBuilder<E> entity(EntityFactory<E> entityFactory) {
        this.entityFactory = entityFactory;
        return this;
    }

    public NeutrinoEntityRegistryBuilder<E> category(SpawnGroup category) {
        this.category = category;
        return this;
    }

    public NeutrinoEntityRegistryBuilder<E> tracker(int trackingDistance, int updateIntervalTicks,
                                            boolean alwaysUpdateVelocity) {
        this.trackingDistance = trackingDistance;
        this.updateIntervalTicks = updateIntervalTicks;
        this.alwaysUpdateVelocity = alwaysUpdateVelocity;
        return this;
    }

    public NeutrinoEntityRegistryBuilder<E> egg(int primaryColor, int secondaryColor) {
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;
        return this;
    }

    public NeutrinoEntityRegistryBuilder<E> dimensions(EntityDimensions size) {
        this.dimensions = size;
        return this;
    }

    @SuppressWarnings({ "unchecked" })
    public EntityType<E> build() {
        EntityType.Builder<E> entityBuilder = EntityType.Builder.create(this.entityFactory, this.category)
                .setDimensions(this.dimensions.width, this.dimensions.height);
        if (this.alwaysUpdateVelocity && this.updateIntervalTicks != 0 & this.trackingDistance != 0) {
            FabricEntityTypeBuilder.create(this.category, this.entityFactory).dimensions(this.dimensions)
                    .trackRangeBlocks(this.trackingDistance).trackedUpdateRate(this.updateIntervalTicks)
                    .forceTrackedVelocityUpdates(this.alwaysUpdateVelocity).build();
        }

        EntityType<E> entityType = Registry.register(Registry.ENTITY_TYPE, name, entityBuilder.build(name.getPath()));
            Registry.register(Registry.ITEM, new Identifier(name.getNamespace(), String.format("%s_spawn_egg", name.getPath())), new SpawnEggItem((EntityType<? extends MobEntity>) entityType, 3224639,
                    2105125, (new Item.Settings()).group(ItemGroup.MISC)));

        return entityType;
    }

}
