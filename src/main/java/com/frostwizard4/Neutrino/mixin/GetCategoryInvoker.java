package com.frostwizard4.Neutrino.mixin;

import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Biome.class)
public interface GetCategoryInvoker {
    @Invoker("getCategory")
    public Biome.Category invokeGetCategory();
}