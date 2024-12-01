package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.init.HybridVxModParticleTypes;
import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

public class AmalgamUndergroundProcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double rayfind_player = 0;
		boolean player_found = false;
		if ((entity instanceof AmalgamationEntity _datEntL0 && _datEntL0.getEntityData().get(AmalgamationEntity.DATA_SpawnedUnderground)) == true
				&& (entity instanceof AmalgamationEntity _datEntL1 && _datEntL1.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == false && !world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))) {
			rayfind_player = 0;
			for (int index0 = 0; index0 < 100; index0++) {
				if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(
						(entity.level.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(rayfind_player)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity)).getBlockPos().getX()),
						(entity.level.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(rayfind_player)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity)).getBlockPos().getY()),
						(entity.level.clip(new ClipContext(entity.getEyePosition(1f), entity.getEyePosition(1f).add(entity.getViewVector(1f).scale(rayfind_player)), ClipContext.Block.COLLIDER, ClipContext.Fluid.NONE, entity)).getBlockPos().getZ())),
						1, 1, 1), e -> true).isEmpty()) {
					player_found = true;
				} else {
					player_found = false;
					rayfind_player = rayfind_player + 1;
				}
			}
			if (player_found || !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).isEmpty()) {
				if (entity instanceof AmalgamationEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_UndergroundDespawnTimer, 1200);
			}
			if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_UndergroundDespawnTimer) : 0) > 1) {
				if (entity instanceof AmalgamationEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_UndergroundDespawnTimer, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_UndergroundDespawnTimer) : 0) - 1));
			}
			if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_UndergroundDespawnTimer) : 0) == 1) {
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
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
				if (!entity.level.isClientSide())
					entity.discard();
			}
		}
	}
}
