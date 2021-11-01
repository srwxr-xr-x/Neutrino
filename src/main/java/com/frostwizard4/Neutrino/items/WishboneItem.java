package com.frostwizard4.Neutrino.items;

import com.frostwizard4.Neutrino.registry.SoundRegister;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WishboneItem extends Item {
    public WishboneItem(Settings settings) {
        super(settings);
    }
    public ActionResult useOnBlock (ItemUsageContext itemUsageContext){
        World world = itemUsageContext.getWorld();
        if (itemUsageContext.getPlayer() != null) {
            itemUsageContext.getPlayer().playSound(SoundRegister.WISHBONE_SNAP, 1, 1);
            world.addBlockBreakParticles(itemUsageContext.getBlockPos(), Blocks.BONE_BLOCK.getDefaultState());
        }
        if (world.isClient) {
            return ActionResult.SUCCESS;
        } else {
            PlayerEntity itemUsageContextPlayer = itemUsageContext.getPlayer();
            if (itemUsageContextPlayer != null) {
                summonWolf(itemUsageContextPlayer, itemUsageContext.getBlockPos());
                if (!itemUsageContextPlayer.isCreative()) {
                    itemUsageContext.getStack().decrement(1);
                }
            }
        }
        return ActionResult.CONSUME;
    }

    public static void summonWolf(PlayerEntity entity, BlockPos blockPos){
        World world = entity.getEntityWorld();
        WolfEntity wolfEntity = EntityType.WOLF.create(world);
        if (wolfEntity != null) {
            wolfEntity.setOwner(entity);
            wolfEntity.refreshPositionAndAngles(blockPos.getX(), blockPos.getY() + 1, blockPos.getZ(), 0, 0);
            world.spawnEntity(wolfEntity);
        }
    }
}
