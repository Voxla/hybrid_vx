package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.init.HybridVxModParticleTypes;
import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ShieldItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import java.util.Comparator;

public class AmalgamGrabPlayerProcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_HasGrabbedPlayer) : 0) > 1) {
			if (entity instanceof AmalgamationEntity) {
				((AmalgamationEntity) entity).setAnimation("animation.amalgamation.grab");
			}
			if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 3, 200, false, false));
			if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_HasGrabbedPlayer) : 0) == 45) {
				if (!world.isClientSide()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.leash_knot.break")), SoundSource.PLAYERS, 1, 1);
						} else {
							_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.leash_knot.break")), SoundSource.PLAYERS, 1, 1, false);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")), SoundSource.PLAYERS, 1, 1);
						} else {
							_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")), SoundSource.PLAYERS, 1, 1, false);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.grab_jumpscare")), SoundSource.HOSTILE, 2, 1);
						} else {
							_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.grab_jumpscare")), SoundSource.HOSTILE, 2, 1, false);
						}
					}
				}
			}
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_HasGrabbedPlayer, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_HasGrabbedPlayer) : 0) - 1));
			if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty()) {
				((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).setDeltaMovement(new Vec3(0, 0, 0));
				entity.setDeltaMovement(new Vec3(0, (entity.getDeltaMovement().y()), 0));
				{
					Entity _ent = ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null));
					_ent.teleportTo((entity.getX() + entity.getLookAngle().x * 1.3), (entity.getY() + 0.8), (entity.getZ() + entity.getLookAngle().z * 1.3));
					if (_ent instanceof ServerPlayer _serverPlayer)
						_serverPlayer.connection.teleport((entity.getX() + entity.getLookAngle().x * 1.3), (entity.getY() + 0.8), (entity.getZ() + entity.getLookAngle().z * 1.3), _ent.getYRot(), _ent.getXRot());
				}
				((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((entity.getX()), (entity.getY() + 2.2), (entity.getZ())));
				if (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _entity && !_entity.level.isClientSide())
					_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2, 2, false, false));
				if (entity instanceof AmalgamationEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_GrabPlayerCooldown, 40);
			}
		} else if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_HasGrabbedPlayer) : 0) == 0) {
			if ((((AmalgamationEntity) entity).animationprocedure).equals("animation.amalgamation.grab")) {
				if (entity instanceof AmalgamationEntity) {
					((AmalgamationEntity) entity).setAnimation("empty");
				}
			}
		}
		if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_HasGrabbedPlayer) : 0) == 1) {
			if (Math.random() > 0.4 && !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty()
					&& (entity instanceof AmalgamationEntity _datEntL35 && _datEntL35.getEntityData().get(AmalgamationEntity.DATA_IsGrabJumpscareThenDontKill)) == false) {
				if (!(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _livEnt37 && _livEnt37.isBlocking())) {
					((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
						}
					}.compareDistOf(x, y, z)).findFirst().orElse(null))
							.hurt(new DamageSource(world.registryAccess().registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(ResourceKey.create(Registries.DAMAGE_TYPE, new ResourceLocation("hybrid_vx:amalgam_damage")))), 50);
					if (!world.isClientSide()) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.grab_kill")), SoundSource.PLAYERS, 1, (float) 0.9);
							} else {
								_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.grab_kill")), SoundSource.PLAYERS, 1, (float) 0.9, false);
							}
						}
					}
				} else if (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)) instanceof LivingEntity _livEnt44 && _livEnt44.isBlocking()) {
					if ((entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY).getItem() instanceof ShieldItem) {
						{
							ItemStack _ist = (entity instanceof LivingEntity _livEnt ? _livEnt.getOffhandItem() : ItemStack.EMPTY);
							if (_ist.hurt(103, RandomSource.create(), null)) {
								_ist.shrink(1);
								_ist.setDamageValue(0);
							}
						}
					} else if ((entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY).getItem() instanceof ShieldItem) {
						{
							ItemStack _ist = (entity instanceof LivingEntity _livEnt ? _livEnt.getMainHandItem() : ItemStack.EMPTY);
							if (_ist.hurt(103, RandomSource.create(), null)) {
								_ist.shrink(1);
								_ist.setDamageValue(0);
							}
						}
					}
					if (!world.isClientSide()) {
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.shield.block")), SoundSource.PLAYERS, 1, (float) 0.9);
							} else {
								_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.shield.block")), SoundSource.PLAYERS, 1, (float) 0.9, false);
							}
						}
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.shield.break")), SoundSource.PLAYERS, 1, (float) 0.9);
							} else {
								_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.shield.break")), SoundSource.PLAYERS, 1, (float) 0.9, false);
							}
						}
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.grab_kill")), SoundSource.PLAYERS, (float) 0.3, (float) 0.9);
							} else {
								_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.grab_kill")), SoundSource.PLAYERS, (float) 0.3, (float) 0.9, false);
							}
						}
					}
				}
			}
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.leash_knot.break")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.leash_knot.break")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")), SoundSource.PLAYERS, 1, 1);
					} else {
						_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.armor.equip_leather")), SoundSource.PLAYERS, 1, 1, false);
					}
				}
			}
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_HasGrabbedPlayer, 0);
			if ((entity instanceof AmalgamationEntity _datEntL61 && _datEntL61.getEntityData().get(AmalgamationEntity.DATA_IsGrabJumpscareThenDontKill)) == true) {
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
		if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_GrabPlayerCooldown) : 0) > 0) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_GrabPlayerCooldown, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_GrabPlayerCooldown) : 0) - 1));
		}
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty()
				&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_GrabPlayerCooldown) : 0) == 0
				&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_HasGrabbedPlayer) : 0) == 0
				&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_GrabChance) : 0) == 2) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_HasGrabbedPlayer, 46);
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_GrabChance, 0);
		}
		if ((entity instanceof AmalgamationEntity _datEntL83 && _datEntL83.getEntityData().get(AmalgamationEntity.DATA_IsGrabJumpscareThenDontKill)) == true
				&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_HasGrabbedPlayer) : 0) == 0) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_HasGrabbedPlayer, 46);
		}
	}
}
