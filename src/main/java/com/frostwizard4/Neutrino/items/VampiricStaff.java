package com.frostwizard4.Neutrino.items;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.HuskEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;
import net.minecraft.world.World;

import java.util.List;

public class VampiricStaff extends SwordItem {

    public VampiricStaff(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        attacker.setHealth(attacker.getHealth() + 0.5F);
        if(target.isDead() && (target.getType() != EntityType.ZOMBIE) && (target.getType() != EntityType.HUSK) && (target.getType() != EntityType.SKELETON) && (target.getType() != EntityType.CREEPER) && (target.getType() != EntityType.SKELETON_HORSE) && (target.getType() != EntityType.WITHER_SKELETON)  && (target.getType() != EntityType.ZOMBIE_HORSE)  && (target.getType() != EntityType.ZOMBIFIED_PIGLIN)  && (target.getType() != EntityType.ZOMBIE_VILLAGER)) {
            HuskEntity huskEntity = new HuskEntity(EntityType.HUSK, attacker.getEntityWorld());
            huskEntity.setPos(target.getX(), target.getY() + 1, target.getZ());
            attacker.getEntityWorld().spawnEntity(huskEntity);
        }
        return true;

    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.neutrino.vampiric_staff.tooltip").formatted(Formatting.AQUA));

        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.neutrino.vampiric_staff.tooltip2"));
        } else {
            tooltip.add(new TranslatableText("item.neutrino.vampiric_staff.shiftdown").formatted(Formatting.DARK_GRAY));
        }
    }
}
