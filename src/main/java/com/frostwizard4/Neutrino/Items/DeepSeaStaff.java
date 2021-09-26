package com.frostwizard4.Neutrino.Items;

import com.frostwizard4.Neutrino.Misc.SummonEvokerFangs;
import com.frostwizard4.Neutrino.NeutrinoMain;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import static com.frostwizard4.Neutrino.Misc.SoundRegister.WAR_HORN_USE;

public class DeepSeaStaff extends Item {
    public DeepSeaStaff(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        //Play Sound
        playerEntity.playSound(SoundEvents.ENTITY_EVOKER_FANGS_ATTACK, 1.0F, 1.0F);
        //Apply Effects


        //Set 20 second Cooldown
        playerEntity.getItemCooldownManager().set(NeutrinoMain.EVOKERS_STAFF, 400);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
}
