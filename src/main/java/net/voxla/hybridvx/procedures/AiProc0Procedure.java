package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.init.HybridVxModParticleTypes;
import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

public class AiProc0Procedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof LivingEntity _entity && !_entity.level.isClientSide())
			_entity.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 3, 200, false, false));
		if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_MFTFAIDisappearTimer) : 0) < 1800) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_MFTFAIDisappearTimer, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_MFTFAIDisappearTimer) : 0) + 1));
		}
		if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_MFTFAIDisappearTimer) : 0) >= 1800) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_MFTFAIDisappearTimer, 0);
			if (Math.random() > 0.9) {
				if (!world.isClientSide()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 2,
									(float) Mth.nextDouble(RandomSource.create(), 0.8, 1));
						} else {
							_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 2, (float) Mth.nextDouble(RandomSource.create(), 0.8, 1), false);
						}
					}
				}
				if (!entity.level.isClientSide())
					entity.discard();
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
			} else {
				if ((entity instanceof AmalgamationEntity _datEntL18 && _datEntL18.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == false) {
					if (entity instanceof AmalgamationEntity _datEntSetL)
						_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_Chasing, true);
				}
			}
		}
		if ((entity instanceof AmalgamationEntity _datEntL20 && _datEntL20.getEntityData().get(AmalgamationEntity.DATA_IsBeingLookedAt)) == true
				|| !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).isEmpty()) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_MFTFAIDisappearTimer, 0);
			if (Math.random() > 0.2) {
				if (!world.isClientSide()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 2,
									(float) Mth.nextDouble(RandomSource.create(), 0.8, 1));
						} else {
							_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.teleport")), SoundSource.HOSTILE, 2, (float) Mth.nextDouble(RandomSource.create(), 0.8, 1), false);
						}
					}
				}
				if (!entity.level.isClientSide())
					entity.discard();
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (entity.getX()), (entity.getY() + 1.5), (entity.getZ()), 50, 0.4, 1, 0.4, 0.01);
			} else {
				if ((entity instanceof AmalgamationEntity _datEntL35 && _datEntL35.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == false) {
					if (entity instanceof AmalgamationEntity _datEntSetL)
						_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_Chasing, true);
				}
			}
		}
	}
}
