package com.frostwizard4.Neutrino.entity;

import net.minecraft.entity.EntityData;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.SpiderEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.Random;

public class RatEntity extends HostileEntity implements IAnimatable {
    AnimationFactory factory = new AnimationFactory(this);
    boolean burrito;

    protected RatEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);
    }


    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rat.walk", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.rat.idle", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "rat_controller", 0, this::predicate));
    }
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_BAT_AMBIENT;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public static DefaultAttributeContainer.Builder createRatAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.45D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0D);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(4, new RatEntity.AttackGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.goalSelector.add(1, new SwimGoal(this));
        this.targetSelector.add(2, new RatEntity.TargetGoal<>(this, PlayerEntity.class));
        super.initGoals();
    }
    private static class AttackGoal extends MeleeAttackGoal {
        public AttackGoal(RatEntity spider) {
            super(spider, 1.0D, true);
        }

        public boolean canStart() {
            return super.canStart() && !this.mob.hasPassengers() && !(this.mob.getName().getString().equals("Burrito"));
        }

        public boolean shouldContinue() {
            float f = this.mob.getBrightnessAtEyes();
            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0 && !(this.mob.getName().getString().equals("Burrito"))) {
                this.mob.setTarget((LivingEntity)null);
                return false;
            } else {
                return super.shouldContinue();
            }
        }

        protected double getSquaredMaxAttackDistance(LivingEntity entity) {
            return (double)(4.0F + entity.getWidth());
        }
    }

    private static class TargetGoal<T extends LivingEntity> extends ActiveTargetGoal<T> {
        public TargetGoal(RatEntity rat, Class<T> targetEntityClass) {
            super(rat, targetEntityClass, true);
        }

        public boolean canStart() {
            float f = this.mob.getBrightnessAtEyes();
            return !(f >= 0.5F) && super.canStart();
        }
        public boolean shouldContinue() {
            float f = this.mob.getBrightnessAtEyes();
            if (f >= 0.5F && this.mob.getRandom().nextInt(100) == 0) {
                this.mob.setTarget((LivingEntity)null);
                return false;
            } else {
                return super.shouldContinue();
            }
        }
    }
}
