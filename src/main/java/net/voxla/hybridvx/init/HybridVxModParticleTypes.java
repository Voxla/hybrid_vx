
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.voxla.hybridvx.init;

import net.voxla.hybridvx.HybridVxMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleType;

public class HybridVxModParticleTypes {
	public static final DeferredRegister<ParticleType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, HybridVxMod.MODID);
	public static final RegistryObject<SimpleParticleType> FEAR_SMOKE = REGISTRY.register("fear_smoke", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> FEAR_GLINT = REGISTRY.register("fear_glint", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> LURKER_EYE_PARTICLE = REGISTRY.register("lurker_eye_particle", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> VOID_DOT_PARTICLE = REGISTRY.register("void_dot_particle", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> VOID_GATEWAY_PARTICLE = REGISTRY.register("void_gateway_particle", () -> new SimpleParticleType(true));
	public static final RegistryObject<SimpleParticleType> FLESH_BLOOD_DRIP = REGISTRY.register("flesh_blood_drip", () -> new SimpleParticleType(true));
}
