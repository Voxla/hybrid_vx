package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.network.HybridVxModVariables;

import net.minecraft.world.entity.Entity;

public class JumpscareLurkerShow6Procedure {
	public static boolean execute(Entity entity) {
		if (entity == null)
			return false;
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).VoidGatewayJumpscareTimer == 14) {
			return true;
		}
		return false;
	}
}