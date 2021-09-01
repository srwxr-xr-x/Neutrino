package com.frostwizard4.Neutrino.Artifacts;

import com.frostwizard4.Neutrino.LightningAccess;
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

import static com.frostwizard4.Neutrino.SoundRegister.LIGHTNING_ROD_ACTIVATE;

public class LightningRodArtifact extends Item {
    public LightningRodArtifact(Settings settings) {
        super(settings);
    }
    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity playerEntity, Hand hand) {
        //Play Sound
        playerEntity.playSound(LIGHTNING_ROD_ACTIVATE, 1.0F, 1.0F);
        //Summon Lightning
        ((LightningAccess) playerEntity).neutrino$setPlayer(playerEntity);
        ((LightningAccess) playerEntity).neutrino$summonLightning();

        //Set 10 second Cooldown
        playerEntity.getItemCooldownManager().set(NeutrinoMain.LIGHTNING_ROD_ARTIFACT, 200);
        return TypedActionResult.success(playerEntity.getStackInHand(hand));
    }
    @Override
    public void appendTooltip(ItemStack itemStack, World world, List<Text> tooltip, TooltipContext tooltipContext) {
        tooltip.add(new TranslatableText("item.neutrino.lightning_rod.tooltip").formatted(Formatting.AQUA));
        tooltip.add(new TranslatableText("item.neutrino.lightning_rod.tooltip2").formatted(Formatting.AQUA));
    }
}
