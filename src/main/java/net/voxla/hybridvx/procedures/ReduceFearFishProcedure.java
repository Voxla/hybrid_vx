package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.network.HybridVxModVariables;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.entity.player.ItemFishedEvent;

import net.minecraft.world.entity.Entity;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class ReduceFearFishProcedure {
	@SubscribeEvent
	public static void onPlayerFishItem(ItemFishedEvent event) {
		execute(event, event.getEntity());
	}

	public static void execute(Entity entity) {
		execute(null, entity);
	}

	private static void execute(@Nullable Event event, Entity entity) {
		if (entity == null)
			return;
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter > 200) {
			{
				double _setval = (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter - 200;
				entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.FearMeter = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
