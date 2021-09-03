package com.frostwizard4.Neutrino.Artifacts;

import com.frostwizard4.Neutrino.NeutrinoMain;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;

import java.util.List;

import static com.frostwizard4.Neutrino.SoundRegister.ENCHANTERS_TOME_ACTIVATE;

public class EnchantersTomeArtifact extends Item {
    public EnchantersTomeArtifact(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        //Play Sound
        playerEntity.playSound(ENCHANTERS_TOME_ACTIVATE, 1.0F, 1.0F);
        //Apply Effects to the Closest Players
        world.getClosestPlayer(playerEntity,15).addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,200, 2));
        world.getClosestPlayer(playerEntity,15).addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 200,1));
        world.getClosestPlayer(playerEntity,15).addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE,200, 2));
        //Add 25 second Cooldown
        playerEntity.getItemCooldownManager().set(NeutrinoMain.ENCHANTERS_TOME, 500);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.neutrino.enchanters_tome.tooltip").formatted(Formatting.AQUA));
        tooltip.add(new TranslatableText("item.neutrino.enchanters_tome.tooltip2").formatted(Formatting.AQUA));
        tooltip.add(new TranslatableText("item.neutrino.enchanters_tome.tooltip3").formatted(Formatting.AQUA));
    }

}
