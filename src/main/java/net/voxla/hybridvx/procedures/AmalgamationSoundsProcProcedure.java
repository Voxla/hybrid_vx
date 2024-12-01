package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class AmalgamationSoundsProcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if ((entity instanceof AmalgamationEntity _datEntL0 && _datEntL0.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == false
				&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_AmbientSoundTimer) : 0) == 0) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_AmbientSoundTimer, Mth.nextInt(RandomSource.create(), 200, 300));
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.ambient")), SoundSource.HOSTILE, (float) 1.3,
								(float) Mth.nextDouble(RandomSource.create(), 0.8, 1.1));
					} else {
						_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.ambient")), SoundSource.HOSTILE, (float) 1.3, (float) Mth.nextDouble(RandomSource.create(), 0.8, 1.1),
								false);
					}
				}
			}
		}
		if ((entity instanceof AmalgamationEntity _datEntL7 && _datEntL7.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == false
				&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_AmbientSoundTimer) : 0) > 0) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_AmbientSoundTimer, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_AmbientSoundTimer) : 0) - 1));
		}
		if ((entity instanceof AmalgamationEntity _datEntL11 && _datEntL11.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == false
				&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_BackingAmbientTimer) : 0) == 0) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_BackingAmbientTimer, Mth.nextInt(RandomSource.create(), 150, 160));
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.backing_ambient")), SoundSource.HOSTILE, (float) 0.8,
								(float) Mth.nextDouble(RandomSource.create(), 0.8, 1.1));
					} else {
						_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.backing_ambient")), SoundSource.HOSTILE, (float) 0.8,
								(float) Mth.nextDouble(RandomSource.create(), 0.8, 1.1), false);
					}
				}
			}
		}
		if ((entity instanceof AmalgamationEntity _datEntL18 && _datEntL18.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == false
				&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_BackingAmbientTimer) : 0) > 0) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_BackingAmbientTimer, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_BackingAmbientTimer) : 0) - 1));
		}
		if ((entity instanceof AmalgamationEntity _datEntL22 && _datEntL22.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == true
				&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ChaseScreamTimer) : 0) == 0) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_ChaseScreamTimer, Mth.nextInt(RandomSource.create(), 30, 35));
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.chase_scream")), SoundSource.HOSTILE, 3,
								(float) Mth.nextDouble(RandomSource.create(), 0.8, 1.1));
					} else {
						_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.chase_scream")), SoundSource.HOSTILE, 3, (float) Mth.nextDouble(RandomSource.create(), 0.8, 1.1),
								false);
					}
				}
			}
		}
		if ((entity instanceof AmalgamationEntity _datEntL29 && _datEntL29.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == true
				&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ChaseScreamTimer) : 0) > 0) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_ChaseScreamTimer, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ChaseScreamTimer) : 0) - 1));
		}
	}
}
