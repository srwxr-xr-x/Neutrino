package com.frostwizard4.Neutrino.Artifacts;

import com.frostwizard4.Neutrino.PlayerEntityAccess;
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
import net.minecraft.world.explosion.Explosion;

import java.util.List;

import static com.frostwizard4.Neutrino.SoundRegister.HARVESTER_ACTIVATE;

public class SoulPouchItem extends Item {
    public SoulPouchItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        //Play Sound
        playerEntity.playSound(SoundEvents.PARTICLE_SOUL_ESCAPE, 5.0f, 1.0f);
        //Set the Soul Power to the Soul Pouch's Power, then Set the Soul Pouch's power to 0
        ((PlayerEntityAccess) playerEntity).neutrino$setPowerCount( ((PlayerEntityAccess) playerEntity).neutrino$getSoulPouchCount() + ((PlayerEntityAccess) playerEntity).neutrino$getPowerCount());
        ((PlayerEntityAccess) playerEntity).neutrino$setSoulPouchCount(0);

        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.neutrino.harvester.tooltip").formatted(Formatting.AQUA));
        tooltip.add(new TranslatableText("item.neutrino.harvester.tooltip2").formatted(Formatting.AQUA));
    }
}
