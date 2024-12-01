package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.network.HybridVxModVariables;

import net.minecraft.world.entity.Entity;

public class PanicattackcommandProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = 301;
			entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.PanicAttackTimer = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
