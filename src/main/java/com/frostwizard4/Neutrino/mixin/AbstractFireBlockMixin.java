package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.registry.ParticleRegistry;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(AbstractFireBlock.class)
public abstract class AbstractFireBlockMixin {

    @Shadow protected abstract boolean isFlammable(BlockState var1);

    @Inject(at = @At("TAIL"), method = "randomDisplayTick")
    public void addEmbers(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        int i;

        if (world.getTime() % 2L == 0L) {
            if (this.isFlammable(world.getBlockState(pos.south()))) {
                for (i = 0; i < 2; ++i) {
                    double d = (double) pos.getX() + random.nextDouble();
                    double e = (double) pos.getY() + random.nextDouble();
                    double f = (double) (pos.getZ() + 1) - random.nextDouble() * (double) 0.1f;
                    world.addParticle(ParticleRegistry.EMBER, d, e, f, 0.0, 0.2, 0.0);
                }
            }
            if (this.isFlammable(world.getBlockState(pos.west()))) {
                for (i = 0; i < 1; ++i) {
                    double d = (double) pos.getX() + random.nextDouble() * (double) 0.1f;
                    double e = (double) pos.getY() + random.nextDouble();
                    double f = (double) pos.getZ() + random.nextDouble();
                    world.addParticle(ParticleRegistry.EMBER, d, e, f, 0.0, 0.2, 0.0);
                }
            }
            if (this.isFlammable(world.getBlockState(pos.east()))) {
                for (i = 0; i < 1; ++i) {
                    double d = (double) (pos.getX() + 1) - random.nextDouble() * (double) 0.1f;
                    double e = (double) pos.getY() + random.nextDouble();
                    double f = (double) pos.getZ() + random.nextDouble();
                    world.addParticle(ParticleRegistry.EMBER, d, e, f, 0.0, 0.2, 0.0);
                }
            }
            if (this.isFlammable(world.getBlockState(pos.north()))) {
                for (i = 0; i < 1; ++i) {
                    double d = (double) pos.getX() + random.nextDouble();
                    double e = (double) pos.getY() + random.nextDouble();
                    double f = (double) pos.getZ() + random.nextDouble() * (double) 0.1f;
                    world.addParticle(ParticleRegistry.EMBER, d, e, f, 0.0, 0.2, 0.0);
                }
            }
        }
    }
}