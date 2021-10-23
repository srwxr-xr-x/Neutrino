package com.frostwizard4.Neutrino.entity;

import com.frostwizard4.Neutrino.registry.ItemRegistry;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;

import java.util.function.Consumer;

public class AlpacaEntity extends AnimalEntity implements IAnimatable, Shearable {
    AnimationFactory factory = new AnimationFactory(this);
    private static final Ingredient BREEDING_INGREDIENT;
    public boolean sheared = false;
    public int randomShear;

    protected AlpacaEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);

    }

    private <P extends IAnimatable> PlayState predicate(AnimationEvent<P> event) {
        if (event.isMoving()) {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alpaca.walk", true));
        } else {
            event.getController().setAnimation(new AnimationBuilder().addAnimation("animation.alpaca.idle", true));
        }
        return PlayState.CONTINUE;
    }

    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController<>(this, "alpaca_controller", 0, this::predicate));
    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }

    public static DefaultAttributeContainer.Builder createAlpacaAttributes() {
        return MobEntity.createMobAttributes()
                .add(EntityAttributes.HORSE_JUMP_STRENGTH)
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 40.0D)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.22499999403953552D)
                .add(EntityAttributes.HORSE_JUMP_STRENGTH, 0.5D)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 40.0D);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return EntityRegistry.ALPACA.create(this.world);
    }

    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_LLAMA_AMBIENT;
    }
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_LLAMA_HURT;
    }
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_LLAMA_DEATH;
    }
    protected void playStepSound(BlockPos pos, BlockState state) {
        this.playSound(SoundEvents.ENTITY_LLAMA_STEP, 0.15F, 1.0F);
    }
    protected void mobTick() {
        randomShear = (int) (Math.random() * (10000 - 1 + 1) + 1);
        if (randomShear == 42) {
            sheared = false;
        }
            super.mobTick();
    }
    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(3, new EscapeDangerGoal(this, 1.2D));
        this.goalSelector.add(4, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.0D));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 0.7D));
        this.goalSelector.add(3, new TemptGoal(this, 1.0D, BREEDING_INGREDIENT, false));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        super.initGoals();
    }
    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(Items.BREAD);
    }

    static {
        BREEDING_INGREDIENT = Ingredient.ofItems(Items.WHEAT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.BEETROOT_SEEDS);
    }

    @Override
    public void sheared(SoundCategory shearedSoundCategory) {
        sheared = true;
        this.world.playSoundFromEntity((PlayerEntity)null, this, SoundEvents.ENTITY_SHEEP_SHEAR, shearedSoundCategory, 1.0F, 1.0F);
        int i = 1 + this.random.nextInt(3);

        for(int j = 0; j < i; ++j) {
            ItemEntity itemEntity = this.dropItem(ItemRegistry.ALPACA_FUR, 1);
            if (itemEntity != null) {
                itemEntity.setVelocity(itemEntity.getVelocity().add((double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F), (double)(this.random.nextFloat() * 0.05F), (double)((this.random.nextFloat() - this.random.nextFloat()) * 0.1F)));
            }
        }
    }


    @Override
    public boolean isShearable() {
        return this.isAlive() && !this.isBaby() && !sheared;
    }
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        if (itemStack.isOf(Items.SHEARS)) {
            if (!this.world.isClient && this.isShearable()) {
                this.sheared(SoundCategory.PLAYERS);
                this.emitGameEvent(GameEvent.SHEAR, player);
                itemStack.damage(1, (LivingEntity)player, (Consumer<LivingEntity>)((playerx) -> {
                    playerx.sendToolBreakStatus(hand);
                }));
                return ActionResult.SUCCESS;
            } else {
                return ActionResult.CONSUME;
            }
        } else {
            return super.interactMob(player, hand);
        }
    }

}