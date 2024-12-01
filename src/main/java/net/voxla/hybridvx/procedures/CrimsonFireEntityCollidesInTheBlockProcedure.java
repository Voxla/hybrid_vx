package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;

public class CrimsonFireEntityCollidesInTheBlockProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity && !(entity instanceof AmalgamationEntity)) {
			entity.setTicksFrozen(200);
		}
	}
}
