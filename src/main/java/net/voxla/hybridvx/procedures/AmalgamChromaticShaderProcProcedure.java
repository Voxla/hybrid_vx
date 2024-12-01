package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.network.HybridVxModVariables;
import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

@Mod.EventBusSubscriber
public class AmalgamChromaticShaderProcProcedure {
	@SubscribeEvent
	public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.player.level, event.player.getX(), event.player.getY(), event.player.getZ(), event.player);
		}
	}

	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		execute(null, world, x, y, z, entity);
	}

	private static void execute(@Nullable Event event, LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		boolean Reset = false;
		if (world.isClientSide() && entity instanceof Player) {
			if (!((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).PanicAttackTimer > 0
					&& !world.getEntitiesOfClass(AmalgamationEntity.class, AABB.ofSize(new Vec3(x, y, z), 30, 30, 30), e -> true).isEmpty() && Reset != true)) {
				if (!(Minecraft.getInstance().gameRenderer.currentEffect() == null)) {
					Minecraft.getInstance().gameRenderer.shutdownEffect();
					Reset = false;
				}
			}
			if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).PanicAttackTimer > 0
					|| !world.getEntitiesOfClass(AmalgamationEntity.class, AABB.ofSize(new Vec3(x, y, z), 30, 30, 30), e -> true).isEmpty()) {
				if (!(Minecraft.getInstance().gameRenderer.currentEffect() != null)) {
					Minecraft.getInstance().gameRenderer.loadEffect(new ResourceLocation("minecraft:shaders/post/deconverge.json"));
					Reset = true;
				}
			}
		}
	}
}
