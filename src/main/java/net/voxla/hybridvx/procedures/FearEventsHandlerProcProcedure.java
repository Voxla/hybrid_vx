package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.network.HybridVxModVariables;
import net.voxla.hybridvx.init.HybridVxModParticleTypes;
import net.voxla.hybridvx.init.HybridVxModEntities;
import net.voxla.hybridvx.entity.VoidGatewayHallucinationEntity;
import net.voxla.hybridvx.entity.MidnightLurkerHallucinationEntity;
import net.voxla.hybridvx.entity.MidnightLurkerFootstepHallucinationEntity;
import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.GameType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import java.util.Comparator;

public class FearEventsHandlerProcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double Px = 0;
		double Pz = 0;
		if ((world instanceof Level _lvl ? _lvl.dimension() : (world instanceof WorldGenLevel _wgl ? _wgl.getLevel().dimension() : Level.OVERWORLD)) == Level.OVERWORLD && !(new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayer _serverPlayer) {
					return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.CREATIVE;
				} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.CREATIVE;
				}
				return false;
			}
		}.checkGamemode(entity)) && !(new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayer _serverPlayer) {
					return _serverPlayer.gameMode.getGameModeForPlayer() == GameType.SPECTATOR;
				} else if (_ent.level.isClientSide() && _ent instanceof Player _player) {
					return Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()) != null && Minecraft.getInstance().getConnection().getPlayerInfo(_player.getGameProfile().getId()).getGameMode() == GameType.SPECTATOR;
				}
				return false;
			}
		}.checkGamemode(entity))) {
			if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter >= 2400
					&& (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter < 4800) {
				if (((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 2400
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 4800
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 7200
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 9600) && Math.random() > 0.8) {
					{
						double _setval = 301;
						entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.PanicAttackTimer = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
				if (((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 1200
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 5400
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 8400
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 9300) && Math.random() > 0.8) {
					if (Math.random() <= 0.8 && Math.random() > 0.6) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.ground")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.ground")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					} else if (Math.random() <= 0.6 && Math.random() > 0.4) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.music")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.music")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					} else if (Math.random() <= 0.4 && Math.random() > 0.2) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.rumble")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.rumble")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					} else if (Math.random() <= 0.2) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.wind")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.wind")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					}
				}
			} else if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter >= 4800
					&& (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter < 7200) {
				if (((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 2400
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 4800
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 7200
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 9600) && Math.random() > 0.7) {
					{
						double _setval = 301;
						entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.PanicAttackTimer = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
				if (((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 1800
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 3900
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 6700
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 8700) && Math.random() > 0.6) {
					for (int index0 = 0; index0 < 3; index0++) {
						world.addParticle((SimpleParticleType) (HybridVxModParticleTypes.LURKER_EYE_PARTICLE.get()), (x + Mth.nextDouble(RandomSource.create(), -6, 6)), (y + Mth.nextDouble(RandomSource.create(), 0, 6)),
								(z + Mth.nextDouble(RandomSource.create(), -6, 6)), 0, 0, 0);
					}
				}
				if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 1000 && Math.random() > 0.8
						&& !(!world.getEntitiesOfClass(MidnightLurkerFootstepHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
					if (!(!world.getEntitiesOfClass(MidnightLurkerFootstepHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
						sx = entity.getX() + Mth.nextInt(RandomSource.create(), -80, 80);
						sz = entity.getZ() + Mth.nextInt(RandomSource.create(), -80, 80);
						for (int index1 = 0; index1 < 24; index1++) {
							sy = entity.getY() + -12;
							for (int index2 = 0; index2 < 24; index2++) {
								for (int index3 = 0; index3 < 24; index3++) {
									if (world.getBlockState(BlockPos.containing(sx, sy - 1, sz)).canOcclude() && !world.getBlockState(BlockPos.containing(sx, sy + 0, sz)).canOcclude()
											&& !world.getBlockState(BlockPos.containing(sx, sy + 1, sz)).canOcclude() && !world.getBlockState(BlockPos.containing(sx, sy + 2, sz)).canOcclude()) {
										if (!(!world.getEntitiesOfClass(MidnightLurkerFootstepHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
											if (world instanceof ServerLevel _level) {
												Entity entityToSpawn = new MidnightLurkerFootstepHallucinationEntity(HybridVxModEntities.MIDNIGHT_LURKER_FOOTSTEP_HALLUCINATION.get(), _level);
												entityToSpawn.moveTo(sx, sy, sz, 0, 0);
												entityToSpawn.setYBodyRot(0);
												entityToSpawn.setYHeadRot(0);
												entityToSpawn.setDeltaMovement(0, 0, 0);
												if (entityToSpawn instanceof Mob _mobToSpawn)
													_mobToSpawn.finalizeSpawn(_level, _level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
												_level.addFreshEntity(entityToSpawn);
											}
										}
									}
								}
								sy = sy + 1;
							}
						}
					}
				}
				if ((HybridVxModVariables.WorldVariables.get(world).IsDaytimeA == false || y <= 50)
						&& ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 1200
								|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 5400
								|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 8400
								|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 9300)
						&& Math.random() > 0.7) {
					if (Math.random() > 0.8) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (Math.random() > 0.4) {
							if (!world.isClientSide()) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.fear.hal")), SoundSource.AMBIENT, 4, 1);
									} else {
										_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.fear.hal")),
												SoundSource.AMBIENT, 4, 1, false);
									}
								}
							}
						} else {
							if (!world.isClientSide()) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.fear.13")), SoundSource.AMBIENT, 4, 1);
									} else {
										_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.fear.13")),
												SoundSource.AMBIENT, 4, 1, false);
									}
								}
							}
						}
					} else if (Math.random() <= 0.8 && Math.random() > 0.6) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.ground")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.ground")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					} else if (Math.random() <= 0.6 && Math.random() > 0.4) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.music")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.music")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					} else if (Math.random() <= 0.4 && Math.random() > 0.2) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.rumble")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.rumble")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					} else if (Math.random() <= 0.2) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.wind")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.wind")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					}
				}
			} else if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter >= 7200
					&& (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter < 9600) {
				if (((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 2400
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 4800
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 7200
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 9600) && Math.random() > 0.4) {
					{
						double _setval = 301;
						entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.PanicAttackTimer = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
				if (((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 1800
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 3900
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 6700
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 8700) && Math.random() > 0.5) {
					for (int index4 = 0; index4 < 7; index4++) {
						world.addParticle((SimpleParticleType) (HybridVxModParticleTypes.LURKER_EYE_PARTICLE.get()), (x + Mth.nextDouble(RandomSource.create(), -6, 6)), (y + Mth.nextDouble(RandomSource.create(), 0, 6)),
								(z + Mth.nextDouble(RandomSource.create(), -6, 6)), 0, 0, 0);
					}
				}
				if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 1000 && Math.random() > 0.7
						&& !(!world.getEntitiesOfClass(MidnightLurkerFootstepHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
					if (!(!world.getEntitiesOfClass(MidnightLurkerFootstepHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
						sx = entity.getX() + Mth.nextInt(RandomSource.create(), -80, 80);
						sz = entity.getZ() + Mth.nextInt(RandomSource.create(), -80, 80);
						for (int index5 = 0; index5 < 24; index5++) {
							sy = entity.getY() + -12;
							for (int index6 = 0; index6 < 24; index6++) {
								for (int index7 = 0; index7 < 24; index7++) {
									if (world.getBlockState(BlockPos.containing(sx, sy - 1, sz)).canOcclude() && !world.getBlockState(BlockPos.containing(sx, sy + 0, sz)).canOcclude()
											&& !world.getBlockState(BlockPos.containing(sx, sy + 1, sz)).canOcclude() && !world.getBlockState(BlockPos.containing(sx, sy + 2, sz)).canOcclude()) {
										if (!(!world.getEntitiesOfClass(MidnightLurkerFootstepHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
											if (world instanceof ServerLevel _level) {
												Entity entityToSpawn = new MidnightLurkerFootstepHallucinationEntity(HybridVxModEntities.MIDNIGHT_LURKER_FOOTSTEP_HALLUCINATION.get(), _level);
												entityToSpawn.moveTo(sx, sy, sz, 0, 0);
												entityToSpawn.setYBodyRot(0);
												entityToSpawn.setYHeadRot(0);
												entityToSpawn.setDeltaMovement(0, 0, 0);
												if (entityToSpawn instanceof Mob _mobToSpawn)
													_mobToSpawn.finalizeSpawn(_level, _level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
												_level.addFreshEntity(entityToSpawn);
											}
										}
									}
								}
								sy = sy + 1;
							}
						}
					}
				}
				if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 6000 && Math.random() > 0.7
						&& !(!world.getEntitiesOfClass(VoidGatewayHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
					if (!(!world.getEntitiesOfClass(VoidGatewayHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
						sx = entity.getX() + Mth.nextInt(RandomSource.create(), -80, 80);
						sz = entity.getZ() + Mth.nextInt(RandomSource.create(), -80, 80);
						for (int index8 = 0; index8 < 24; index8++) {
							sy = entity.getY() + -12;
							for (int index9 = 0; index9 < 24; index9++) {
								for (int index10 = 0; index10 < 24; index10++) {
									if (world.getBlockState(BlockPos.containing(sx, sy - 1, sz)).canOcclude() && !world.getBlockState(BlockPos.containing(sx, sy + 0, sz)).canOcclude()
											&& !world.getBlockState(BlockPos.containing(sx, sy + 1, sz)).canOcclude() && !world.getBlockState(BlockPos.containing(sx, sy + 2, sz)).canOcclude()) {
										if (!(!world.getEntitiesOfClass(VoidGatewayHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
											if (world instanceof ServerLevel _level) {
												Entity entityToSpawn = new VoidGatewayHallucinationEntity(HybridVxModEntities.VOID_GATEWAY_HALLUCINATION.get(), _level);
												entityToSpawn.moveTo(sx, sy, sz, 0, 0);
												entityToSpawn.setYBodyRot(0);
												entityToSpawn.setYHeadRot(0);
												entityToSpawn.setDeltaMovement(0, 0, 0);
												if (entityToSpawn instanceof Mob _mobToSpawn)
													_mobToSpawn.finalizeSpawn(_level, _level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
												_level.addFreshEntity(entityToSpawn);
											}
										}
									}
								}
								sy = sy + 1;
							}
						}
					}
				}
				if ((HybridVxModVariables.WorldVariables.get(world).IsDaytimeA == false || y <= 50)
						&& ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 1200
								|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 5400
								|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 8400
								|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 9300)
						&& Math.random() > 0.5) {
					if (Math.random() > 0.8) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (Math.random() > 0.4) {
							if (!world.isClientSide()) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.fear.hal")), SoundSource.AMBIENT, 4, 1);
									} else {
										_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.fear.hal")),
												SoundSource.AMBIENT, 4, 1, false);
									}
								}
							}
						} else {
							if (!world.isClientSide()) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.fear.13")), SoundSource.AMBIENT, 4, 1);
									} else {
										_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.fear.13")),
												SoundSource.AMBIENT, 4, 1, false);
									}
								}
							}
						}
					} else if (Math.random() <= 0.8 && Math.random() > 0.6) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.ground")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.ground")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					} else if (Math.random() <= 0.6 && Math.random() > 0.4) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.music")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.music")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					} else if (Math.random() <= 0.4 && Math.random() > 0.2) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.rumble")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.rumble")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					} else if (Math.random() <= 0.2) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.wind")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.wind")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					}
				}
				if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 4000 && Math.random() > 0.6) {
					if (!world.getBlockState(BlockPos.containing(entity.getX() + entity.getLookAngle().x * 1, entity.getY() + 0, entity.getZ() + entity.getLookAngle().z * 1)).canOcclude()
							&& !world.getBlockState(BlockPos.containing(entity.getX() + entity.getLookAngle().x * 1, entity.getY() + 1, entity.getZ() + entity.getLookAngle().z * 1)).canOcclude()
							&& !world.getBlockState(BlockPos.containing(entity.getX() + entity.getLookAngle().x * 1, entity.getY() + 2, entity.getZ() + entity.getLookAngle().z * 1)).canOcclude()) {
						if (world instanceof ServerLevel _level) {
							Entity entityToSpawn = new AmalgamationEntity(HybridVxModEntities.HYBRID.get(), _level);
							entityToSpawn.moveTo((entity.getX() + entity.getLookAngle().x * 1), (entity.getY() + 0), (entity.getZ() + entity.getLookAngle().z * 1), (float) (entity.getYRot() - 180), 0);
							entityToSpawn.setYBodyRot((float) (entity.getYRot() - 180));
							entityToSpawn.setYHeadRot((float) (entity.getYRot() - 180));
							entityToSpawn.setDeltaMovement(0, 0, 0);
							if (entityToSpawn instanceof Mob _mobToSpawn)
								_mobToSpawn.finalizeSpawn(_level, _level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
							_level.addFreshEntity(entityToSpawn);
						}
						if (!world.getEntitiesOfClass(AmalgamationEntity.class, AABB.ofSize(new Vec3((entity.getX() + entity.getLookAngle().x * 1), (entity.getY() + 0), (entity.getZ() + entity.getLookAngle().z * 1)), 4, 4, 4), e -> true).isEmpty()) {
							if (((Entity) world.getEntitiesOfClass(AmalgamationEntity.class, AABB.ofSize(new Vec3((entity.getX() + entity.getLookAngle().x * 1), (entity.getY() + 0), (entity.getZ() + entity.getLookAngle().z * 1)), 4, 4, 4), e -> true)
									.stream().sorted(new Object() {
										Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
											return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
										}
									}.compareDistOf((entity.getX() + entity.getLookAngle().x * 1), (entity.getY() + 0), (entity.getZ() + entity.getLookAngle().z * 1))).findFirst().orElse(null)) instanceof AmalgamationEntity _datEntSetL)
								_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_IsGrabJumpscareThenDontKill, true);
						}
					}
				}
			} else if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter >= 9600) {
				if (((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 2400
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 4800
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 7200
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 9600) && Math.random() > 0.2) {
					{
						double _setval = 301;
						entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.PanicAttackTimer = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
				if (((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 1800
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 3900
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 6700
						|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 8700) && Math.random() > 0.4) {
					for (int index11 = 0; index11 < 15; index11++) {
						world.addParticle((SimpleParticleType) (HybridVxModParticleTypes.LURKER_EYE_PARTICLE.get()), (x + Mth.nextDouble(RandomSource.create(), -6, 6)), (y + Mth.nextDouble(RandomSource.create(), 0, 6)),
								(z + Mth.nextDouble(RandomSource.create(), -6, 6)), 0, 0, 0);
					}
				}
				if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 1000 && Math.random() > 0.6
						&& !(!world.getEntitiesOfClass(MidnightLurkerFootstepHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
					if (!(!world.getEntitiesOfClass(MidnightLurkerFootstepHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
						sx = entity.getX() + Mth.nextInt(RandomSource.create(), -80, 80);
						sz = entity.getZ() + Mth.nextInt(RandomSource.create(), -80, 80);
						for (int index12 = 0; index12 < 24; index12++) {
							sy = entity.getY() + -12;
							for (int index13 = 0; index13 < 24; index13++) {
								for (int index14 = 0; index14 < 24; index14++) {
									if (world.getBlockState(BlockPos.containing(sx, sy - 1, sz)).canOcclude() && !world.getBlockState(BlockPos.containing(sx, sy + 0, sz)).canOcclude()
											&& !world.getBlockState(BlockPos.containing(sx, sy + 1, sz)).canOcclude() && !world.getBlockState(BlockPos.containing(sx, sy + 2, sz)).canOcclude()) {
										if (!(!world.getEntitiesOfClass(MidnightLurkerFootstepHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
											if (world instanceof ServerLevel _level) {
												Entity entityToSpawn = new MidnightLurkerFootstepHallucinationEntity(HybridVxModEntities.MIDNIGHT_LURKER_FOOTSTEP_HALLUCINATION.get(), _level);
												entityToSpawn.moveTo(sx, sy, sz, 0, 0);
												entityToSpawn.setYBodyRot(0);
												entityToSpawn.setYHeadRot(0);
												entityToSpawn.setDeltaMovement(0, 0, 0);
												if (entityToSpawn instanceof Mob _mobToSpawn)
													_mobToSpawn.finalizeSpawn(_level, _level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
												_level.addFreshEntity(entityToSpawn);
											}
										}
									}
								}
								sy = sy + 1;
							}
						}
					}
				}
				if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 6000 && Math.random() > 0.6
						&& !(!world.getEntitiesOfClass(VoidGatewayHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
					if (!(!world.getEntitiesOfClass(VoidGatewayHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
						sx = entity.getX() + Mth.nextInt(RandomSource.create(), -80, 80);
						sz = entity.getZ() + Mth.nextInt(RandomSource.create(), -80, 80);
						for (int index15 = 0; index15 < 24; index15++) {
							sy = entity.getY() + -12;
							for (int index16 = 0; index16 < 24; index16++) {
								for (int index17 = 0; index17 < 24; index17++) {
									if (world.getBlockState(BlockPos.containing(sx, sy - 1, sz)).canOcclude() && !world.getBlockState(BlockPos.containing(sx, sy + 0, sz)).canOcclude()
											&& !world.getBlockState(BlockPos.containing(sx, sy + 1, sz)).canOcclude() && !world.getBlockState(BlockPos.containing(sx, sy + 2, sz)).canOcclude()) {
										if (!(!world.getEntitiesOfClass(VoidGatewayHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
											if (world instanceof ServerLevel _level) {
												Entity entityToSpawn = new VoidGatewayHallucinationEntity(HybridVxModEntities.VOID_GATEWAY_HALLUCINATION.get(), _level);
												entityToSpawn.moveTo(sx, sy, sz, 0, 0);
												entityToSpawn.setYBodyRot(0);
												entityToSpawn.setYHeadRot(0);
												entityToSpawn.setDeltaMovement(0, 0, 0);
												if (entityToSpawn instanceof Mob _mobToSpawn)
													_mobToSpawn.finalizeSpawn(_level, _level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
												_level.addFreshEntity(entityToSpawn);
											}
										}
									}
								}
								sy = sy + 1;
							}
						}
					}
				}
				if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 8000 && Math.random() > 0.4
						&& !(!world.getEntitiesOfClass(MidnightLurkerHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
					if (!(!world.getEntitiesOfClass(MidnightLurkerHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
						sx = entity.getX() + Mth.nextInt(RandomSource.create(), -80, 80);
						sz = entity.getZ() + Mth.nextInt(RandomSource.create(), -80, 80);
						for (int index18 = 0; index18 < 24; index18++) {
							sy = entity.getY() + -12;
							for (int index19 = 0; index19 < 24; index19++) {
								for (int index20 = 0; index20 < 24; index20++) {
									if (world.getBlockState(BlockPos.containing(sx, sy - 1, sz)).canOcclude() && !world.getBlockState(BlockPos.containing(sx, sy + 0, sz)).canOcclude()
											&& !world.getBlockState(BlockPos.containing(sx, sy + 1, sz)).canOcclude() && !world.getBlockState(BlockPos.containing(sx, sy + 2, sz)).canOcclude()) {
										if (!(!world.getEntitiesOfClass(MidnightLurkerHallucinationEntity.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
											if (world instanceof ServerLevel _level) {
												Entity entityToSpawn = new MidnightLurkerHallucinationEntity(HybridVxModEntities.MIDNIGHT_LURKER_HALLUCINATION.get(), _level);
												entityToSpawn.moveTo(sx, sy, sz, 0, 0);
												entityToSpawn.setYBodyRot(0);
												entityToSpawn.setYHeadRot(0);
												entityToSpawn.setDeltaMovement(0, 0, 0);
												if (entityToSpawn instanceof Mob _mobToSpawn)
													_mobToSpawn.finalizeSpawn(_level, _level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
												_level.addFreshEntity(entityToSpawn);
											}
										}
									}
								}
								sy = sy + 1;
							}
						}
					}
				}
				if ((HybridVxModVariables.WorldVariables.get(world).IsDaytimeA == false || y <= 50)
						&& ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 1200
								|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 5400
								|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 8400
								|| (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 9300)
						&& Math.random() > 0.4) {
					if (Math.random() > 0.8) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (Math.random() > 0.4) {
							if (!world.isClientSide()) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.fear.hal")), SoundSource.AMBIENT, 4, 1);
									} else {
										_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.fear.hal")),
												SoundSource.AMBIENT, 4, 1, false);
									}
								}
							}
						} else {
							if (!world.isClientSide()) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.fear.13")), SoundSource.AMBIENT, 4, 1);
									} else {
										_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.fear.13")),
												SoundSource.AMBIENT, 4, 1, false);
									}
								}
							}
						}
					} else if (Math.random() <= 0.8 && Math.random() > 0.6) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.ground")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.ground")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					} else if (Math.random() <= 0.6 && Math.random() > 0.4) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.music")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.music")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					} else if (Math.random() <= 0.4 && Math.random() > 0.2) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.rumble")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.rumble")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					} else if (Math.random() <= 0.2) {
						Px = entity.getX() + Mth.nextInt(RandomSource.create(), -30, 30);
						Pz = entity.getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.wind")), SoundSource.AMBIENT, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.wind")),
											SoundSource.AMBIENT, 4, 1, false);
								}
							}
						}
					}
				}
				if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer == 4000 && Math.random() > 0.5) {
					if (!world.getBlockState(BlockPos.containing(entity.getX() + entity.getLookAngle().x * 1, entity.getY() + 0, entity.getZ() + entity.getLookAngle().z * 1)).canOcclude()
							&& !world.getBlockState(BlockPos.containing(entity.getX() + entity.getLookAngle().x * 1, entity.getY() + 1, entity.getZ() + entity.getLookAngle().z * 1)).canOcclude()
							&& !world.getBlockState(BlockPos.containing(entity.getX() + entity.getLookAngle().x * 1, entity.getY() + 2, entity.getZ() + entity.getLookAngle().z * 1)).canOcclude()) {
						if (world instanceof ServerLevel _level) {
							Entity entityToSpawn = new AmalgamationEntity(HybridVxModEntities.HYBRID.get(), _level);
							entityToSpawn.moveTo((entity.getX() + entity.getLookAngle().x * 1), (entity.getY() + 0), (entity.getZ() + entity.getLookAngle().z * 1), (float) (entity.getYRot() - 180), 0);
							entityToSpawn.setYBodyRot((float) (entity.getYRot() - 180));
							entityToSpawn.setYHeadRot((float) (entity.getYRot() - 180));
							entityToSpawn.setDeltaMovement(0, 0, 0);
							if (entityToSpawn instanceof Mob _mobToSpawn)
								_mobToSpawn.finalizeSpawn(_level, _level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
							_level.addFreshEntity(entityToSpawn);
						}
						if (!world.getEntitiesOfClass(AmalgamationEntity.class, AABB.ofSize(new Vec3((entity.getX() + entity.getLookAngle().x * 1), (entity.getY() + 0), (entity.getZ() + entity.getLookAngle().z * 1)), 4, 4, 4), e -> true).isEmpty()) {
							if (((Entity) world.getEntitiesOfClass(AmalgamationEntity.class, AABB.ofSize(new Vec3((entity.getX() + entity.getLookAngle().x * 1), (entity.getY() + 0), (entity.getZ() + entity.getLookAngle().z * 1)), 4, 4, 4), e -> true)
									.stream().sorted(new Object() {
										Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
											return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
										}
									}.compareDistOf((entity.getX() + entity.getLookAngle().x * 1), (entity.getY() + 0), (entity.getZ() + entity.getLookAngle().z * 1))).findFirst().orElse(null)) instanceof AmalgamationEntity _datEntSetL)
								_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_IsGrabJumpscareThenDontKill, true);
						}
					}
				}
			}
		}
	}
}
