package com.frostwizard4.Neutrino.artifacts;

import com.frostwizard4.Neutrino.NeutrinoMain;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

import static com.frostwizard4.Neutrino.registry.SoundRegister.UPDRAFT_TOME_ACTIVATE;

public class UpdraftTomeArtifact extends Item {
    public UpdraftTomeArtifact(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        //Play Sound
        playerEntity.playSound(UPDRAFT_TOME_ACTIVATE, 1.0F, 1.0F);
        //Add Levitation
        for (Entity e : world.getOtherEntities(playerEntity, Box.of(playerEntity.getPos(), 10, 10, 10))) {
            if (e instanceof MobEntity) {
                if (playerEntity.distanceTo(e) < 10) {
                    ((MobEntity) e).addStatusEffect(new StatusEffectInstance(StatusEffects.LEVITATION, 20, 15));
                }
            }
        }
        //Set 10 second Cooldown
        playerEntity.getItemCooldownManager().set(NeutrinoMain.UPDRAFT_TOME, 200);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.neutrino.updraft_tome.tooltip").formatted(Formatting.AQUA));
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.neutrino.updraft_tome.tooltip2"));
        } else {
            tooltip.add(new TranslatableText("item.neutrino.updraft_tome.shiftdown").formatted(Formatting.DARK_GRAY));
        }
    }
}
