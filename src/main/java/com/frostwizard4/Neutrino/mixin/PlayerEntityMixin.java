package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.NeutrinoMain;
import com.frostwizard4.Neutrino.PlayerEntityAccess;
import com.frostwizard4.Neutrino.misc.Config;
import com.frostwizard4.Neutrino.registry.ItemRegistry;
import net.minecraft.entity.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.PhantomEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.SpawnHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin extends LivingEntity implements PlayerEntityAccess {
    protected PlayerEntityMixin(EntityType<? extends LivingEntity> entityType, World world) {
        super(entityType, world);
    }

    private float neutrino$boomPowerCounter = 0;
    private float neutrino$soulPouchCounter = 0;

    @Override
    public float neutrino$getPowerCount() {
        return neutrino$boomPowerCounter;
    }

    public float neutrino$getSoulPouchCount() { return neutrino$soulPouchCounter; }

    public void neutrino$setPowerCount(float newValue) {
        neutrino$boomPowerCounter = newValue;
    }

    public void neutrino$setSoulPouchCount(float newValue) {
        neutrino$soulPouchCounter = newValue;
    }

    @Shadow
    protected abstract boolean isOnSoulSpeedBlock();

    @Shadow public abstract void sendMessage(Text message, boolean actionBar);

    @Inject(at = @At("HEAD"), method = "tick()V")
    private void neutrino$checkHolding(CallbackInfo ci) {
        if (getMainHandStack().isOf(ItemRegistry.HARVESTER) || getMainHandStack().isOf(ItemRegistry.LIGHTNING_ROD_ARTIFACT)) {
            if (world.isClient()) {
                sendMessage(Text.of("Soul Level: " + (int) neutrino$boomPowerCounter / 10), true);
            }
        }
        if (isOnSoulSpeedBlock()) {
            if (getMainHandStack().isOf(ItemRegistry.SOUL_POUCH)) {
                displaySoulSpeedEffects();
                if (neutrino$soulPouchCounter >= 3000) {
                    neutrino$soulPouchCounter = 3000;
                    if (world.isClient()) {
                        //CheckHolding.sendBundleChatMessage(neutrino$soulPouchCounter);
                        sendMessage(Text.of("Soul Pouch Level: " + (int) neutrino$soulPouchCounter / 10), true);

                    }
                } else {
                    if (getMainHandStack().isOf(ItemRegistry.SOUL_POUCH)) {
                        neutrino$soulPouchCounter++;
                        if (world.isClient()) {
                            //CheckHolding.sendBundleChatMessage(neutrino$soulPouchCounter);
                            sendMessage(Text.of("Soul Pouch Level: " + (int) neutrino$soulPouchCounter / 10), true);

                        }
                    }
                }
            }
            if (getMainHandStack().isOf(ItemRegistry.HARVESTER) || getMainHandStack().isOf(ItemRegistry.LIGHTNING_ROD_ARTIFACT) || getMainHandStack().isOf(ItemRegistry.SOUL_HEALER)) {
                displaySoulSpeedEffects();
                if (neutrino$boomPowerCounter >= 1500) {
                    neutrino$boomPowerCounter = 1500;
                    if (world.isClient()) {
                        //CheckHolding.sendChatMessage(neutrino$boomPowerCounter);
                        sendMessage(Text.of("Soul Level: " + (int) neutrino$boomPowerCounter / 10), true);

                    }
                } else {
                    neutrino$boomPowerCounter++;
                    if (world.isClient()) {
                        //CheckHolding.sendChatMessage(neutrino$boomPowerCounter);
                        sendMessage(Text.of("Soul Level: " + (int) neutrino$boomPowerCounter / 10), true);
                    }
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "tick()V")
    private void neutrino$freezePlayer(CallbackInfo ci) {
        if(getY() >= 175 && !(getEquippedStack(EquipmentSlot.CHEST).isOf(ItemRegistry.ALPACA_FUR_SWEATER)) && Config.lines.get(5).endsWith("On")) {
            if (getY() >= 230 && !(getEquippedStack(EquipmentSlot.CHEST).isOf(ItemRegistry.ALPACA_FUR_SWEATER))) {
                this.setFrozenTicks(200);
                this.damage(DamageSource.FREEZE, 0.5F);
            }
            this.setFrozenTicks(150);

        }
        if(world.getBiome(getBlockPos()).isCold(getBlockPos()) && !(getEquippedStack(EquipmentSlot.CHEST).isOf(ItemRegistry.ALPACA_FUR_SWEATER)) && Config.lines.get(5).endsWith("On")) {
            if(world.isRaining() || world.isThundering() && !(getEquippedStack(EquipmentSlot.CHEST).isOf(ItemRegistry.ALPACA_FUR_SWEATER))) {
                this.setFrozenTicks(200);
                this.damage(DamageSource.FREEZE, 0.5F);
            }
            this.setFrozenTicks(150);
        }
    }

    @Inject(at = @At("HEAD"), method = "tick()V")
    private void neutrino$redSun(CallbackInfo ci) {
        Random random = new Random();
        boolean isRedSun = false;
        BlockPos blockPos2;
        BlockPos blockPos3;
        BlockPos blockPos = this.getBlockPos();


        if(world.getTimeOfDay() == 23000) {
            if (random.nextInt(10) == 2) {
                isRedSun = true;
            }
        }
        if(isRedSun) {
            isRedSun = false;
            this.sendMessage(Text.of("§c§o§lA Red Sun rises...."), true);
            blockPos2 = blockPos.east(-10 + random.nextInt(21)).south(-10 + random.nextInt(21));
            blockPos3 = blockPos.west(-10 + random.nextInt(21)).north(-10 + random.nextInt(21));

            for (int spawnNumber = 0; spawnNumber < 5; ++spawnNumber) {
                ZombieEntity zombieEntity = EntityType.ZOMBIE.create(world);
                if (zombieEntity != null) {
                    zombieEntity.refreshPositionAndAngles(blockPos2, 0.0F, 0.0F);
                }
                world.spawnEntity(zombieEntity);
            }
            for (int spawnNumber = 0; spawnNumber < 5; ++spawnNumber) {
                ZombieEntity zombieEntity = EntityType.ZOMBIE.create(world);
                if (zombieEntity != null) {
                    zombieEntity.refreshPositionAndAngles(blockPos3, 0.0F, 0.0F);
                }
                world.spawnEntity(zombieEntity);
            }
        }
    }
}





