package com.frostwizard4.Neutrino.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Blocks;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(Blocks.class)
public class BlocksMixin {
    @ModifyArg(method = "createLeavesBlock", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/LeavesBlock;<init>(Lnet/minecraft/block/AbstractBlock$Settings;)V"))
    private static AbstractBlock.Settings appendNewSettings(AbstractBlock.Settings original) {
        return original.noCollision();
    }
}
