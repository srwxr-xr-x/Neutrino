package com.frostwizard4.Neutrino.mixin;

import com.frostwizard4.Neutrino.NeutrinoMain;
import com.frostwizard4.Neutrino.PlayerEntityAccess;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

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
        if (getMainHandStack().isOf(NeutrinoMain.HARVESTER) || getMainHandStack().isOf(NeutrinoMain.LIGHTNING_ROD_ARTIFACT)) {
            if (world.isClient()) {
                //CheckHolding.sendChatMessage(neutrino$boomPowerCounter);
                sendMessage(Text.of("Soul Level: " + (int) neutrino$boomPowerCounter / 10), true);

                //ServerPlayerEntity!!
            }
        }
        if (isOnSoulSpeedBlock()) {
            if (getMainHandStack().isOf(NeutrinoMain.SOUL_POUCH)) {
                displaySoulSpeedEffects();
                if (neutrino$soulPouchCounter >= 3000) {
                    neutrino$soulPouchCounter = 3000;
                    if (world.isClient()) {
                        //CheckHolding.sendBundleChatMessage(neutrino$soulPouchCounter);
                        sendMessage(Text.of("Soul Pouch Level: " + (int) neutrino$soulPouchCounter / 10), true);

                    }
                } else {
                    if (getMainHandStack().isOf(NeutrinoMain.SOUL_POUCH)) {
                        neutrino$soulPouchCounter++;
                        if (world.isClient()) {
                            //CheckHolding.sendBundleChatMessage(neutrino$soulPouchCounter);
                            sendMessage(Text.of("Soul Pouch Level: " + (int) neutrino$soulPouchCounter / 10), true);

                        }
                    }
                }
            }
            if (getMainHandStack().isOf(NeutrinoMain.HARVESTER) || getMainHandStack().isOf(NeutrinoMain.LIGHTNING_ROD_ARTIFACT) || getMainHandStack().isOf(NeutrinoMain.SOUL_HEALER)) {
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
    private void neutrino$freezeHeight(CallbackInfo ci) {
        if(getY() >= 175 && !(getEquippedStack(EquipmentSlot.FEET).isOf(Items.LEATHER_BOOTS))
                && !(getEquippedStack(EquipmentSlot.LEGS).isOf(Items.LEATHER_LEGGINGS))
                && !(getEquippedStack(EquipmentSlot.CHEST).isOf(Items.LEATHER_CHESTPLATE))
                && !(getEquippedStack(EquipmentSlot.HEAD).isOf(Items.LEATHER_HELMET))) {
            if (getY() >= 230 && !(getEquippedStack(EquipmentSlot.FEET).isOf(Items.LEATHER_BOOTS))
                    && !(getEquippedStack(EquipmentSlot.LEGS).isOf(Items.LEATHER_LEGGINGS))
                    && !(getEquippedStack(EquipmentSlot.CHEST).isOf(Items.LEATHER_CHESTPLATE))
                    && !(getEquippedStack(EquipmentSlot.HEAD).isOf(Items.LEATHER_HELMET))) {
                this.setFrozenTicks(200);
                this.damage(DamageSource.FREEZE, 0.5F);
            }
            this.setFrozenTicks(150);
        }
    }
}





