package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.init.HybridVxModParticleTypes;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
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

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class CrimsonCampfireOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, BlockState blockstate) {
		if ((blockstate.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp1 && blockstate.getValue(_getbp1)) == false
				&& (blockstate.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _getip3 ? blockstate.getValue(_getip3) : -1) == 0) {
			if (Math.random() > 0.97) {
				if (!world.isClientSide()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.campfire.crackle")), SoundSource.BLOCKS, (float) 0.5, 1);
						} else {
							_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.campfire.crackle")), SoundSource.BLOCKS, (float) 0.5, 1, false);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:crimson_fire.ambient")), SoundSource.BLOCKS, (float) 0.2, 1);
						} else {
							_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:crimson_fire.ambient")), SoundSource.BLOCKS, (float) 0.2, 1, false);
						}
					}
				}
			}
			if (Math.random() > 0.93) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.FEAR_GLINT.get()), (x + 0.5 + Mth.nextDouble(RandomSource.create(), -0.4, 0.4)), (y + 0.8 + Mth.nextDouble(RandomSource.create(), -0.2, 0.2)),
							(z + 0.5 + Mth.nextDouble(RandomSource.create(), -0.4, 0.4)), 0, 0, 0.1, 0, 0.1);
			}
			if (Math.random() > 0.8) {
				if ((world.getBlockState(BlockPos.containing(x, y - 1, z))).getBlock() == Blocks.HAY_BLOCK) {
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, (x + 0.5 + Mth.nextDouble(RandomSource.create(), -0.2, 0.2)), (y + 0.8 + Mth.nextDouble(RandomSource.create(), -0.2, 0.2)),
								(z + 0.5 + Mth.nextDouble(RandomSource.create(), -0.2, 0.2)), 0, 0, 0.1, 0, 0.7);
				} else {
					if (world instanceof ServerLevel _level)
						_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (x + 0.5 + Mth.nextDouble(RandomSource.create(), -0.2, 0.2)), (y + 0.8 + Mth.nextDouble(RandomSource.create(), -0.2, 0.2)),
								(z + 0.5 + Mth.nextDouble(RandomSource.create(), -0.2, 0.2)), 0, 0, 0.1, 0, 0.7);
				}
			}
		}
		if ((blockstate.getBlock().getStateDefinition().getProperty("waterlogged") instanceof BooleanProperty _getbp22 && blockstate.getValue(_getbp22)) == true
				&& (blockstate.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _getip24 ? blockstate.getValue(_getip24) : -1) == 0) {
			{
				int _value = 1;
				BlockPos _pos = BlockPos.containing(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				if (_bs.getBlock().getStateDefinition().getProperty("blockstate") instanceof IntegerProperty _integerProp && _integerProp.getPossibleValues().contains(_value))
					world.setBlock(_pos, _bs.setValue(_integerProp, _value), 3);
			}
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:crimson_fire.extinguish")), SoundSource.BLOCKS, 1, (float) 0.7);
					} else {
						_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:crimson_fire.extinguish")), SoundSource.BLOCKS, 1, (float) 0.7, false);
					}
				}
			}
		}
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(20 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if (entityiterator instanceof Player) {
					if (Math.random() > 0.97) {
						if (world instanceof ServerLevel _level)
							_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.FEAR_GLINT.get()), (entityiterator.getX()), (entityiterator.getY() + 0.9), (entityiterator.getZ()), 5, 0.3, 0.7, 0.3, 0.01);
					}
					if (!(entityiterator instanceof LivingEntity _livEnt33 && _livEnt33.hasEffect(MobEffects.REGENERATION))) {
						if (entityiterator instanceof LivingEntity _entity && !_entity.level.isClientSide())
							_entity.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 310, 1, false, false));
					}
				}
			}
		}
	}
}
