package com.frostwizard4.Neutrino.entity;

import com.frostwizard4.Neutrino.registry.ItemRegistry;
import com.frostwizard4.Neutrino.registry.SoundRegister;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.WitherSkullEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldEvents;
import net.minecraft.world.WorldView;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

public class WitherlingEntity extends HostileEntity implements IAnimatable, RangedAttackMob {
    AnimationFactory factory = new AnimationFactory(this);

    protected WitherlingEntity(EntityType<? extends HostileEntity> entityType, World world) {
        super(entityType, world);

    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.witherling.float", true));
        }  else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.witherling.idle", true));
        }
        return PlayState.CONTINUE;

    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "witherling_controller", 0, this::predicate));
    }
    protected SoundEvent getAmbientSound() {
        return SoundRegister.WITHERLING_IDLE;
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundRegister.WITHERLING_ATTACK;
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public static DefaultAttributeContainer.Builder createWitherlingAttributes() {
        return HostileEntity.createHostileAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 15.0D).add(EntityAttributes.GENERIC_ARMOR, 1.3D);
    }
    public WitherlingEntity(World world) {
        this(EntityType.ZOMBIE, world);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(2, new ProjectileAttackGoal(this, 2.0D, 80, 20.0F));
        this.goalSelector.add(7, new AttackGoal(this));
        this.targetSelector.add(1, new RevengeGoal(this));
        this.targetSelector.add(2, new ActiveTargetGoal<>(this, PlayerEntity.class, true));
        this.goalSelector.add(7, new WanderAroundGoal(this, 1.0D));
        super.initGoals();
    }

    protected void mobTick() {
        if (this.age % 20 == 0) {
            this.heal(1.0F);
        }
    }

    private void shootSkullAt(int headIndex, LivingEntity target) {
        this.shootSkullAt(headIndex, target.getX(), target.getY() + (double)target.getStandingEyeHeight() * 0.5D, target.getZ(), headIndex == 0 && this.random.nextFloat() < 0.001F);
    }

    private void shootSkullAt(int headIndex, double targetX, double targetY, double targetZ, boolean charged) {
        if (!this.isSilent()) {
            this.world.syncWorldEvent((PlayerEntity)null, WorldEvents.WITHER_SHOOTS, this.getBlockPos(), 0);
        }

        double d = this.getHeadX(headIndex);
        double e = this.getHeadY(headIndex);
        double f = this.getHeadZ(headIndex);
        double g = targetX - d;
        double h = targetY - e;
        double i = targetZ - f;
        WitherSkullEntity witherSkullEntity = new WitherSkullEntity(this.world, this, g, h, i);
        witherSkullEntity.setOwner(this);

        if (charged) {
            witherSkullEntity.setCharged(true);
        }

        witherSkullEntity.setPos(d, e, f);
        this.world.spawnEntity(witherSkullEntity);
    }

    public void attack(LivingEntity target, float pullProgress) {
        this.shootSkullAt(0, target);
    }

    private double getHeadX(int headIndex) {
        if (headIndex <= 0) {
            return this.getX();
        } else {
            float f = (this.bodyYaw + (float)(180 * (headIndex - 1))) * 0.017453292F;
            float g = MathHelper.cos(f);
            return this.getX() + (double)g * 1.3D;
        }
    }

    private double getHeadY(int headIndex) {
        return headIndex <= 0 ? this.getY() + 2.0D : this.getY() + 1.2D;
    }

    private double getHeadZ(int headIndex) {
        if (headIndex <= 0) {
            return this.getZ();
        } else {
            float f = (this.bodyYaw + (float) (180 * (headIndex - 1))) * 0.017453292F;
            float g = MathHelper.sin(f);
            return this.getZ() + (double) g * 1.3D;
        }
    }

    protected void dropEquipment(DamageSource source, int lootingMultiplier, boolean allowDrops) {
        super.dropEquipment(source, lootingMultiplier, allowDrops);
        ItemEntity itemEntity = this.dropItem(ItemRegistry.WITHERING_HEART);
        if (itemEntity != null) {
            itemEntity.setCovetedItem();
        }
    }
    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }
    public void tickMovement() {
        super.tickMovement();
        Vec3d vec3d = this.getVelocity();
        if (!this.onGround && vec3d.y < 0.0D) {
            this.setVelocity(vec3d.multiply(1.0D, 0.8D, 1.0D));
        }
    }
    public boolean canSpawn(WorldView world) {
        if (world.intersectsEntities(this) && !world.containsFluid(this.getBoundingBox())) {
            BlockPos blockPos = this.getBlockPos();
            if (blockPos.getY() < world.getSeaLevel()) {
                return false;
            }


            BlockState blockState = world.getBlockState(blockPos.down());
            return blockState.isIn(BlockTags.SOUL_SPEED_BLOCKS);
        }

        return false;
    }
    public int getLimitPerChunk() {
        return 1;
    }
}