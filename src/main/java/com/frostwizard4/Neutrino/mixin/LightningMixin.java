package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.LightningAccess;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(PlayerEntity.class)
public abstract class LightningMixin extends LivingEntity implements LightningAccess {
    protected LightningMixin(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    private double neutrino$x;
    private double neutrino$y;
    private double neutrino$z;
    private PlayerEntity player;
    private MinecraftClient client = MinecraftClient.getInstance();


    @Override
    public void neutrino$setPlayer(PlayerEntity playerEntity) {
        player = playerEntity;
    }

    private void neutrino$locateX() {
        for (Entity e : client.world.getEntities()) {
            if (e instanceof MobEntity) {
                if (client.player.distanceTo(e) < 10) {
                    neutrino$x = e.getX();
                }
            }
        }
    }

    private void neutrino$locateY() {
        for (Entity e : client.world.getEntities()) {
            if (e instanceof MobEntity) {
                if (client.player.distanceTo(e) < 10) {
                    neutrino$y = e.getY();
                }
            }
        }
    }

    private void neutrino$locateZ() {
        for (Entity e : client.world.getEntities()) {
            if (e instanceof MobEntity) {
                if (client.player.distanceTo(e) < 10) {
                    neutrino$z = e.getZ();
                }
            }
        }
    }

    @Override
    public void neutrino$summonLightning() {
        neutrino$locateX();
        neutrino$locateY();
        neutrino$locateZ();
        if (player.getX() + 10 >= neutrino$x && player.getY() + 10 >= neutrino$y && player.getZ() + 10 >= neutrino$z) {
            if (world.isRaining()) {
                if (world.isThundering()) {
                    LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(this.world);
                    lightningEntity.refreshPositionAfterTeleport(neutrino$x, neutrino$y, neutrino$z);
                    this.world.spawnEntity(lightningEntity);
                }
                LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(this.world);
                lightningEntity.refreshPositionAfterTeleport(neutrino$x, neutrino$y, neutrino$z);
                this.world.spawnEntity(lightningEntity);
            }
            LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(this.world);
            lightningEntity.refreshPositionAfterTeleport(neutrino$x, neutrino$y, neutrino$z);
            this.world.spawnEntity(lightningEntity);
        }
    }
}