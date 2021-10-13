package com.frostwizard4.Neutrino.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EndermiteEntity;
import net.minecraft.item.*;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(ChorusFruitItem.class)
public class ChorusFruitItemMixin extends Item {
    int neutrino$rndEndermite = 0;

    public ChorusFruitItemMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "finishUsing", at = @At("RETURN"))
    public void neutrino$spawnEndermite(ItemStack stack, World world, LivingEntity user, CallbackInfoReturnable<ItemStack> cir) {
        for(int i = 0; i < 50; i++) {
            neutrino$rndEndermite = (int) (Math.random() * (100 - 1 + 1) + 1);
        }
        if (neutrino$rndEndermite > 10 && neutrino$rndEndermite < 25 && world.getGameRules().getBoolean(GameRules.DO_MOB_SPAWNING)) {
            EndermiteEntity endermiteEntity = (EndermiteEntity) EntityType.ENDERMITE.create(world);
            endermiteEntity.refreshPositionAndAngles(user.getX(), user.getY(), user.getZ(), user.getYaw(), user.getPitch());
            world.spawnEntity(endermiteEntity);
        }
    }
}
