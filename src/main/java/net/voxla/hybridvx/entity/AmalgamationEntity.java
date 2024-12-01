
package net.voxla.hybridvx.entity;

import software.bernie.geckolib.util.GeckoLibUtil;
import software.bernie.geckolib.core.object.PlayState;
import software.bernie.geckolib.core.animation.RawAnimation;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animation.AnimationController;
import software.bernie.geckolib.core.animation.AnimatableManager;
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache;
import software.bernie.geckolib.animatable.GeoEntity;

import net.voxla.hybridvx.procedures.AmalgamationOnInitialEntitySpawnProcedure;
import net.voxla.hybridvx.procedures.AmalgamationOnEntityTickUpdateProcedure;
import net.voxla.hybridvx.procedures.AmalgamationEntityDiesProcedure;
import net.voxla.hybridvx.procedures.AmalgamationDeathTimeIsReachedProcedure;
import net.voxla.hybridvx.procedures.AmalgamationBoundingBoxScaleProcedure;
import net.voxla.hybridvx.procedures.AmalgamCanAttackProcProcedure;
import net.voxla.hybridvx.init.HybridVxModEntities;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.network.PlayMessages;
import net.minecraftforge.network.NetworkHooks;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnGroupData;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.AreaEffectCloud;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.Packet;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.core.BlockPos;

import javax.annotation.Nullable;

