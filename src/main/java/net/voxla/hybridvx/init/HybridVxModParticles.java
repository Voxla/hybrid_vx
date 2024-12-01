
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.voxla.hybridvx.init;

import net.voxla.hybridvx.client.particle.VoidGatewayParticleParticle;
import net.voxla.hybridvx.client.particle.VoidDotParticleParticle;
import net.voxla.hybridvx.client.particle.LurkerEyeParticleParticle;
import net.voxla.hybridvx.client.particle.FleshBloodDripParticle;
import net.voxla.hybridvx.client.particle.FearSmokeParticle;
import net.voxla.hybridvx.client.particle.FearGlintParticle;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.api.distmarker.Dist;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class HybridVxModParticles {
	@SubscribeEvent
	public static void registerParticles(RegisterParticleProvidersEvent event) {
		event.registerSpriteSet(HybridVxModParticleTypes.FEAR_SMOKE.get(), FearSmokeParticle::provider);
		event.registerSpriteSet(HybridVxModParticleTypes.FEAR_GLINT.get(), FearGlintParticle::provider);
		event.registerSpriteSet(HybridVxModParticleTypes.LURKER_EYE_PARTICLE.get(), LurkerEyeParticleParticle::provider);
		event.registerSpriteSet(HybridVxModParticleTypes.VOID_DOT_PARTICLE.get(), VoidDotParticleParticle::provider);
		event.registerSpriteSet(HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get(), VoidGatewayParticleParticle::provider);
		event.registerSpriteSet(HybridVxModParticleTypes.FLESH_BLOOD_DRIP.get(), FleshBloodDripParticle::provider);
	}
}
