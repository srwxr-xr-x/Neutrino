package com.frostwizard4.Neutrino.Artifacts;

import com.frostwizard4.Neutrino.NeutrinoMain;
import net.minecraft.client.item.TooltipContext;
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

import static com.frostwizard4.Neutrino.SoundRegister.EAT_DEATH_CAP_MUSHROOM;

public class UpdraftTomeArtifact extends Item {
    public UpdraftTomeArtifact(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        //Play Sound
        playerEntity.playSound(EAT_DEATH_CAP_MUSHROOM, 1.0F, 1.0F);
        //Apply Effects

        //Set 30 second Cooldown
        playerEntity.getItemCooldownManager().set(NeutrinoMain.DEATH_CAP_MUSHROOM, 600);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.neutrino.updraft_tome.tooltip").formatted(Formatting.AQUA));
        tooltip.add(new TranslatableText("item.neutrino.updraft_tome.tooltip2").formatted(Formatting.AQUA));
        tooltip.add(new TranslatableText("item.neutrino.updraft_tome.tooltip3").formatted(Formatting.AQUA));
    }
}
