package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.PlayerEntityAccess;
import com.frostwizard4.Neutrino.misc.Config;
import com.frostwizard4.Neutrino.registry.ItemRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import org.spongepowered.asm.mixin.Final;
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
    int i = 0;
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

    @Shadow public abstract boolean damage(DamageSource source, float amount);

    @Shadow @Final private PlayerInventory inventory;

    @Shadow public abstract void playSound(SoundEvent sound, float volume, float pitch);

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
            if (random.nextInt(7) == 2) {
                isRedSun = true;
            }
        }
        if(isRedSun) {
            isRedSun = false;
            this.sendMessage(Text.of("§c§o§lA Red Sun rises...."), true);

            for (int spawnNumber = 0; spawnNumber < 10; ++spawnNumber) {
                blockPos2 = blockPos.east(-10 + random.nextInt(21)).south(-10 + random.nextInt(21));
                ZombieEntity zombieEntity = EntityType.ZOMBIE.create(world);
                if (zombieEntity != null) {
                    zombieEntity.refreshPositionAndAngles(blockPos2, 0.0F, 0.0F);
                }
                world.spawnEntity(zombieEntity);
            }
            for (int spawnNumber = 0; spawnNumber < 10; ++spawnNumber) {
                blockPos3 = blockPos.west(-10 + random.nextInt(21)).north(-10 + random.nextInt(21));
                CreeperEntity creeperEntity = EntityType.CREEPER.create(world);
                if (creeperEntity != null) {
                    creeperEntity.refreshPositionAndAngles(blockPos3, 0.0F, 0.0F);
                }
                world.spawnEntity(creeperEntity);
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "tick()V")
    private void neutrino$tickDesert(CallbackInfo ci) {
        if(!this.isTouchingWaterOrRain() && Config.lines.get(6).endsWith("On")) {
            if(world.getBiome(getBlockPos()).getCategory().equals(Biome.Category.DESERT) && this.getEntityWorld().isSkyVisible(getBlockPos())) {
                if(world.getTimeOfDay() > 5500 && world.getTimeOfDay() < 6500 && world.getTime() % 25 == 0) {
                    this.damage(DamageSource.HOT_FLOOR, 0.3f);
                }
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "tick()V")
    private void neutrino$tickClockandCompass(CallbackInfo ci) {
        Random random = new Random();
        if(inventory.contains(Items.CLOCK.getDefaultStack())) {
            i++;
            if(i == 1000 + random.nextInt(19000)) {
                i = 0;
                inventory.setStack(inventory.getSlotWithStack(Items.CLOCK.getDefaultStack()), ItemRegistry.BROKEN_CLOCK.getDefaultStack());
                this.playSound(SoundEvents.ENTITY_ITEM_BREAK,1,1);
                world.addBlockBreakParticles(getBlockPos(), Blocks.GOLD_BLOCK.getDefaultState());
            }
        }
        if(inventory.contains(Items.COMPASS.getDefaultStack())) {
            i++;
            if(i == 1000 + random.nextInt(19000)) {
                i = 0;
                inventory.setStack(inventory.getSlotWithStack(Items.CLOCK.getDefaultStack()), ItemRegistry.BROKEN_COMPASS.getDefaultStack());
                this.playSound(SoundEvents.ENTITY_ITEM_BREAK,1,1);
                world.addBlockBreakParticles(getBlockPos(), Blocks.IRON_BLOCK.getDefaultState());
            }
        }
    }
}





