package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.network.HybridVxModVariables;
import net.voxla.hybridvx.init.HybridVxModEntities;
import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

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
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AmalgamCanSpawnProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level, event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double spawnx = 0;
		double spawnz = 0;
		double sx = 0;
		double sy = 0;
		double sz = 0;
		double cavespawnx = 0;
		double cavespawny = 0;
		double cavespawnyz = 0;
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
		}.checkGamemode(entity)) && HybridVxModVariables.WorldVariables.get(world).IsDaytimeA == false) {
			if (entity.getY() > 50) {
				if (HybridVxModVariables.WorldVariables.get(world).IsDaytimeA == false && HybridVxModVariables.WorldVariables.get(world).AmalgamSpawnTimer >= 1 && HybridVxModVariables.WorldVariables.get(world).AmalgamSpawnTimer <= 10
						&& HybridVxModVariables.WorldVariables.get(world).AmalgamSpawnChance == 1 && !(!world.getEntitiesOfClass(AmalgamationEntity.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).isEmpty())
						&& HybridVxModVariables.WorldVariables.get(world).HasOneDayPassedA == true) {
					if (!(!world.getEntitiesOfClass(AmalgamationEntity.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).isEmpty())) {
						spawnx = Mth.nextInt(RandomSource.create(), -100, 100);
						spawnz = Mth.nextInt(RandomSource.create(), -100, 100);
					}
					if (!world.getBlockState(BlockPos.containing(x + spawnx, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) (x + spawnx), (int) (z + spawnz)) + 0, z + spawnz)).canOcclude()
							&& !world.getBlockState(BlockPos.containing(x + spawnx, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) (x + spawnx), (int) (z + spawnz)) + 1, z + spawnz)).canOcclude()
							&& !world.getBlockState(BlockPos.containing(x + spawnx, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) (x + spawnx), (int) (z + spawnz)) + 2, z + spawnz)).canOcclude()
							&& world.getBlockState(BlockPos.containing(x + spawnx, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) (x + spawnx), (int) (z + spawnz)) - 1, z + spawnz)).canOcclude()) {
						if (world instanceof ServerLevel _level) {
							Entity entityToSpawn = new AmalgamationEntity(HybridVxModEntities.HYBRID.get(), _level);
							entityToSpawn.moveTo((x + spawnx), (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) (x + spawnx), (int) (z + spawnz))), (z + spawnz), 0, 0);
							entityToSpawn.setYBodyRot(0);
							entityToSpawn.setYHeadRot(0);
							entityToSpawn.setDeltaMovement(0, 0, 0);
							if (entityToSpawn instanceof Mob _mobToSpawn)
								_mobToSpawn.finalizeSpawn(_level, _level.getCurrentDifficultyAt(entityToSpawn.blockPosition()), MobSpawnType.MOB_SUMMONED, null, null);
							_level.addFreshEntity(entityToSpawn);
						}
					}
				}
			} else if (entity.getY() <= 50) {
				if (!world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z)) && HybridVxModVariables.WorldVariables.get(world).AmalgamSpawnTimer >= 1 && HybridVxModVariables.WorldVariables.get(world).AmalgamSpawnTimer <= 10
						&& HybridVxModVariables.WorldVariables.get(world).AmalgamSpawnChance == 1 && !(!world.getEntitiesOfClass(AmalgamationEntity.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).isEmpty())) {
					if (!(!world.getEntitiesOfClass(AmalgamationEntity.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).isEmpty())) {
						sx = entity.getX() + Mth.nextInt(RandomSource.create(), -40, 40);
						sz = entity.getZ() + Mth.nextInt(RandomSource.create(), -40, 40);
						for (int index0 = 0; index0 < 24; index0++) {
							sy = entity.getY() + -12;
							for (int index1 = 0; index1 < 24; index1++) {
								for (int index2 = 0; index2 < 24; index2++) {
									if (world.getBlockState(BlockPos.containing(sx, sy - 1, sz)).canOcclude() && !world.getBlockState(BlockPos.containing(sx, sy + 0, sz)).canOcclude()
											&& !world.getBlockState(BlockPos.containing(sx, sy + 1, sz)).canOcclude() && !world.getBlockState(BlockPos.containing(sx, sy + 2, sz)).canOcclude()) {
										if (!(!world.getEntitiesOfClass(AmalgamationEntity.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).isEmpty())) {
											if (world instanceof ServerLevel _level) {
												Entity entityToSpawn = new AmalgamationEntity(HybridVxModEntities.HYBRID.get(), _level);
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
			}
		}
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter >= 2400
				&& (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer <= 0) {
			{
				double _setval = 12000;
				entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FearEventsTimer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter >= 2400
				&& (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer > 0) {
			FearEventsHandlerProcProcedure.execute(world, x, y, z, entity);
			{
				double _setval = (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearEventsTimer - 1;
				entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FearEventsTimer = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
