package com.frostwizard4.Neutrino.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class EntityRegistry {

    public static final EntityType<RatEntity> RAT = Registry.register(Registry.ENTITY_TYPE, new Identifier("neutrino", "rat"),
            FabricEntityTypeBuilder.<RatEntity>create(SpawnGroup.MONSTER, RatEntity::new)
                    .spawnGroup(SpawnGroup.MONSTER)
                    .dimensions(EntityDimensions.fixed(0.6F, 0.4F))
                    .trackRangeBlocks(90)
                    .trackedUpdateRate(4)
                    .build());

    public static final EntityType<DuckEntity> DUCK = Registry.register(Registry.ENTITY_TYPE, new Identifier("neutrino", "duck"),
            FabricEntityTypeBuilder.<DuckEntity>create(SpawnGroup.CREATURE, DuckEntity::new)
                .dimensions(EntityDimensions.fixed(0.6F, 0.5F))
                .trackRangeBlocks(90)
                .trackedUpdateRate(4)
                .build());

    public static final EntityType<WitherlingEntity> WITHERLING = Registry.register(Registry.ENTITY_TYPE, new Identifier("neutrino", "witherling"),
            FabricEntityTypeBuilder.<WitherlingEntity>create(SpawnGroup.MONSTER, WitherlingEntity::new)//.spawnRestriction(SpawnRestriction.Location.ON_GROUND, Heightmap.Type.WORLD_SURFACE, WitherlingEntity::canSpawn)
                    .spawnGroup(SpawnGroup.MONSTER)
                    .specificSpawnBlocks(Blocks.SOUL_SAND, Blocks.SOUL_SOIL)
                    .dimensions(EntityDimensions.fixed(0.4F, 1.5F))
                    .trackRangeBlocks(90)
                    .trackedUpdateRate(4)
                    .fireImmune()
                    .build());
    public static final EntityType<AlpacaEntity> ALPACA = Registry.register(Registry.ENTITY_TYPE, new Identifier("neutrino", "alpaca"),
            FabricEntityTypeBuilder.<AlpacaEntity>create(SpawnGroup.CREATURE, AlpacaEntity::new)
                    .dimensions(EntityDimensions.fixed(0.9F, 1.87F))
                    .trackRangeBlocks(90)
                    .trackedUpdateRate(4)
                    .build());

    public static final Item RAT_SPAWN_EGG = Registry.register(Registry.ITEM, new Identifier("neutrino", "rat_spawn_egg"), new SpawnEggItem(RAT, 3224639, 2105125, new Item.Settings().group(ItemGroup.MISC)));
    public static final Item DUCK_SPAWN_EGG = Registry.register(Registry.ITEM, new Identifier("neutrino", "duck_spawn_egg"), new SpawnEggItem(DUCK, 4672575, 6308916, new Item.Settings().group(ItemGroup.MISC)));
    public static final Item ALPACA_SPAWN_EGG = Registry.register(Registry.ITEM, new Identifier("neutrino", "alpaca_spawn_egg"), new SpawnEggItem(ALPACA, 14928533, 12886649, new Item.Settings().group(ItemGroup.MISC)));

}