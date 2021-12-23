package com.frostwizard4.Neutrino.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldView;

public class AlarmClock extends HorizontalFacingBlock {
    public AlarmClock(Settings settings) {
        super(settings);
    }
    private static long time = 1L;

    public static long getTimeToWake() {
        return time;
    }

    int i = 1;
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(i == 1 || i == 2) {
            player.sendMessage(Text.of("Wake Time set to: Noon"), true);
            time = 6000L;
        } else if(i == 3 || i == 4) {
            player.sendMessage(Text.of("Wake Time set to: Midnight"), true);
            time = 42000L;
        } else if(i == 5 || i == 6) {
            player.sendMessage(Text.of("Wake Time set to: Morning"), true);
            time = 1L;
        }
        i++;
        i = (i == 7) ? 1 : i;
        System.out.println(i);
        return ActionResult.SUCCESS;
    }
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.2375,0,0.39375,0.7625,0.6875,0.625);
    }
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return VoxelShapes.cuboid(0.2375,0,0.39375,0.7625,0.6875,0.625);
    }
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return VoxelShapes.cuboid(0.2375,0,0.39375,0.7625,0.6875,0.625);
    }
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return world.getBlockState(pos.down()).getMaterial().isSolid();
    }
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing().getOpposite());
    }
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return (BlockState)state.with(FACING, rotation.rotate((Direction)state.get(FACING)));
    }
}
