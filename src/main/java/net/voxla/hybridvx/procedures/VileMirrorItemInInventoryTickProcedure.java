package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.init.HybridVxModParticleTypes;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

public class VileMirrorItemInInventoryTickProcedure {
	public static void execute(LevelAccessor world, Entity entity, ItemStack itemstack) {
		if (entity == null)
			return;
		if ((entity instanceof Player _plrCldRem1 ? _plrCldRem1.getCooldowns().getCooldownPercent(itemstack.getItem(), 0f) * 100 : 0) == 95) {
			if ((entity.level.dimension()) == Level.OVERWORLD) {
				if (!world.isClientSide()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY() + 1, entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.chorus_fruit.teleport")), SoundSource.PLAYERS, 1, 1);
						} else {
							_level.playLocalSound((entity.getX()), (entity.getY() + 1), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.chorus_fruit.teleport")), SoundSource.PLAYERS, 1, 1, false);
						}
					}
				}
			}
		}
		if ((entity instanceof Player _plrCldRem11 ? _plrCldRem11.getCooldowns().getCooldownPercent(itemstack.getItem(), 0f) * 100 : 0) == 92.5) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (entity.getX()), (entity.getY() + 1), (entity.getZ()), 10, 0.3, 0.6, 0.3, 0.01);
			if ((entity.level.dimension()) == Level.OVERWORLD) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (entity.getX()), (entity.getY() + 1), (entity.getZ()), 18, 0.3, 0.6, 0.3, 0.01);
				{
					Entity _ent = entity;
					_ent.teleportTo(
							((entity instanceof ServerPlayer _player && !_player.level.isClientSide())
									? ((_player.getRespawnDimension().equals(_player.level.dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getX() : _player.level.getLevelData().getXSpawn())
									: 0),
							((entity instanceof ServerPlayer _player && !_player.level.isClientSide())
									? ((_player.getRespawnDimension().equals(_player.level.dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getY() : _player.level.getLevelData().getYSpawn())
									: 0),
							((entity instanceof ServerPlayer _player && !_player.level.isClientSide())
									? ((_player.getRespawnDimension().equals(_player.level.dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getZ() : _player.level.getLevelData().getZSpawn())
									: 0));
					if (_ent instanceof ServerPlayer _serverPlayer)
						_serverPlayer.connection.teleport(
								((entity instanceof ServerPlayer _player && !_player.level.isClientSide())
										? ((_player.getRespawnDimension().equals(_player.level.dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getX() : _player.level.getLevelData().getXSpawn())
										: 0),
								((entity instanceof ServerPlayer _player && !_player.level.isClientSide())
										? ((_player.getRespawnDimension().equals(_player.level.dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getY() : _player.level.getLevelData().getYSpawn())
										: 0),
								((entity instanceof ServerPlayer _player && !_player.level.isClientSide())
										? ((_player.getRespawnDimension().equals(_player.level.dimension()) && _player.getRespawnPosition() != null) ? _player.getRespawnPosition().getZ() : _player.level.getLevelData().getZSpawn())
										: 0),
								_ent.getYRot(), _ent.getXRot());
				}
			} else {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.LARGE_SMOKE, (entity.getX()), (entity.getY() + 1), (entity.getZ()), 18, 0.3, 0.6, 0.3, 0.01);
				if (!world.isClientSide()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY() + 1, entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.glass.break")), SoundSource.PLAYERS, 1, (float) 0.8);
						} else {
							_level.playLocalSound((entity.getX()), (entity.getY() + 1), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.glass.break")), SoundSource.PLAYERS, 1, (float) 0.8, false);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY() + 1, entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.glass.break")), SoundSource.PLAYERS, 1, 1);
						} else {
							_level.playLocalSound((entity.getX()), (entity.getY() + 1), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.glass.break")), SoundSource.PLAYERS, 1, 1, false);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY() + 1, entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.glass.break")), SoundSource.PLAYERS, 1, (float) 1.2);
						} else {
							_level.playLocalSound((entity.getX()), (entity.getY() + 1), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.glass.break")), SoundSource.PLAYERS, 1, (float) 1.2, false);
						}
					}
				}
			}
		}
		if ((entity instanceof Player _plrCldRem45 ? _plrCldRem45.getCooldowns().getCooldownPercent(itemstack.getItem(), 0f) * 100 : 0) == 90) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.VOID_GATEWAY_PARTICLE.get()), (entity.getX()), (entity.getY() + 1), (entity.getZ()), 10, 0.3, 0.6, 0.3, 0.01);
			if ((entity.level.dimension()) == Level.OVERWORLD) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.CAMPFIRE_COSY_SMOKE, (entity.getX()), (entity.getY() + 1), (entity.getZ()), 18, 0.3, 0.6, 0.3, 0.01);
				if (!world.isClientSide()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(entity.getX(), entity.getY() + 1, entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.chorus_fruit.teleport")), SoundSource.PLAYERS, 1, 1);
						} else {
							_level.playLocalSound((entity.getX()), (entity.getY() + 1), (entity.getZ()), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("item.chorus_fruit.teleport")), SoundSource.PLAYERS, 1, 1, false);
						}
					}
				}
			} else {
				if (world instanceof ServerLevel _level)
					_level.sendParticles(ParticleTypes.LARGE_SMOKE, (entity.getX()), (entity.getY() + 1), (entity.getZ()), 18, 0.3, 0.6, 0.3, 0.01);
			}
		}
	}
}
