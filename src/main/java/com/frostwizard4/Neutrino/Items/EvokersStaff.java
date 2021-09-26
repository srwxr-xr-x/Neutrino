package com.frostwizard4.Neutrino.Items;

import com.frostwizard4.Neutrino.Misc.SummonEvokerFangs;
import com.frostwizard4.Neutrino.NeutrinoMain;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.world.World;

import java.util.List;

import static com.frostwizard4.Neutrino.Misc.SoundRegister.WAR_HORN_USE;

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
        } else {
            tooltip.add(new TranslatableText("item.neutrino.evokers_staff.shiftdown").formatted(Formatting.DARK_GRAY));
        }
    }
}
