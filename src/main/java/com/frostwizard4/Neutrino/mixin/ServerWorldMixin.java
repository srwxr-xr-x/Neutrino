package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.blocks.AlarmClock;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.server.world.SleepManager;
import net.minecraft.util.profiler.Profiler;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.GameRules;
import net.minecraft.world.MutableWorldProperties;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin extends World implements StructureWorldAccess {
    protected ServerWorldMixin(MutableWorldProperties properties, RegistryKey<World> registryRef, DimensionType dimensionType, Supplier<Profiler> profiler, boolean isClient, boolean debugWorld, long seed) {
        super(properties, registryRef, dimensionType, profiler, isClient, debugWorld, seed);
    }

    @Shadow protected abstract void wakeSleepingPlayers();

    @Shadow @Final private SleepManager sleepManager;

    @Shadow @Final List<ServerPlayerEntity> players;

    @Shadow public abstract void setTimeOfDay(long timeOfDay);

    @Shadow protected abstract void resetWeather();


    @Inject(method = "tick", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;setTimeOfDay(J)V", shift = At.Shift.AFTER))
    public void neutrino$tickSleeping(BooleanSupplier shouldKeepTicking, CallbackInfo ci) {
        int i;
        i = this.getGameRules().getInt(GameRules.PLAYERS_SLEEPING_PERCENTAGE);
        if (this.sleepManager.canSkipNight(i) && this.sleepManager.canResetTime(i, this.players)) {
            if (this.getGameRules().getBoolean(GameRules.DO_DAYLIGHT_CYCLE)) {
                long j = this.properties.getTimeOfDay() + AlarmClock.getTimeToWake();
                this.setTimeOfDay(j - j % AlarmClock.getTimeToWake());
            }

            this.wakeSleepingPlayers();
            if (this.getGameRules().getBoolean(GameRules.DO_WEATHER_CYCLE)) {
                this.resetWeather();
            }
        }
    }
}
