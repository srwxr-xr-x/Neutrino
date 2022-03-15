package com.frostwizard4.Neutrino.mixin;

import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.util.SpriteIdentifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashSet;

import static com.frostwizard4.Neutrino.registry.NeutrinoModelLoader.ELECTRICITY;

@Mixin(ModelLoader.class)
public class ModelLoaderMixin {
    @Inject(method = "method_24150", at = @At("HEAD"))
    private static void addTextures(HashSet<SpriteIdentifier> set, CallbackInfo ci) {
        set.add(ELECTRICITY);
    }
}