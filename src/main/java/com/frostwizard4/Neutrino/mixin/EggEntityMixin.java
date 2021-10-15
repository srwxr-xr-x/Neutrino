package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.entity.DuckEntity;
import com.frostwizard4.Neutrino.entity.EntityRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.ChickenEntity;
import net.minecraft.entity.projectile.thrown.EggEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EggEntity.class)
public abstract class EggEntityMixin extends ThrownItemEntity {

    public EggEntityMixin(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "onCollision", at = @At("HEAD"))
    private void addDuck(HitResult hitResult, CallbackInfo ci) {
        super.onCollision(hitResult);
        if (!this.world.isClient) {
            if (this.random.nextInt(8) == 0) {
                int i = 1;
                if (this.random.nextInt(32) == 0) {
                    i = 4;
                }

                for(int j = 0; j < i; ++j) {

                    DuckEntity duckEntity = (DuckEntity) EntityRegistry.DUCK.create(this.world);
                    if (duckEntity != null) {
                        duckEntity.setBreedingAge(-24000);
                        duckEntity.refreshPositionAndAngles(this.getX(), this.getY(), this.getZ(), this.getYaw(), 0.0F);
                    }
                    this.world.spawnEntity(duckEntity);
                }
            }

            this.world.sendEntityStatus(this, (byte)3);
            this.discard();
        }
    }

}
