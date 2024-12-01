package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.init.HybridVxModParticleTypes;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;

public class AmalgamationDeathTimeIsReachedProcedure {
	public static void execute(LevelAccessor world, Entity entity) {
		if (entity == null)
			return;
		if (world instanceof ServerLevel _level)
			_level.sendParticles(ParticleTypes.POOF, (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 25, 0.4, 1, 0.4, 0.01);
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.FEAR_SMOKE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 10, 0.4, 1, 0.4, 0.01);
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_DOT_PARTICLE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
		if (world instanceof ServerLevel _level)
			_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.FLESH_BLOOD_DRIP.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 70, 0.4, 1, 0.4, 0.01);
	}
}
