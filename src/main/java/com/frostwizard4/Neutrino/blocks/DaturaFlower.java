package com.frostwizard4.Neutrino.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.FlowerBlock;
import net.minecraft.entity.AreaEffectCloudEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.PiglinBrain;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.potion.Potions;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;

public class DaturaFlower extends FlowerBlock {
    public DaturaFlower(StatusEffect suspiciousStewEffect, int effectDuration, Settings settings) {
        super(suspiciousStewEffect, effectDuration, settings);
    }

    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        this.spawnBreakParticles(world, player, pos, state);
        if (state.isIn(BlockTags.GUARDED_BY_PIGLINS)) {
            PiglinBrain.onGuardedBlockInteracted(player, false);
        }
        AreaEffectCloudEntity areaEffectCloudEntity = new AreaEffectCloudEntity(world, pos.getX(), pos.getY(), pos.getZ());

        areaEffectCloudEntity.setRadius(3.0F);
        areaEffectCloudEntity.setRadiusOnUse(-0.5F);
        areaEffectCloudEntity.setWaitTime(10);
        areaEffectCloudEntity.setRadiusGrowth(-areaEffectCloudEntity.getRadius() / (float) areaEffectCloudEntity.getDuration());
        areaEffectCloudEntity.setPotion(Potions.POISON);
        areaEffectCloudEntity.addEffect(new StatusEffectInstance(StatusEffects.POISON));

        if (world.isDay()) {
            world.spawnEntity(areaEffectCloudEntity);
        }
        world.emitGameEvent(player, GameEvent.BLOCK_DESTROY, pos);
    }

}
