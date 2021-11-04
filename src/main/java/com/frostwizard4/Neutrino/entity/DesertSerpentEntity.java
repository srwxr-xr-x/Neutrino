package com.frostwizard4.Neutrino.entity;

import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityPose;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class DesertSerpentEntity extends HostileDataEntity implements IAnimatable {

    public DesertSerpentEntity(EntityType<DesertSerpentEntity> entityType, World worldIn) {
        super(entityType, worldIn);
    }

    private final AnimationFactory factory = new AnimationFactory(this);

    private <E extends IAnimatable> PlayState predicate(AnimationEvent<E> event) {
        if (this.dataTracker.get(STATE) == 1 && !(this.dead || this.getHealth() < 0.01 || this.isDead())) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.serpent.attack", true));
            return PlayState.CONTINUE;
        } else if(event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.serpent.slither", true));
            return PlayState.CONTINUE;
        }
        return PlayState.STOP;
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(6, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(6, new LookAroundGoal(this));
        this.goalSelector.add(5, new WanderAroundFarGoal(this, 0.8D));
        this.targetSelector.add(1, new RevengeGoal(this).setGroupRevenge());
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.add(1, new DesertSerpentEntity.AttackGoal(this));

    }

    public static DefaultAttributeContainer.Builder createSerpentAttributes() {
        return LivingEntity.createLivingAttributes().add(EntityAttributes.GENERIC_FOLLOW_RANGE, 25.0D)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 15)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 1.0D);
    }

    @Override
    protected void updateGoalControls() {
        boolean flag = this.getTarget() != null && this.canSee(this.getTarget());
        this.goalSelector.setControlEnabled(Goal.Control.LOOK, flag);
        super.updateGoalControls();
    }

    class AttackGoal extends Goal {
        private final DesertSerpentEntity ghast;
        public int cooldown;

        public AttackGoal(DesertSerpentEntity ghast) {
            this.ghast = ghast;
        }

        public boolean canStart() {
            return this.ghast.getTarget() != null;
        }

        public void start() {
            super.start();
            this.ghast.setAttacking(true);
            this.cooldown = 0;
            this.ghast.setAttackingState(0);
        }

        @Override
        public void stop() {
            super.stop();
            this.ghast.setAttacking(false);
            this.ghast.setAttackingState(0);
        }

        public void tick() {
            LivingEntity livingEntity = this.ghast.getTarget();
            if (this.ghast.canSee(livingEntity) && livingEntity != null) {
                double d0 = this.ghast.squaredDistanceTo(livingEntity.getX(), livingEntity.getY(), livingEntity.getZ());
                ++this.cooldown;
                if (this.cooldown == 14) {
                    if(livingEntity.distanceTo(this.ghast) <= 2) {
                        this.ghast.setAttackingState(1);
                        this.ghast.tryAttack(livingEntity);
                    }
                }
                if (this.cooldown == 27) {
                    this.ghast.setAttackingState(0);
                    this.ghast.getNavigation().setSpeed(1.33);
                    this.cooldown = -60;
                }
            } else if (this.cooldown > 0) {
                --this.cooldown;
            }
        }
        protected double getSquaredMaxAttackDistance(LivingEntity entity) {
            return (double)(this.ghast.getWidth() * 2.0F * this.ghast.getWidth() * 2.0F + entity.getWidth());
        }
    }
    @Override
    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return 1.74F;
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    protected SoundEvent getStepSound() {
        return SoundEvents.ENTITY_ZOMBIE_STEP;
    }

    @Override
    protected void playStepSound(BlockPos pos, BlockState blockIn) {
        this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }
}