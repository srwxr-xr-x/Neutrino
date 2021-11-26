package com.frostwizard4.Neutrino.misc;

import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import net.minecraft.world.gen.decorator.DecoratorContext;
import net.minecraft.world.gen.decorator.NopeDecoratorConfig;

import java.util.Random;
import java.util.stream.Stream;

public class FarOreDecorator extends Decorator<NopeDecoratorConfig> {
    public FarOreDecorator(Codec<NopeDecoratorConfig> configCodec) {
        super(configCodec);
    }
    @Override
    public Stream<BlockPos> getPositions(DecoratorContext context, Random random, NopeDecoratorConfig config, BlockPos pos) {
        BlockPos spawnPos = context.getWorld().toServerWorld().getSpawnPos();
        if(spawnPos.getSquaredDistance(pos.getX(), pos.getY(), pos.getZ(), true) >= (10000*10000)) {
            return Stream.of(pos);
        }
        return Stream.empty();
    }
}
