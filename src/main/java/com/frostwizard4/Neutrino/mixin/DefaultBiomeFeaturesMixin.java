package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.entity.EntityRegistry;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.feature.DefaultBiomeFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(DefaultBiomeFeatures.class)
public class DefaultBiomeFeaturesMixin {

    @Inject(method = "addPlainsMobs", at = @At("HEAD"))
    private static void addDuck(SpawnSettings.Builder builder, CallbackInfo ci) {
        builder.spawn(SpawnGroup.CREATURE, new SpawnSettings.SpawnEntry(EntityRegistry.DUCK, 10, 4, 4));
    }
    @Inject(method = "addMonsters", at = @At("HEAD"))
    private static void addRat(SpawnSettings.Builder builder, int zombieWeight, int zombieVillagerWeight, int skeletonWeight, CallbackInfo ci) {
        builder.spawn(SpawnGroup.MONSTER, new SpawnSettings.SpawnEntry(EntityRegistry.RAT, 100, 1, 2));
    }
}
