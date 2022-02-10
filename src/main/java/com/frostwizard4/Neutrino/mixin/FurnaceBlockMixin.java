package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.registry.ItemRegistry;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlastFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(FurnaceBlock.class)
public abstract class FurnaceBlockMixin extends AbstractFurnaceBlock {
    protected FurnaceBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("HEAD"), method = "randomDisplayTick")
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        PlayerEntity playerEntity = world.getClosestPlayer((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 10.0, false);

        if (playerEntity != null && playerEntity.getMainHandStack().isOf(ItemRegistry.TANZANITE_CRYSTAL)) {
            for (int i = -2; i <= 2; ++i) {
                for (int j = -2; j <= 2; ++j) {
                    if (i > -2 && i < 2 && j == -1) {
                            j = 2;
                    }
                    if (random.nextInt(16) == 0) {
                        for (int k = 0; k <= 1; ++k) {
                            world.addParticle(ParticleTypes.ENCHANT, (double) pos.getX() + 0.5D, (double) pos.getY() + 2.0D, (double) pos.getZ() + 0.5D, (double) ((float) i + random.nextFloat()) - 0.5D, (double) ((float) k - random.nextFloat() - 1.0F), (double) ((float) j + random.nextFloat()) - 0.5D);
                        }
                    }
                }
            }
        }
    }
}
@Mixin(BlastFurnaceBlock.class)
abstract class BlastFurnaceBlockMixin extends AbstractFurnaceBlock {
    protected BlastFurnaceBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(at = @At("HEAD"), method = "randomDisplayTick")
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random, CallbackInfo ci) {
        PlayerEntity playerEntity = world.getClosestPlayer((double)pos.getX() + 0.5, (double)pos.getY() + 0.5, (double)pos.getZ() + 0.5, 10.0, false);

        if (playerEntity != null && playerEntity.getMainHandStack().isOf(ItemRegistry.TANZANITE_CRYSTAL)) {
            for (int i = -2; i <= 2; ++i) {
                for (int j = -2; j <= 2; ++j) {
                    if (i > -2 && i < 2 && j == -1) {
                        j = 2;
                    }
                    if (random.nextInt(16) == 0) {
                        for (int k = 0; k <= 1; ++k) {
                            world.addParticle(ParticleTypes.ENCHANT, (double) pos.getX() + 0.5D, (double) pos.getY() + 2.0D, (double) pos.getZ() + 0.5D, (double) ((float) i + random.nextFloat()) - 0.5D, (double) ((float) k - random.nextFloat() - 1.0F), (double) ((float) j + random.nextFloat()) - 0.5D);
                        }
                    }
                }
            }
        }
    }
}

