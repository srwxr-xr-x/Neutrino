package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.LightningAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public abstract class LightningMixin extends LivingEntity implements LightningAccess {
    protected LightningMixin(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }
    private PlayerEntity player;

    @Override
    public void neutrino$setPlayer(PlayerEntity playerEntity) {
        player = playerEntity;
    }

    @Override
    public void neutrino$summonLightning() {
        if (world.isRaining()) {
            if (world.isThundering()) {
                for (Entity e : world.getOtherEntities(player, Box.of(player.getPos(), 10, 10, 10))) {
                    if (e instanceof MobEntity) {
                        if (player.distanceTo(e) < 10) {
                            LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(this.world);
                            lightningEntity.refreshPositionAfterTeleport(e.getX(), e.getY(), e.getZ());
                            this.world.spawnEntity(lightningEntity);
                        }
                    }
                }
            }
            for (Entity e : world.getOtherEntities(player, Box.of(player.getPos(), 10, 10, 10))) {
                if (e instanceof MobEntity) {
                    if (player.distanceTo(e) < 10) {
                        LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(this.world);
                        lightningEntity.refreshPositionAfterTeleport(e.getX(), e.getY(), e.getZ());
                        this.world.spawnEntity(lightningEntity);
                    }
                }
            }
        }
        for (Entity e : world.getOtherEntities(player, Box.of(player.getPos(), 10, 10, 10))) {
            if (e instanceof MobEntity) {
                if (player.distanceTo(e) < 10) {
                    LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(this.world);
                    lightningEntity.refreshPositionAfterTeleport(e.getX(), e.getY(), e.getZ());
                    this.world.spawnEntity(lightningEntity);
                }
            }
        }
    }
}
