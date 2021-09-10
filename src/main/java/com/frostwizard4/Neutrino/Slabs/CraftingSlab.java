package com.frostwizard4.Neutrino.Slabs;


import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandlerContext;
import net.minecraft.screen.SimpleNamedScreenHandlerFactory;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;


public class CraftingSlab extends SlabBlock {
    public CraftingSlab(Settings settings) {
        super(settings);
    }

    private static final Text title = new TranslatableText("container.crafting", new Object[0]);

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {

        if (!world.isClient) {
            player.openHandledScreen(screen(world, pos));
            return ActionResult.SUCCESS;
        } else {
            player.incrementStat(Stats.INTERACT_WITH_CRAFTING_TABLE);
            return ActionResult.CONSUME;
        }
    }
    private NamedScreenHandlerFactory screen(World world, BlockPos pos) {
        return new SimpleNamedScreenHandlerFactory(
                (i, inv, player)
                        ->
                        new CraftingSlabContainer(i, inv,
                                ScreenHandlerContext.create(world, pos)),
                title
        );
    }
}

