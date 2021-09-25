package com.frostwizard4.Neutrino.Artifacts;

import com.frostwizard4.Neutrino.NeutrinoMain;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

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
        world.getClosestPlayer(playerEntity,15).addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH,400, 2));
        world.getClosestPlayer(playerEntity,15).addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 400,1));
        world.getClosestPlayer(playerEntity,15).addStatusEffect(new StatusEffectInstance(StatusEffects.HASTE,400, 2));
        //Add 25 second Cooldown
        playerEntity.getItemCooldownManager().set(NeutrinoMain.ENCHANTERS_TOME, 500);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.neutrino.enchanters_tome.tooltip").formatted(Formatting.AQUA));

        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.neutrino.enchanters_tome.tooltip2"));
            tooltip.add(new TranslatableText("item.neutrino.enchanters_tome.tooltip3"));
            tooltip.add(new TranslatableText("item.neutrino.enchanters_tome.tooltip4"));

        } else {
            tooltip.add(new TranslatableText("item.neutrino.enchanters_tome.shiftdown").formatted(Formatting.DARK_GRAY));
        }
    }

}
