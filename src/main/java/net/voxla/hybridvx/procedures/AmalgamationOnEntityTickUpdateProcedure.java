package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.init.HybridVxModParticleTypes;
import net.voxla.hybridvx.init.HybridVxModBlocks;
import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class AmalgamationOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		if (entity.isPassenger()) {
			entity.stopRiding();
		}
		AmalgamSneakProcProcedure.execute(world, x, y, z, entity);
		if ((entity instanceof AmalgamationEntity _datEntL2 && _datEntL2.getEntityData().get(AmalgamationEntity.DATA_IsGrabJumpscareThenDontKill)) == true) {
			AmalgamGrabPlayerProcProcedure.execute(world, x, y, z, entity);
		}
		if ((entity instanceof AmalgamationEntity _datEntL3 && _datEntL3.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == true) {
			AmalgamBreakGlassBlockProcProcedure.execute(world, x, y, z);
			AmalgamBreakGlassPaneProcProcedure.execute(world, x, y, z);
			AmalgamBreakOpenDoorProcProcedure.execute(world, x, y, z);
		}
		if ((entity instanceof AmalgamationEntity _datEntL4 && _datEntL4.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == true && entity.isShiftKeyDown() == false) {
			AmalgamGrabPlayerProcProcedure.execute(world, x, y, z, entity);
			AmalgamationClimbProcProcedure.execute(world, x, y, z, entity);
		}
		if ((entity instanceof AmalgamationEntity _datEntL6 && _datEntL6.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == false
				&& (entity instanceof AmalgamationEntity _datEntL7 && _datEntL7.getEntityData().get(AmalgamationEntity.DATA_IsGrabJumpscareThenDontKill)) == false) {
			if ((entity instanceof AmalgamationEntity _datEntL8 && _datEntL8.getEntityData().get(AmalgamationEntity.DATA_Climbing)) == true) {
				if (entity instanceof AmalgamationEntity _datEntSetL)
					_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_Climbing, false);
			}
			if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_AmalgamAI) : 0) == 0) {
				AiProc0Procedure.execute(world, x, y, z, entity);
			} else if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_AmalgamAI) : 0) == 1) {
				AiProc1Procedure.execute(world, x, y, z, entity);
			} else if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_AmalgamAI) : 0) == 2) {
				AiProc2Procedure.execute(world, x, y, z, entity);
			} else if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_AmalgamAI) : 0) == 3) {
				AiProc3Procedure.execute(world, x, y, z, entity);
			} else if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_AmalgamAI) : 0) == 4) {
				AiProc4Procedure.execute(world, x, y, z, entity);
			}
			if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).isEmpty()) {
				entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).getX()), (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).getY()), (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).getZ())));
			}
		}
		AmalgamUndergroundProcProcedure.execute(world, x, y, z, entity);
		AmalgamationDistantSoundProcProcedure.execute(world, x, y, z, entity);
		AmalgamationSoundsProcProcedure.execute(world, x, y, z, entity);
		PlayerCanSeeAmalgamProcProcedure.execute(world, x, y, z, entity);
		AmalgamChaseMusicPlayerProcedure.execute(world, x, y, z, entity);
		if (Math.random() > 0.8) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_DOT_PARTICLE.get()), (entity.getX()), (entity.getY() + 1), (entity.getZ()), 2, 0.35, 0.8, 0.35, 0.01);
		}
		if (Math.random() > 0.7) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.FLESH_BLOOD_DRIP.get()), (entity.getX()), (entity.getY() + 1), (entity.getZ()), 3, 0.35, 0.8, 0.35, 0.01);
		}
		if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_Attacking) : 0) > 0) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_Attacking, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_Attacking) : 0) - 1));
		}
		if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
			if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_Attacking) : 0) < 6) {
				if (entity instanceof AmalgamationEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_Attacking, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_Attacking) : 0) + 10));
			}
		}
		if (entity.getTicksFrozen() > 0) {
			entity.setTicksFrozen(0);
		}
		if ((entity instanceof AmalgamationEntity _datEntL41 && _datEntL41.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == true
				&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ChaseLightningTimer) : 0) < 20) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_ChaseLightningTimer, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ChaseLightningTimer) : 0) + 1));
			if (!world.isClientSide()) {
				if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ChaseLightningTimer) : 0) == 1) {
					if (entity instanceof AmalgamationEntity _datEntSetI)
						_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_ChaseTimer, Mth.nextInt(RandomSource.create(), 600, 2400));
					{
						final Vec3 _center = new Vec3(x, y, z);
						List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(200 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
								.collect(Collectors.toList());
						for (Entity entityiterator : _entfound) {
							if (entityiterator instanceof Player) {
								if (entityiterator instanceof LivingEntity _entity && !_entity.level.isClientSide())
									_entity.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 200, 0, false, false));
							}
						}
					}
					if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
						_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 3, 200, false, false));
					if (world instanceof ServerLevel _level) {
						LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
						entityToSpawn.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(x, y, z)));
						entityToSpawn.setVisualOnly(true);
						_level.addFreshEntity(entityToSpawn);
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.chase_start")), SoundSource.HOSTILE, 3, 1);
						} else {
							_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.chase_start")), SoundSource.HOSTILE, 3, 1, false);
						}
					}
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.LARGE_SMOKE, (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 400, 1, 1, 1, 0.01);
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.FEAR_SMOKE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 100, 1, 1, 1, 0.01);
				}
			}
		} else if ((entity instanceof AmalgamationEntity _datEntL63 && _datEntL63.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == false
				&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ChaseLightningTimer) : 0) > 0) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_ChaseLightningTimer, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ChaseLightningTimer) : 0) - 1));
		}
		if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ChaseTimer) : 0) > 1) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_ChaseTimer, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ChaseTimer) : 0) - 1));
		}
		if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ChaseTimer) : 0) == 1) {
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 2,
								(float) Mth.nextDouble(RandomSource.create(), 0.8, 1));
					} else {
						_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 2, (float) Mth.nextDouble(RandomSource.create(), 0.8, 1), false);
					}
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.chase_end")), SoundSource.HOSTILE, 3, 1);
					} else {
						_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.chase_end")), SoundSource.HOSTILE, 3, 1, false);
					}
				}
			}
			if (!entity.level.isClientSide())
				entity.discard();
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
			if (world instanceof ServerLevel _level) {
				LightningBolt entityToSpawn = EntityType.LIGHTNING_BOLT.create(_level);
				entityToSpawn.moveTo(Vec3.atBottomCenterOf(BlockPos.containing(x, y, z)));
				entityToSpawn.setVisualOnly(true);
				_level.addFreshEntity(entityToSpawn);
			}
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.LARGE_SMOKE, (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 400, 1, 1, 1, 0.01);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.FEAR_SMOKE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 100, 1, 1, 1, 0.01);
			if (!world.getBlockState(BlockPos.containing(x, y, z)).canOcclude() && world.getBlockState(BlockPos.containing(x, y - 1, z)).canOcclude()) {
				world.setBlock(BlockPos.containing(x, y, z), HybridVxModBlocks.CRIMSON_FIRE.get().defaultBlockState(), 3);
			}
		}
		if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_HasBeenOnAIStageTooLong) : 0) == 0) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_HasBeenOnAIStageTooLong, Mth.nextInt(RandomSource.create(), 7200, 12000));
		}
		if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_HasBeenOnAIStageTooLong) : 0) > 1) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_HasBeenOnAIStageTooLong, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_HasBeenOnAIStageTooLong) : 0) - 1));
		}
		if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_HasBeenOnAIStageTooLong) : 0) == 1) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_AmalgamAI, Mth.nextInt(RandomSource.create(), 0, 4));
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_HasBeenOnAIStageTooLong, 0);
		}
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 6, 6, 6), e -> true).isEmpty()) {
			if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 2, 1, false, false));
		}
		if ((entity instanceof AmalgamationEntity _datEntL108 && _datEntL108.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == true && !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).isEmpty()
				&& !(!world.getEntitiesOfClass(IronGolem.class, AABB.ofSize(new Vec3(x, y, z), 20, 20, 20), e -> true).isEmpty())) {
			if (entity instanceof Mob _entity && ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).stream().sorted(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _ent)
				_entity.setTarget(_ent);
		} else if (!world.getEntitiesOfClass(IronGolem.class, AABB.ofSize(new Vec3(x, y, z), 20, 20, 20), e -> true).isEmpty()) {
			if (entity instanceof Mob _entity && ((Entity) world.getEntitiesOfClass(IronGolem.class, AABB.ofSize(new Vec3(x, y, z), 20, 20, 20), e -> true).stream().sorted(new Object() {
				Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
					return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
				}
			}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _ent)
				_entity.setTarget(_ent);
		}
		if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 60, 255, false, false));
		if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.DOLPHINS_GRACE, 60, 4, false, false));
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.WATER || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.WATER
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.WATER || (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.WATER) {
			if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(100 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
							.collect(Collectors.toList());
					for (Entity entityiterator : _entfound) {
						if (entityiterator == (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null)) {
							if (entityiterator.isPassenger()) {
								if (entity.getDeltaMovement().x() != 0 || entity.getDeltaMovement().z() != 0) {
									entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() * 1.04), (entity.getDeltaMovement().y()), (entity.getDeltaMovement().z() * 1.04)));
								}
							}
						}
					}
				}
			}
		}
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				if ((world instanceof Level _lvl138 && _lvl138.isDay()) == true && world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))) {
					if (!world.isClientSide()) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 2,
										(float) Mth.nextDouble(RandomSource.create(), 0.8, 1));
							} else {
								_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 2, (float) Mth.nextDouble(RandomSource.create(), 0.8, 1),
										false);
							}
						}
					}
					if (!entity.level.isClientSide())
						entity.discard();
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
					if (world instanceof ServerLevel _level)
						_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
				}
			}
			world = _worldorig;
		}
		if (!(!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).isEmpty())) {
			if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_DespawnTimer) : 0) < 2400) {
				if (entity instanceof AmalgamationEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_DespawnTimer, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_DespawnTimer) : 0) + 1));
			}
			if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_DespawnTimer) : 0) >= 2400) {
				if (!world.isClientSide()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 2,
									(float) Mth.nextDouble(RandomSource.create(), 0.8, 1));
						} else {
							_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 2, (float) Mth.nextDouble(RandomSource.create(), 0.8, 1), false);
						}
					}
				}
				if (!entity.level.isClientSide())
					entity.discard();
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
			}
		}
	}
}
