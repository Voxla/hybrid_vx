package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.world.inventory.AmalgamConfigGuiMenu;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;

public class AmalgamConfigCancelButtonProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof Player _plr0 && _plr0.containerMenu instanceof AmalgamConfigGuiMenu) {
			if (entity instanceof Player _player)
				_player.closeContainer();
		}
	}
}
