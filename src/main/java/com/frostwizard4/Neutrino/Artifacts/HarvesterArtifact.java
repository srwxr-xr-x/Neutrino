package com.frostwizard4.Neutrino.Artifacts;

import com.frostwizard4.Neutrino.PlayerEntityAccess;
import net.minecraft.client.gui.screen.Screen;
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
import net.minecraft.world.explosion.Explosion;

import java.util.List;

import static com.frostwizard4.Neutrino.SoundRegister.HARVESTER_ACTIVATE;

public class HarvesterArtifact extends Item {
    public HarvesterArtifact(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        //Play Harvester Sound
        playerEntity.playSound(HARVESTER_ACTIVATE, 1.0F, 1.0F);
        //Create the Explosion
        world.createExplosion(playerEntity, playerEntity.getX(), playerEntity.getY(), playerEntity.getZ(), ((PlayerEntityAccess) playerEntity).neutrino$getPowerCount() / 100, false, Explosion.DestructionType.NONE);
        ((PlayerEntityAccess) playerEntity).neutrino$setPowerCount(0f);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }

    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.neutrino.harvester.tooltip").formatted(Formatting.AQUA));
        if(Screen.hasShiftDown()) {
            tooltip.add(new TranslatableText("item.neutrino.harvester.tooltip2"));
        } else {
            tooltip.add(new TranslatableText("item.neutrino.harvester.shiftdown"));
        }
    }
}
