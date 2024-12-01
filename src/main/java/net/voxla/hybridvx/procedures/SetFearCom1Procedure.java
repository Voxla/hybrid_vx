package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.network.HybridVxModVariables;

import net.minecraft.world.entity.Entity;

public class SetFearCom1Procedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		{
			double _setval = 3000;
			entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.FearMeter = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
