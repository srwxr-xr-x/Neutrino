package com.frostwizard4.Neutrino.Artifacts;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

import static com.frostwizard4.Neutrino.Misc.SoundRegister.EAT_DEATH_CAP_MUSHROOM;

public class DeathCapArtifact extends Item {
    public DeathCapArtifact(Settings settings) {
        super(settings);
    }
    int rndDeath = 0;

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        for(int i = 0; i < 50; i++) {
            rndDeath = (int) (Math.random() * (100 - 1 + 1) + 1);
        }
        if(rndDeath > 15 && rndDeath < 30) {
            user.kill();
        }
        //Play Sound
        user.playSound(EAT_DEATH_CAP_MUSHROOM, 1.0F, 1.0F);
        //Apply Effects
        world.getClosestPlayer(user,15).addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,400, 2));
        world.getClosestPlayer(user,15).addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 400,1));
        //Set 30 second Cooldown
        return this.isFood() ? user.eatFood(world, stack) : stack;
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.neutrino.death_cap_mushroom.tooltip").formatted(Formatting.AQUA));

        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.neutrino.death_cap_mushroom.tooltip2"));
            tooltip.add(new TranslatableText("item.neutrino.death_cap_mushroom.tooltip3"));
        } else {
            tooltip.add(new TranslatableText("item.neutrino.death_cap_mushroom.shiftdown").formatted(Formatting.DARK_GRAY));
        }
    }
}
