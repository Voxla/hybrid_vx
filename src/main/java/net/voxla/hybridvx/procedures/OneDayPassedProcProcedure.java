package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.network.HybridVxModVariables;

import net.minecraft.world.level.LevelAccessor;

public class OneDayPassedProcProcedure {
	public static void execute(LevelAccessor world) {
		if (HybridVxModVariables.WorldVariables.get(world).HasOneDayPassedA == false) {
			HybridVxModVariables.WorldVariables.get(world).HasOneDayPassedA = true;
			HybridVxModVariables.WorldVariables.get(world).syncData(world);
		}
	}
}
