package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.NeutrinoMain;
import com.frostwizard4.Neutrino.registry.BlockRegistry;
import net.minecraft.block.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Random;

@Mixin(CropBlock.class)
public abstract class CropBlockMixin extends PlantBlock implements Fertilizable {

    @Shadow protected abstract int getAge(BlockState state);

    @Shadow public abstract int getMaxAge();

    @Shadow
    protected static float getAvailableMoisture(Block block, BlockView world, BlockPos pos) {
        return 0;
    }

    @Shadow public abstract BlockState withAge(int age);

    protected CropBlockMixin(Settings settings) {
        super(settings);
    }
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (world.getBaseLightLevel(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                float f = getAvailableMoisture(this, world, pos);
                if (random.nextInt((int)(25.0F / f) + 1) == 0) {
                    if(BlockPos.findClosest(pos, 10, 10, _pos -> world.getBlockState(_pos).isOf(BlockRegistry.SCARECROW)).isPresent()) {
                        if(i+3 < this.getMaxAge()) {
                            i+=3;
                        }
                        world.spawnParticles(ParticleTypes.HAPPY_VILLAGER,pos.getX(),pos.getY(),pos.getZ(),4,1,1,1,1);
                    world.setBlockState(pos, this.withAge(i + 1), Block.NOTIFY_LISTENERS);
                    }
                }
            }
        }
    }
}





