
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.voxla.hybridvx.init;

import net.voxla.hybridvx.client.renderer.VoidGatewayHallucinationRenderer;
import net.voxla.hybridvx.client.renderer.MidnightLurkerHallucinationRenderer;
import net.voxla.hybridvx.client.renderer.MidnightLurkerFootstepHallucinationRenderer;
import net.voxla.hybridvx.client.renderer.AmalgamationRenderer;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HybridVxModEntityRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(HybridVxModEntities.HYBRID.get(), AmalgamationRenderer::new);
		event.registerEntityRenderer(HybridVxModEntities.MIDNIGHT_LURKER_HALLUCINATION.get(), MidnightLurkerHallucinationRenderer::new);
		event.registerEntityRenderer(HybridVxModEntities.VOID_GATEWAY_HALLUCINATION.get(), VoidGatewayHallucinationRenderer::new);
		event.registerEntityRenderer(HybridVxModEntities.MIDNIGHT_LURKER_FOOTSTEP_HALLUCINATION.get(), MidnightLurkerFootstepHallucinationRenderer::new);
	}
}
