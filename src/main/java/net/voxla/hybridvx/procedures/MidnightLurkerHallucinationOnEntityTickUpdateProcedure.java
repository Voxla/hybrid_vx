package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.network.HybridVxModVariables;
import net.voxla.hybridvx.init.HybridVxModParticleTypes;
import net.voxla.hybridvx.entity.MidnightLurkerHallucinationEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
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

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

public class MidnightLurkerHallucinationOnEntityTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double raytrace_distance = 0;
		boolean entity_found = false;
		com.google.gson.JsonObject amaljsonobj = new com.google.gson.JsonObject();
		File hybridconfig = new File("");
		if (entity.isPassenger()) {
			entity.stopRiding();
		}
		if ((entity instanceof MidnightLurkerHallucinationEntity _datEntL2 && _datEntL2.getEntityData().get(MidnightLurkerHallucinationEntity.DATA_RunActivate)) == false) {
			if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty()) {
				entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).getX()), (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).getY()), (((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).stream().sorted(new Object() {
					Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
						return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
					}
				}.compareDistOf(x, y, z)).findFirst().orElse(null)).getZ())));
			}
		}
		if ((entity instanceof MidnightLurkerHallucinationEntity _datEntI ? _datEntI.getEntityData().get(MidnightLurkerHallucinationEntity.DATA_RunChance) : 0) != 1) {
			if ((entity instanceof MidnightLurkerHallucinationEntity _datEntI ? _datEntI.getEntityData().get(MidnightLurkerHallucinationEntity.DATA_AnimationDeterminer) : 0) == 0) {
				if (entity instanceof MidnightLurkerHallucinationEntity) {
					((MidnightLurkerHallucinationEntity) entity).setAnimation("animation.ml_hallucination.idle1");
				}
			} else if ((entity instanceof MidnightLurkerHallucinationEntity _datEntI ? _datEntI.getEntityData().get(MidnightLurkerHallucinationEntity.DATA_AnimationDeterminer) : 0) == 1) {
				if (entity instanceof MidnightLurkerHallucinationEntity) {
					((MidnightLurkerHallucinationEntity) entity).setAnimation("animation.ml_hallucination.idle2");
				}
			} else if ((entity instanceof MidnightLurkerHallucinationEntity _datEntI ? _datEntI.getEntityData().get(MidnightLurkerHallucinationEntity.DATA_AnimationDeterminer) : 0) == 2) {
				if (entity instanceof MidnightLurkerHallucinationEntity) {
					((MidnightLurkerHallucinationEntity) entity).setAnimation("animation.ml_hallucination.idle3");
				}
			} else if ((entity instanceof MidnightLurkerHallucinationEntity _datEntI ? _datEntI.getEntityData().get(MidnightLurkerHallucinationEntity.DATA_AnimationDeterminer) : 0) == 3) {
				if (entity instanceof MidnightLurkerHallucinationEntity) {
					((MidnightLurkerHallucinationEntity) entity).setAnimation("animation.ml_hallucination.idle4");
				}
			}
		}
		if (Math.random() > 0.9) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.FEAR_SMOKE.get()), x, (y + 1.5), z, 1, 0.3, 0.8, 0.3, 0.01);
		}
		if (Math.random() > 0.9) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.SMOKE, x, (y + 1.5), z, 1, 0.3, 0.8, 0.3, 0.01);
		}
		if (Math.random() > 0.9) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 1, 0.3, 0.8, 0.3, 0.01);
		}
		if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 60, 255, false, false));
		if ((entity instanceof MidnightLurkerHallucinationEntity _datEntL27 && _datEntL27.getEntityData().get(MidnightLurkerHallucinationEntity.DATA_RunActivate)) == false) {
			if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
				_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 200, false, false));
		}
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 30, 30, 30), e -> true).isEmpty()
				&& (entity instanceof MidnightLurkerHallucinationEntity _datEntI ? _datEntI.getEntityData().get(MidnightLurkerHallucinationEntity.DATA_RunChance) : 0) == 1) {
			if ((entity instanceof MidnightLurkerHallucinationEntity _datEntL31 && _datEntL31.getEntityData().get(MidnightLurkerHallucinationEntity.DATA_RunActivate)) == false) {
				if (entity instanceof MidnightLurkerHallucinationEntity _datEntSetL)
					_datEntSetL.getEntityData().set(MidnightLurkerHallucinationEntity.DATA_RunActivate, true);
			}
		}
		if ((entity instanceof MidnightLurkerHallucinationEntity _datEntI ? _datEntI.getEntityData().get(MidnightLurkerHallucinationEntity.DATA_NaturalDisappearTimer) : 0) > 1) {
			if (entity instanceof MidnightLurkerHallucinationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(MidnightLurkerHallucinationEntity.DATA_NaturalDisappearTimer,
						(int) ((entity instanceof MidnightLurkerHallucinationEntity _datEntI ? _datEntI.getEntityData().get(MidnightLurkerHallucinationEntity.DATA_NaturalDisappearTimer) : 0) - 1));
		}
		if ((world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.WATER || (world.getBlockState(BlockPos.containing(x, y, z))).getBlock() == Blocks.WATER
				|| (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.WATER || (world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.WATER) {
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 1,
								(float) Mth.nextDouble(RandomSource.create(), 0.8, 1));
					} else {
						_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 1, (float) Mth.nextDouble(RandomSource.create(), 0.8, 1), false);
					}
				}
			}
			if (!entity.level.isClientSide())
				entity.discard();
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.3, 0.8, 0.3, 0.01);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.FEAR_SMOKE.get()), x, (y + 1.5), z, 50, 0.3, 0.8, 0.3, 0.01);
		}
		if ((entity instanceof MidnightLurkerHallucinationEntity _datEntI ? _datEntI.getEntityData().get(MidnightLurkerHallucinationEntity.DATA_NaturalDisappearTimer) : 0) == 1) {
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 1,
								(float) Mth.nextDouble(RandomSource.create(), 0.8, 1));
					} else {
						_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 1, (float) Mth.nextDouble(RandomSource.create(), 0.8, 1), false);
					}
				}
			}
			if (!entity.level.isClientSide())
				entity.discard();
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.3, 0.8, 0.3, 0.01);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.FEAR_SMOKE.get()), x, (y + 1.5), z, 50, 0.3, 0.8, 0.3, 0.01);
		}
		if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).isEmpty()) {
			hybridconfig = new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "hybridvxconfig.json");
			{
				try {
					BufferedReader bufferedReader = new BufferedReader(new FileReader(hybridconfig));
					StringBuilder jsonstringbuilder = new StringBuilder();
					String line;
					while ((line = bufferedReader.readLine()) != null) {
						jsonstringbuilder.append(line);
					}
					bufferedReader.close();
					amaljsonobj = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
					if (amaljsonobj.get("hallucination_jumpscare").getAsBoolean() == true) {
						if (Math.random() > 0.6) {
							if ((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).VoidGatewayJumpscareTimer < 1) {
								{
									double _setval = 19;
									((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
										Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
											return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
										}
									}.compareDistOf(x, y, z)).findFirst().orElse(null)).getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
										capability.VoidGatewayJumpscareTimer = _setval;
										capability.syncPlayerVariables(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 15, 15, 15), e -> true).stream().sorted(new Object() {
											Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
												return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
											}
										}.compareDistOf(x, y, z)).findFirst().orElse(null)));
									});
								}
							}
						}
					}
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 1,
								(float) Mth.nextDouble(RandomSource.create(), 0.8, 1));
					} else {
						_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 1, (float) Mth.nextDouble(RandomSource.create(), 0.8, 1), false);
					}
				}
			}
			if (!entity.level.isClientSide())
				entity.discard();
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.3, 0.8, 0.3, 0.01);
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.FEAR_SMOKE.get()), x, (y + 1.5), z, 50, 0.3, 0.8, 0.3, 0.01);
		}
		if ((entity instanceof MidnightLurkerHallucinationEntity _datEntI ? _datEntI.getEntityData().get(MidnightLurkerHallucinationEntity.DATA_CanBeSeenChance) : 0) == 2) {
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(200 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
						.collect(Collectors.toList());
				for (Entity entityiterator : _entfound) {
					if (entityiterator instanceof Player) {
						raytrace_distance = 0;
						for (int index0 = 0; index0 < 100; index0++) {
							if (!world.getEntitiesOfClass(MidnightLurkerHallucinationEntity.class,
									AABB.ofSize(new Vec3(
											(entityiterator.level.clip(new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER,
													ClipContext.Fluid.NONE, entityiterator)).getBlockPos().getX()),
											(entityiterator.level.clip(new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER,
													ClipContext.Fluid.NONE, entityiterator)).getBlockPos().getY()),
											(entityiterator.level.clip(new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER,
													ClipContext.Fluid.NONE, entityiterator)).getBlockPos().getZ())),
											1, 1, 1),
									e -> true).isEmpty()) {
								entity_found = true;
							} else {
								entity_found = false;
								raytrace_distance = raytrace_distance + 1;
							}
						}
						if (entity_found || !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).isEmpty()) {
							hybridconfig = new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "hybridvxconfig.json");
							{
								try {
									BufferedReader bufferedReader = new BufferedReader(new FileReader(hybridconfig));
									StringBuilder jsonstringbuilder = new StringBuilder();
									String line;
									while ((line = bufferedReader.readLine()) != null) {
										jsonstringbuilder.append(line);
									}
									bufferedReader.close();
									amaljsonobj = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
									if (amaljsonobj.get("hallucination_jumpscare").getAsBoolean() == true) {
										if (Math.random() > 0.6) {
											if ((((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).stream().sorted(new Object() {
												Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
													return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
												}
											}.compareDistOf(x, y, z)).findFirst().orElse(null)).getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).VoidGatewayJumpscareTimer < 1) {
												{
													double _setval = 19;
													((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).stream().sorted(new Object() {
														Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
															return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
														}
													}.compareDistOf(x, y, z)).findFirst().orElse(null)).getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
														capability.VoidGatewayJumpscareTimer = _setval;
														capability.syncPlayerVariables(((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).stream().sorted(new Object() {
															Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
																return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
															}
														}.compareDistOf(x, y, z)).findFirst().orElse(null)));
													});
												}
											}
										}
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
							if (!world.isClientSide()) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 1,
												(float) Mth.nextDouble(RandomSource.create(), 0.8, 1));
									} else {
										_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 1,
												(float) Mth.nextDouble(RandomSource.create(), 0.8, 1), false);
									}
								}
							}
							if (!entity.level.isClientSide())
								entity.discard();
							if (world instanceof ServerLevel _level)
								_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.3, 0.8, 0.3, 0.01);
							if (world instanceof ServerLevel _level)
								_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.FEAR_SMOKE.get()), x, (y + 1.5), z, 50, 0.3, 0.8, 0.3, 0.01);
						}
					}
				}
			}
		}
	}
}
