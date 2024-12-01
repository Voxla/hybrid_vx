package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.network.HybridVxModVariables;
import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;

public class PanicAttackBarProcProcedure {
	public static boolean execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return false;
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).PanicAttackTimer > 0
				|| !world.getEntitiesOfClass(AmalgamationEntity.class, AABB.ofSize(new Vec3(x, y, z), 30, 30, 30), e -> true).isEmpty()) {
			return true;
		}
		return false;
	}
}
