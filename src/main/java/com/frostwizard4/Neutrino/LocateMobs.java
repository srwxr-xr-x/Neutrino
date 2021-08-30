package com.frostwizard4.Neutrino;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;

public class LocateMobs extends PathAwareEntity {
    LivingEntity livingEntity = this;

    double x;
    double y;
    double z;

    protected LocateMobs(EntityType<? extends PathAwareEntity> entityType, World world) {
        super(entityType, world);
    }

    public double locateX() {
        return x = livingEntity.getPos().x;
    }
    public double locateY() {
        return y = livingEntity.getPos().y;
    }
    public double locateZ() {
        return z = livingEntity.getPos().z;
    }
    public void lightningMobs(PlayerEntity player) {
        if(player.getX() + 10 >= locateX() && player.getY() + 10 >= locateY() && player.getZ() + 10 >= locateZ()) {

            LightningEntity lightningEntity = (LightningEntity) EntityType.LIGHTNING_BOLT.create(this.world);
            lightningEntity.refreshPositionAfterTeleport(locateX(), locateY(), locateZ());
            this.world.spawnEntity(lightningEntity);
        }
    }


}
