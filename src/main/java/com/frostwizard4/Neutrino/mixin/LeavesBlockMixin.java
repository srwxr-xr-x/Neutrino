package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.misc.Config;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

import static net.minecraft.state.property.Properties.PERSISTENT;


@Mixin(LeavesBlock.class)
public class LeavesBlockMixin extends Block {
    public LeavesBlockMixin(Settings settings) {
        super(settings);
    }

    @Override
    public void onEntityCollision(BlockState state, World world, BlockPos pos, Entity entity) {
        if(Config.lines.get(1).endsWith("On")) {
            entity.slowMovement(state, new Vec3d(0.95D, 0.99D, 0.95D));
        }
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        if(!state.get(PERSISTENT) && Config.lines.get(9).endsWith("On")) {
            return VoxelShapes.cuboid(0, 0, 0, 1, 1, 1);
        } else {
            return VoxelShapes.empty();
        }
    }
}
