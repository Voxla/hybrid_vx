package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.init.HybridVxModParticleTypes;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.client.player.LocalPlayer;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class CrimsonFireOnTickUpdateProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if (Math.random() > 0.99) {
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:crimson_fire.ambient")), SoundSource.BLOCKS, 1, 1);
					} else {
						_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:crimson_fire.ambient")), SoundSource.BLOCKS, 1, 1, false);
					}
				}
			}
		}
		if (Math.random() > 0.87) {
			if (world instanceof ServerLevel _level)
				_level.sendParticles(ParticleTypes.LARGE_SMOKE, (x + 0.5), (y + 0.8 + Mth.nextDouble(RandomSource.create(), -0.2, 0.2)), (z + 0.5), 6, 0.3, 0.1, 0.3, 0.00001);
			if (Math.random() > 0.5) {
				if (world instanceof ServerLevel _level)
					_level.sendParticles((SimpleParticleType) (HybridVxModParticleTypes.FEAR_SMOKE.get()), (x + 0.5), (y + 0.8 + Mth.nextDouble(RandomSource.create(), -0.2, 0.2)), (z + 0.5), 3, 0.3, 0.1, 0.3, 0.00001);
			}
		}
		if ((new Object() {
			public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getBoolean(tag);
				return false;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "CantDisappear")) == false && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "DisappearTimer") < 400) {
			if (!world.isClientSide()) {
				BlockPos _bp = BlockPos.containing(x, y, z);
				BlockEntity _blockEntity = world.getBlockEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_blockEntity != null)
					_blockEntity.getPersistentData().putDouble("DisappearTimer", (new Object() {
						public double getValue(LevelAccessor world, BlockPos pos, String tag) {
							BlockEntity blockEntity = world.getBlockEntity(pos);
							if (blockEntity != null)
								return blockEntity.getPersistentData().getDouble(tag);
							return -1;
						}
					}.getValue(world, BlockPos.containing(x, y, z), "DisappearTimer") + 1));
				if (world instanceof Level _level)
					_level.sendBlockUpdated(_bp, _bs, _bs, 3);
			}
		}
		if ((new Object() {
			public boolean getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getBoolean(tag);
				return false;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "CantDisappear")) == false && new Object() {
			public double getValue(LevelAccessor world, BlockPos pos, String tag) {
				BlockEntity blockEntity = world.getBlockEntity(pos);
				if (blockEntity != null)
					return blockEntity.getPersistentData().getDouble(tag);
				return -1;
			}
		}.getValue(world, BlockPos.containing(x, y, z), "DisappearTimer") >= 400) {
			if (!world.isClientSide()) {
				if (world instanceof Level _level) {
					if (!_level.isClientSide()) {
						_level.playSound(null, BlockPos.containing(x + 0.5, y + 0.5, z + 0.5), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:crimson_fire.extinguish")), SoundSource.BLOCKS, (float) 0.5, (float) 0.7);
					} else {
						_level.playLocalSound((x + 0.5), (y + 0.5), (z + 0.5), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:crimson_fire.extinguish")), SoundSource.BLOCKS, (float) 0.5, (float) 0.7, false);
					}
				}
				world.setBlock(BlockPos.containing(x, y, z), Blocks.AIR.defaultBlockState(), 3);
			}
		}
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(10 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if (entityiterator instanceof Player) {
					if (!(new Object() {
						public boolean hasRecipe(Entity _ent, ResourceLocation recipe) {
							if (_ent instanceof ServerPlayer _player)
								return _player.getRecipeBook().contains(recipe);
							else if (_ent.level.isClientSide() && _ent instanceof LocalPlayer _player)
								return _player.getRecipeBook().contains(recipe);
							return false;
						}
					}.hasRecipe(entityiterator, new ResourceLocation("hybrid_vx:crimson_campfire_recipe")))) {
						if (entityiterator instanceof ServerPlayer _serverPlayer)
							_serverPlayer.awardRecipesByKey(new ResourceLocation[]{new ResourceLocation("hybrid_vx:crimson_campfire_recipe")});
					}
				}
			}
		}
	}
}
