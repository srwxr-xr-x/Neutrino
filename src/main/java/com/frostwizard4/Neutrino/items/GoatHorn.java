package com.frostwizard4.Neutrino.items;

import com.frostwizard4.Neutrino.NeutrinoMain;
import com.frostwizard4.Neutrino.registry.ItemRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import static com.frostwizard4.Neutrino.registry.SoundRegister.WAR_HORN_USE;

public class GoatHorn extends Item {
    public GoatHorn(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        //Play Sound
        playerEntity.playSound(WAR_HORN_USE, 1.0F, 1.0F);
        //Apply Effects
        for (Entity e : world.getOtherEntities(playerEntity, Box.of(playerEntity.getPos(), 10, 10, 10))) {
            if (e instanceof MobEntity) {
                if (playerEntity.distanceTo(e) < 10) {
                    ((MobEntity) e).addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 250, 2));
                }
            }
        }
        //Set 20 second Cooldown
        playerEntity.getItemCooldownManager().set(ItemRegistry.GOAT_HORN, 400);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
