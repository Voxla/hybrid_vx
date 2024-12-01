package net.voxla.hybridvx.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.tags.BlockTags;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class AmalgamBreakOpenDoorProcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z) {
		if ((world.getBlockState(BlockPos.containing(x + 1, y, z))).is(BlockTags.create(new ResourceLocation("minecraft:doors"))) && !((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock() == Blocks.IRON_DOOR)) {
			if (((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp5 && (world.getBlockState(BlockPos.containing(x + 1, y, z))).getValue(_getbp5)) == false) {
				if (!world.isClientSide()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.attack_wooden_door")), SoundSource.HOSTILE, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.attack_wooden_door")), SoundSource.HOSTILE, 1, 1, false);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.break_wooden_door")), SoundSource.HOSTILE, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.break_wooden_door")), SoundSource.HOSTILE, 1, 1, false);
						}
					}
					if (((world.getBlockState(BlockPos.containing(x + 1, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp10
							&& (world.getBlockState(BlockPos.containing(x + 1, y, z))).getValue(_getbp10)) == false) {
						{
							BlockPos _pos = BlockPos.containing(x + 1, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _booleanProp)
								world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
						}
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x + 1, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wooden_door.open")), SoundSource.BLOCKS, 1, 1);
							} else {
								_level.playLocalSound((x + 1), y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wooden_door.open")), SoundSource.BLOCKS, 1, 1, false);
							}
						}
					}
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x - 1, y, z))).is(BlockTags.create(new ResourceLocation("minecraft:doors"))) && !((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock() == Blocks.IRON_DOOR)) {
			if (((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp18
					&& (world.getBlockState(BlockPos.containing(x - 1, y, z))).getValue(_getbp18)) == false) {
				if (!world.isClientSide()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.attack_wooden_door")), SoundSource.HOSTILE, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.attack_wooden_door")), SoundSource.HOSTILE, 1, 1, false);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.break_wooden_door")), SoundSource.HOSTILE, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.break_wooden_door")), SoundSource.HOSTILE, 1, 1, false);
						}
					}
					if (((world.getBlockState(BlockPos.containing(x - 1, y, z))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp23
							&& (world.getBlockState(BlockPos.containing(x - 1, y, z))).getValue(_getbp23)) == false) {
						{
							BlockPos _pos = BlockPos.containing(x - 1, y, z);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _booleanProp)
								world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
						}
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x - 1, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wooden_door.open")), SoundSource.BLOCKS, 1, 1);
							} else {
								_level.playLocalSound((x - 1), y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wooden_door.open")), SoundSource.BLOCKS, 1, 1, false);
							}
						}
					}
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y, z + 1))).is(BlockTags.create(new ResourceLocation("minecraft:doors"))) && !((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock() == Blocks.IRON_DOOR)) {
			if (((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp31
					&& (world.getBlockState(BlockPos.containing(x, y, z + 1))).getValue(_getbp31)) == false) {
				if (!world.isClientSide()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.attack_wooden_door")), SoundSource.HOSTILE, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.attack_wooden_door")), SoundSource.HOSTILE, 1, 1, false);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.break_wooden_door")), SoundSource.HOSTILE, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.break_wooden_door")), SoundSource.HOSTILE, 1, 1, false);
						}
					}
					if (((world.getBlockState(BlockPos.containing(x, y, z + 1))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp36
							&& (world.getBlockState(BlockPos.containing(x, y, z + 1))).getValue(_getbp36)) == false) {
						{
							BlockPos _pos = BlockPos.containing(x, y, z + 1);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _booleanProp)
								world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
						}
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z + 1), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wooden_door.open")), SoundSource.BLOCKS, 1, 1);
							} else {
								_level.playLocalSound(x, y, (z + 1), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wooden_door.open")), SoundSource.BLOCKS, 1, 1, false);
							}
						}
					}
				}
			}
		} else if ((world.getBlockState(BlockPos.containing(x, y, z - 1))).is(BlockTags.create(new ResourceLocation("minecraft:doors"))) && !((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock() == Blocks.IRON_DOOR)) {
			if (((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp44
					&& (world.getBlockState(BlockPos.containing(x, y, z - 1))).getValue(_getbp44)) == false) {
				if (!world.isClientSide()) {
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.attack_wooden_door")), SoundSource.HOSTILE, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.attack_wooden_door")), SoundSource.HOSTILE, 1, 1, false);
						}
					}
					if (world instanceof Level _level) {
						if (!_level.isClientSide()) {
							_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.break_wooden_door")), SoundSource.NEUTRAL, 1, 1);
						} else {
							_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("entity.zombie.break_wooden_door")), SoundSource.NEUTRAL, 1, 1, false);
						}
					}
					if (((world.getBlockState(BlockPos.containing(x, y, z - 1))).getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _getbp49
							&& (world.getBlockState(BlockPos.containing(x, y, z - 1))).getValue(_getbp49)) == false) {
						{
							BlockPos _pos = BlockPos.containing(x, y, z - 1);
							BlockState _bs = world.getBlockState(_pos);
							if (_bs.getBlock().getStateDefinition().getProperty("open") instanceof BooleanProperty _booleanProp)
								world.setBlock(_pos, _bs.setValue(_booleanProp, true), 3);
						}
						if (world instanceof Level _level) {
							if (!_level.isClientSide()) {
								_level.playSound(null, BlockPos.containing(x, y, z - 1), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wooden_door.open")), SoundSource.BLOCKS, 1, 1);
							} else {
								_level.playLocalSound(x, y, (z - 1), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.wooden_door.open")), SoundSource.BLOCKS, 1, 1, false);
							}
						}
					}
				}
			}
		}
	}
}
