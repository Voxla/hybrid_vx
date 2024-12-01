package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraft.world.entity.Entity;

public class AmalgamCanAttackProcProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof AmalgamationEntity _datEntL0 && _datEntL0.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == true) {
			return true;
		}
		return false;
	}
}
