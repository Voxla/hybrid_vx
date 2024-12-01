package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.entity.MidnightLurkerHallucinationEntity;

import net.minecraft.world.entity.Entity;

public class HalluChaseProcedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity instanceof MidnightLurkerHallucinationEntity _datEntI ? _datEntI.getEntityData().get(MidnightLurkerHallucinationEntity.DATA_RunChance) : 0) == 1) {
			return true;
		}
		return false;
	}
}
