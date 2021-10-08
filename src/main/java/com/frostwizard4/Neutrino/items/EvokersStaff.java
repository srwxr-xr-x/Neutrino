package com.frostwizard4.Neutrino.items;

import com.frostwizard4.Neutrino.misc.SummonEvokerFangs;
import com.frostwizard4.Neutrino.NeutrinoMain;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.List;

public class EvokersStaff extends Item {
    public EvokersStaff(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        //Play Sound
        playerEntity.playSound(SoundEvents.ENTITY_EVOKER_FANGS_ATTACK, 1.0F, 1.0F);
        //Summon Evoker Fangs
        SummonEvokerFangs.summonFangs(playerEntity);
        //Set 20 second Cooldown
        playerEntity.getItemCooldownManager().set(NeutrinoMain.EVOKERS_STAFF, 300);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.neutrino.evokers_staff.tooltip").formatted(Formatting.AQUA));

        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.neutrino.evokers_staff.tooltip2"));
            tooltip.add(new TranslatableText("item.neutrino.evokers_staff.tooltip3"));
        } else {
            tooltip.add(new TranslatableText("item.neutrino.evokers_staff.shiftdown").formatted(Formatting.DARK_GRAY));
        }
    }
}
