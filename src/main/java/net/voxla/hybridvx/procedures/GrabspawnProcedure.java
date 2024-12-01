package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.init.HybridVxModEntities;
import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

import java.util.Comparator;

public class GrabspawnProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
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
				if (((Entity) world.getEntitiesOfClass(AmalgamationEntity.class, AABB.ofSize(new Vec3((entity.getX() + entity.getLookAngle().x * 1), (entity.getY() + 0), (entity.getZ() + entity.getLookAngle().z * 1)), 4, 4, 4), e -> true).stream()
						.sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
							}
						}.compareDistOf((entity.getX() + entity.getLookAngle().x * 1), (entity.getY() + 0), (entity.getZ() + entity.getLookAngle().z * 1))).findFirst().orElse(null)) instanceof AmalgamationEntity _datEntSetL)
					_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_IsGrabJumpscareThenDontKill, true);
			}
		}
	}
}
