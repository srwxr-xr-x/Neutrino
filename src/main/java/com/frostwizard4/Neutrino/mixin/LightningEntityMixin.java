package com.frostwizard4.Neutrino.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LightningEntity.class)
public abstract class LightningEntityMixin extends Entity {
    int neutrino$rndStrike = 0;

    public LightningEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "spawnFire", at = @At("HEAD"))
    private void neutrino$createExplosion(int spreadAttempts, CallbackInfo ci) {
        for(int i = 0; i < 50; i++) {
            neutrino$rndStrike = (int) (Math.random() * (100 - 1 + 1) + 1);
        }
        if(neutrino$rndStrike > 10 && neutrino$rndStrike < 15) {
            world.createExplosion(this, this.getX(), this.getY(), this.getZ(), 4, Explosion.DestructionType.BREAK);
        }
    }
}