public class AmalgamationEntity extends Monster implements GeoEntity {
	public static final EntityDataAccessor<Boolean> SHOOT = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<String> ANIMATION = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<String> TEXTURE = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.STRING);
	public static final EntityDataAccessor<Boolean> DATA_IsBeingLookedAt = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> DATA_AmalgamAI1Determin = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> DATA_Chasing = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> DATA_IsBeingSeen = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> DATA_Attacking = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_AmalgamAI = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_AmbientSoundTimer = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_ScreamTimer = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_ChaseScreamTimer = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_ChaseMusicTimer = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_MFTFAIDisappearTimer = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_HasBeenOnAIStageTooLong = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_ChaseLightningTimer = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_AI1IfNotLookedAtTimer = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_InvisTeleportTimer = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_TPx = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_TPy = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_TPz = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_BackingAmbientTimer = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_DespawnTimer = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_ChaseTimer = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_CowerTimer = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_HasGrabbedPlayer = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_GrabChance = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Integer> DATA_GrabPlayerCooldown = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	public static final EntityDataAccessor<Boolean> DATA_IsGrabJumpscareThenDontKill = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> DATA_Climbing = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Boolean> DATA_SpawnedUnderground = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.BOOLEAN);
	public static final EntityDataAccessor<Integer> DATA_UndergroundDespawnTimer = SynchedEntityData.defineId(AmalgamationEntity.class, EntityDataSerializers.INT);
	private final AnimatableInstanceCache cache = GeckoLibUtil.createInstanceCache(this);
	private boolean swinging;
	private boolean lastloop;
	private long lastSwing;
	public String animationprocedure = "empty";

	public AmalgamationEntity(PlayMessages.SpawnEntity packet, Level world) {
		this(HybridVxModEntities.HYBRID.get(), world);
	}

	public AmalgamationEntity(EntityType<AmalgamationEntity> type, Level world) {
		super(type, world);
		xpReward = 200;
		setNoAi(false);
		maxUpStep = 0.6f;
		setPersistenceRequired();
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(SHOOT, false);
		this.entityData.define(ANIMATION, "undefined");
		this.entityData.define(TEXTURE, "amalgam");
		this.entityData.define(DATA_IsBeingLookedAt, false);
		this.entityData.define(DATA_AmalgamAI1Determin, 0);
		this.entityData.define(DATA_Chasing, false);
		this.entityData.define(DATA_IsBeingSeen, false);
		this.entityData.define(DATA_Attacking, 0);
		this.entityData.define(DATA_AmalgamAI, 0);
		this.entityData.define(DATA_AmbientSoundTimer, 0);
		this.entityData.define(DATA_ScreamTimer, 0);
		this.entityData.define(DATA_ChaseScreamTimer, 0);
		this.entityData.define(DATA_ChaseMusicTimer, 0);
		this.entityData.define(DATA_MFTFAIDisappearTimer, 0);
		this.entityData.define(DATA_HasBeenOnAIStageTooLong, 0);
		this.entityData.define(DATA_ChaseLightningTimer, 0);
		this.entityData.define(DATA_AI1IfNotLookedAtTimer, 0);
		this.entityData.define(DATA_InvisTeleportTimer, 0);
		this.entityData.define(DATA_TPx, 0);
		this.entityData.define(DATA_TPy, 0);
		this.entityData.define(DATA_TPz, 0);
		this.entityData.define(DATA_BackingAmbientTimer, 0);
		this.entityData.define(DATA_DespawnTimer, 0);
		this.entityData.define(DATA_ChaseTimer, 0);
		this.entityData.define(DATA_CowerTimer, 0);
		this.entityData.define(DATA_HasGrabbedPlayer, 0);
		this.entityData.define(DATA_GrabChance, 0);
		this.entityData.define(DATA_GrabPlayerCooldown, 0);
		this.entityData.define(DATA_IsGrabJumpscareThenDontKill, false);
		this.entityData.define(DATA_Climbing, false);
		this.entityData.define(DATA_SpawnedUnderground, false);
		this.entityData.define(DATA_UndergroundDespawnTimer, 0);
	}

	public void setTexture(String texture) {
		this.entityData.set(TEXTURE, texture);
	}

	public String getTexture() {
		return this.entityData.get(TEXTURE);
	}

	@Override
	public Packet<ClientGamePacketListener> getAddEntityPacket() {
		return NetworkHooks.getEntitySpawningPacket(this);
	}

	@Override
	protected void registerGoals() {
		super.registerGoals();
		this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
		this.targetSelector.addGoal(2, new NearestAttackableTargetGoal(this, Player.class, true, false) {
			@Override
			public boolean canUse() {
				double x = AmalgamationEntity.this.getX();
				double y = AmalgamationEntity.this.getY();
				double z = AmalgamationEntity.this.getZ();
				Entity entity = AmalgamationEntity.this;
				Level world = AmalgamationEntity.this.level;
				return super.canUse() && AmalgamCanAttackProcProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = AmalgamationEntity.this.getX();
				double y = AmalgamationEntity.this.getY();
				double z = AmalgamationEntity.this.getZ();
				Entity entity = AmalgamationEntity.this;
				Level world = AmalgamationEntity.this.level;
				return super.canContinueToUse() && AmalgamCanAttackProcProcedure.execute(entity);
			}
		});
		this.goalSelector.addGoal(3, new MeleeAttackGoal(this, 3, false) {
			@Override
			protected double getAttackReachSqr(LivingEntity entity) {
				return 6.76;
			}
		});
		this.goalSelector.addGoal(4, new RandomStrollGoal(this, 1));
		this.goalSelector.addGoal(5, new RandomSwimmingGoal(this, 1, 40) {
			@Override
			public boolean canUse() {
				double x = AmalgamationEntity.this.getX();
				double y = AmalgamationEntity.this.getY();
				double z = AmalgamationEntity.this.getZ();
				Entity entity = AmalgamationEntity.this;
				Level world = AmalgamationEntity.this.level;
				return super.canUse() && AmalgamCanAttackProcProcedure.execute(entity);
			}

			@Override
			public boolean canContinueToUse() {
				double x = AmalgamationEntity.this.getX();
				double y = AmalgamationEntity.this.getY();
				double z = AmalgamationEntity.this.getZ();
				Entity entity = AmalgamationEntity.this;
				Level world = AmalgamationEntity.this.level;
				return super.canContinueToUse() && AmalgamCanAttackProcProcedure.execute(entity);
			}
		});
		this.goalSelector.addGoal(6, new FloatGoal(this));
	}

	@Override
	public MobType getMobType() {
		return MobType.UNDEFINED;
	}

	@Override
	public boolean removeWhenFarAway(double distanceToClosestPlayer) {
		return false;
	}

	@Override
	public void playStepSound(BlockPos pos, BlockState blockIn) {
		this.playSound(ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.footstep")), 0.15f, 1);
	}

	@Override
	public SoundEvent getHurtSound(DamageSource ds) {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.hurt"));
	}

	@Override
	public SoundEvent getDeathSound() {
		return ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:none"));
	}

	@Override
	public boolean hurt(DamageSource source, float amount) {
		if (source.is(DamageTypes.IN_FIRE))
			return false;
		if (source.getDirectEntity() instanceof ThrownPotion || source.getDirectEntity() instanceof AreaEffectCloud)
			return false;
		if (source.is(DamageTypes.FALL))
			return false;
		if (source.is(DamageTypes.CACTUS))
			return false;
		if (source.is(DamageTypes.DROWN))
			return false;
		if (source.is(DamageTypes.LIGHTNING_BOLT))
			return false;
		if (source.is(DamageTypes.FALLING_ANVIL))
			return false;
		if (source.is(DamageTypes.DRAGON_BREATH))
			return false;
		if (source.is(DamageTypes.WITHER))
			return false;
		if (source.is(DamageTypes.WITHER_SKULL))
			return false;
		return super.hurt(source, amount);
	}

	@Override
	public void die(DamageSource source) {
		super.die(source);
		AmalgamationEntityDiesProcedure.execute(this.level, this.getX(), this.getY(), this.getZ(), source.getEntity());
	}

	@Override
	public SpawnGroupData finalizeSpawn(ServerLevelAccessor world, DifficultyInstance difficulty, MobSpawnType reason, @Nullable SpawnGroupData livingdata, @Nullable CompoundTag tag) {
		SpawnGroupData retval = super.finalizeSpawn(world, difficulty, reason, livingdata, tag);
		AmalgamationOnInitialEntitySpawnProcedure.execute(world, this.getX(), this.getY(), this.getZ(), this);
		return retval;
	}

	@Override
	public void addAdditionalSaveData(CompoundTag compound) {
		super.addAdditionalSaveData(compound);
		compound.putString("Texture", this.getTexture());
		compound.putBoolean("DataIsBeingLookedAt", this.entityData.get(DATA_IsBeingLookedAt));
		compound.putInt("DataAmalgamAI1Determin", this.entityData.get(DATA_AmalgamAI1Determin));
		compound.putBoolean("DataChasing", this.entityData.get(DATA_Chasing));
		compound.putBoolean("DataIsBeingSeen", this.entityData.get(DATA_IsBeingSeen));
		compound.putInt("DataAttacking", this.entityData.get(DATA_Attacking));
		compound.putInt("DataAmalgamAI", this.entityData.get(DATA_AmalgamAI));
		compound.putInt("DataAmbientSoundTimer", this.entityData.get(DATA_AmbientSoundTimer));
		compound.putInt("DataScreamTimer", this.entityData.get(DATA_ScreamTimer));
		compound.putInt("DataChaseScreamTimer", this.entityData.get(DATA_ChaseScreamTimer));
		compound.putInt("DataChaseMusicTimer", this.entityData.get(DATA_ChaseMusicTimer));
		compound.putInt("DataMFTFAIDisappearTimer", this.entityData.get(DATA_MFTFAIDisappearTimer));
		compound.putInt("DataHasBeenOnAIStageTooLong", this.entityData.get(DATA_HasBeenOnAIStageTooLong));
		compound.putInt("DataChaseLightningTimer", this.entityData.get(DATA_ChaseLightningTimer));
		compound.putInt("DataAI1IfNotLookedAtTimer", this.entityData.get(DATA_AI1IfNotLookedAtTimer));
		compound.putInt("DataInvisTeleportTimer", this.entityData.get(DATA_InvisTeleportTimer));
		compound.putInt("DataTPx", this.entityData.get(DATA_TPx));
		compound.putInt("DataTPy", this.entityData.get(DATA_TPy));
		compound.putInt("DataTPz", this.entityData.get(DATA_TPz));
		compound.putInt("DataBackingAmbientTimer", this.entityData.get(DATA_BackingAmbientTimer));
		compound.putInt("DataDespawnTimer", this.entityData.get(DATA_DespawnTimer));
		compound.putInt("DataChaseTimer", this.entityData.get(DATA_ChaseTimer));
		compound.putInt("DataCowerTimer", this.entityData.get(DATA_CowerTimer));
		compound.putInt("DataHasGrabbedPlayer", this.entityData.get(DATA_HasGrabbedPlayer));
		compound.putInt("DataGrabChance", this.entityData.get(DATA_GrabChance));
		compound.putInt("DataGrabPlayerCooldown", this.entityData.get(DATA_GrabPlayerCooldown));
		compound.putBoolean("DataIsGrabJumpscareThenDontKill", this.entityData.get(DATA_IsGrabJumpscareThenDontKill));
		compound.putBoolean("DataClimbing", this.entityData.get(DATA_Climbing));
		compound.putBoolean("DataSpawnedUnderground", this.entityData.get(DATA_SpawnedUnderground));
		compound.putInt("DataUndergroundDespawnTimer", this.entityData.get(DATA_UndergroundDespawnTimer));
	}

	@Override
	public void readAdditionalSaveData(CompoundTag compound) {
		super.readAdditionalSaveData(compound);
		if (compound.contains("Texture"))
			this.setTexture(compound.getString("Texture"));
		if (compound.contains("DataIsBeingLookedAt"))
			this.entityData.set(DATA_IsBeingLookedAt, compound.getBoolean("DataIsBeingLookedAt"));
		if (compound.contains("DataAmalgamAI1Determin"))
			this.entityData.set(DATA_AmalgamAI1Determin, compound.getInt("DataAmalgamAI1Determin"));
		if (compound.contains("DataChasing"))
			this.entityData.set(DATA_Chasing, compound.getBoolean("DataChasing"));
		if (compound.contains("DataIsBeingSeen"))
			this.entityData.set(DATA_IsBeingSeen, compound.getBoolean("DataIsBeingSeen"));
		if (compound.contains("DataAttacking"))
			this.entityData.set(DATA_Attacking, compound.getInt("DataAttacking"));
		if (compound.contains("DataAmalgamAI"))
			this.entityData.set(DATA_AmalgamAI, compound.getInt("DataAmalgamAI"));
		if (compound.contains("DataAmbientSoundTimer"))
			this.entityData.set(DATA_AmbientSoundTimer, compound.getInt("DataAmbientSoundTimer"));
		if (compound.contains("DataScreamTimer"))
			this.entityData.set(DATA_ScreamTimer, compound.getInt("DataScreamTimer"));
		if (compound.contains("DataChaseScreamTimer"))
			this.entityData.set(DATA_ChaseScreamTimer, compound.getInt("DataChaseScreamTimer"));
		if (compound.contains("DataChaseMusicTimer"))
			this.entityData.set(DATA_ChaseMusicTimer, compound.getInt("DataChaseMusicTimer"));
		if (compound.contains("DataMFTFAIDisappearTimer"))
			this.entityData.set(DATA_MFTFAIDisappearTimer, compound.getInt("DataMFTFAIDisappearTimer"));
		if (compound.contains("DataHasBeenOnAIStageTooLong"))
			this.entityData.set(DATA_HasBeenOnAIStageTooLong, compound.getInt("DataHasBeenOnAIStageTooLong"));
		if (compound.contains("DataChaseLightningTimer"))
			this.entityData.set(DATA_ChaseLightningTimer, compound.getInt("DataChaseLightningTimer"));
		if (compound.contains("DataAI1IfNotLookedAtTimer"))
			this.entityData.set(DATA_AI1IfNotLookedAtTimer, compound.getInt("DataAI1IfNotLookedAtTimer"));
		if (compound.contains("DataInvisTeleportTimer"))
			this.entityData.set(DATA_InvisTeleportTimer, compound.getInt("DataInvisTeleportTimer"));
		if (compound.contains("DataTPx"))
			this.entityData.set(DATA_TPx, compound.getInt("DataTPx"));
		if (compound.contains("DataTPy"))
			this.entityData.set(DATA_TPy, compound.getInt("DataTPy"));
		if (compound.contains("DataTPz"))
			this.entityData.set(DATA_TPz, compound.getInt("DataTPz"));
		if (compound.contains("DataBackingAmbientTimer"))
			this.entityData.set(DATA_BackingAmbientTimer, compound.getInt("DataBackingAmbientTimer"));
		if (compound.contains("DataDespawnTimer"))
			this.entityData.set(DATA_DespawnTimer, compound.getInt("DataDespawnTimer"));
		if (compound.contains("DataChaseTimer"))
			this.entityData.set(DATA_ChaseTimer, compound.getInt("DataChaseTimer"));
		if (compound.contains("DataCowerTimer"))
			this.entityData.set(DATA_CowerTimer, compound.getInt("DataCowerTimer"));
		if (compound.contains("DataHasGrabbedPlayer"))
			this.entityData.set(DATA_HasGrabbedPlayer, compound.getInt("DataHasGrabbedPlayer"));
		if (compound.contains("DataGrabChance"))
			this.entityData.set(DATA_GrabChance, compound.getInt("DataGrabChance"));
		if (compound.contains("DataGrabPlayerCooldown"))
			this.entityData.set(DATA_GrabPlayerCooldown, compound.getInt("DataGrabPlayerCooldown"));
		if (compound.contains("DataIsGrabJumpscareThenDontKill"))
			this.entityData.set(DATA_IsGrabJumpscareThenDontKill, compound.getBoolean("DataIsGrabJumpscareThenDontKill"));
		if (compound.contains("DataClimbing"))
			this.entityData.set(DATA_Climbing, compound.getBoolean("DataClimbing"));
		if (compound.contains("DataSpawnedUnderground"))
			this.entityData.set(DATA_SpawnedUnderground, compound.getBoolean("DataSpawnedUnderground"));
		if (compound.contains("DataUndergroundDespawnTimer"))
			this.entityData.set(DATA_UndergroundDespawnTimer, compound.getInt("DataUndergroundDespawnTimer"));
	}

	@Override
	public void baseTick() {
		super.baseTick();
		AmalgamationOnEntityTickUpdateProcedure.execute(this.level, this.getX(), this.getY(), this.getZ(), this);
		this.refreshDimensions();
	}

	@Override
	public EntityDimensions getDimensions(Pose p_33597_) {
		Entity entity = this;
		Level world = this.level;
		double x = this.getX();
		double y = entity.getY();
		double z = entity.getZ();
		return super.getDimensions(p_33597_).scale((float) AmalgamationBoundingBoxScaleProcedure.execute(world, x, y, z));
	}

	public static void init() {
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.17);
		builder = builder.add(Attributes.MAX_HEALTH, 150);
		builder = builder.add(Attributes.ARMOR, 2);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 1);
		builder = builder.add(Attributes.FOLLOW_RANGE, 32);
		builder = builder.add(Attributes.KNOCKBACK_RESISTANCE, 0.6);
		return builder;
	}

	private PlayState movementPredicate(AnimationState event) {
		if (this.animationprocedure.equals("empty")) {
			if ((event.isMoving() || !(event.getLimbSwingAmount() > -0.15F && event.getLimbSwingAmount() < 0.15F))

					&& !this.isAggressive()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.amalgamation.walk"));
			}
			if (this.isInWaterOrBubble()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.amalgamation.swin"));
			}
			if (this.isShiftKeyDown()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.amalgamation.idlesneak"));
			}
			if (this.isAggressive() && event.isMoving()) {
				return event.setAndContinue(RawAnimation.begin().thenLoop("animation.amalgamation.runfaster"));
			}
			return event.setAndContinue(RawAnimation.begin().thenLoop("animation.amalgamation.idle"));
		}
		return PlayState.STOP;
	}

	private PlayState attackingPredicate(AnimationState event) {
		double d1 = this.getX() - this.xOld;
		double d0 = this.getZ() - this.zOld;
		float velocity = (float) Math.sqrt(d1 * d1 + d0 * d0);
		if (getAttackAnim(event.getPartialTick()) > 0f && !this.swinging) {
			this.swinging = true;
			this.lastSwing = level.getGameTime();
		}
		if (this.swinging && this.lastSwing + 7L <= level.getGameTime()) {
			this.swinging = false;
		}
		if (this.swinging && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
			event.getController().forceAnimationReset();
			return event.setAndContinue(RawAnimation.begin().thenPlay("animation.amalgamation.attack"));
		}
		return PlayState.CONTINUE;
	}

	private PlayState procedurePredicate(AnimationState event) {
		if (!animationprocedure.equals("empty") && event.getController().getAnimationState() == AnimationController.State.STOPPED) {
			event.getController().setAnimation(RawAnimation.begin().thenPlay(this.animationprocedure));
			if (event.getController().getAnimationState() == AnimationController.State.STOPPED) {
				this.animationprocedure = "empty";
				event.getController().forceAnimationReset();
			}
		} else if (animationprocedure.equals("empty")) {
			return PlayState.STOP;
		}
		return PlayState.CONTINUE;
	}

	@Override
	protected void tickDeath() {
		++this.deathTime;
		if (this.deathTime == 1) {
			this.remove(AmalgamationEntity.RemovalReason.KILLED);
			this.dropExperience();
			AmalgamationDeathTimeIsReachedProcedure.execute(this.level, this);
		}
	}

	public String getSyncedAnimation() {
		return this.entityData.get(ANIMATION);
	}

	public void setAnimation(String animation) {
		this.entityData.set(ANIMATION, animation);
	}

	@Override
	public void registerControllers(AnimatableManager.ControllerRegistrar data) {
		data.add(new AnimationController<>(this, "movement", 4, this::movementPredicate));
		data.add(new AnimationController<>(this, "attacking", 4, this::attackingPredicate));
		data.add(new AnimationController<>(this, "procedure", 4, this::procedurePredicate));
	}

	@Override
	public AnimatableInstanceCache getAnimatableInstanceCache() {
		return this.cache;
	}
}
