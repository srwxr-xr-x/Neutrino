package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.registry.StatusEffectRegistry;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.MilkBucketItem;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.stat.Stats;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(MilkBucketItem.class)
public class MilkBucketItemOverride extends Item {

    public MilkBucketItemOverride(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        if (user instanceof ServerPlayerEntity) {
            ServerPlayerEntity serverPlayerEntity = (ServerPlayerEntity)user;
            Criteria.CONSUME_ITEM.trigger(serverPlayerEntity, stack);
            serverPlayerEntity.incrementStat(Stats.USED.getOrCreateStat(this));
        }

        if (user instanceof PlayerEntity && !((PlayerEntity)user).getAbilities().creativeMode) {
            stack.decrement(1);
        }

        if (!world.isClient) {
            if(user.hasStatusEffect(StatusEffectRegistry.SNAKE_POISON)) {
                user.clearStatusEffects();
                user.addStatusEffect(new StatusEffectInstance(StatusEffectRegistry.SNAKE_POISON,100000,1));
            } else {
                user.clearStatusEffects();
            }
        }

        return stack.isEmpty() ? new ItemStack(Items.BUCKET) : stack;
    }
}
