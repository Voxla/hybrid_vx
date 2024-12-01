package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import java.util.Comparator;

public class AmalgamationDistantSoundProcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double Px = 0;
		double Pz = 0;
		if (!(!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 60, 60, 60), e -> true).isEmpty()) && (entity instanceof AmalgamationEntity _datEntL1 && _datEntL1.getEntityData().get(AmalgamationEntity.DATA_IsBeingSeen)) == false
				&& world.canSeeSkyFromBelowWater(BlockPos.containing(x, y, z))) {
			if ((entity instanceof AmalgamationEntity _datEntL3 && _datEntL3.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == false
					&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ScreamTimer) : 0) == 0) {
				if (entity instanceof AmalgamationEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_ScreamTimer, Mth.nextInt(RandomSource.create(), 300, 4800));
			}
			if ((entity instanceof AmalgamationEntity _datEntL7 && _datEntL7.getEntityData().get(AmalgamationEntity.DATA_Chasing)) == false
					&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ScreamTimer) : 0) > 0) {
				if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ScreamTimer) : 0) == 1) {
					if (Math.random() > 0.8) {
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y + 1.5, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.scream")), SoundSource.HOSTILE, 6, 1);
								} else {
									_level.playLocalSound(x, (y + 1.5), z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.scream")), SoundSource.HOSTILE, 6, 1, false);
								}
							}
						}
					} else if (Math.random() <= 0.8 && Math.random() > 0.6) {
						if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).isEmpty()) {
							Px = ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).getX() + Mth.nextInt(RandomSource.create(), -30, 30);
							Pz = ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
							if (!world.isClientSide()) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.ground")), SoundSource.AMBIENT, 4, 1);
									} else {
										_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz,
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.ground")), SoundSource.AMBIENT, 4, 1, false);
									}
								}
							}
						}
					} else if (Math.random() <= 0.6 && Math.random() > 0.4) {
						if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).isEmpty()) {
							Px = ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).getX() + Mth.nextInt(RandomSource.create(), -30, 30);
							Pz = ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
							if (!world.isClientSide()) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.music")), SoundSource.AMBIENT, 4, 1);
									} else {
										_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz,
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.music")), SoundSource.AMBIENT, 4, 1, false);
									}
								}
							}
						}
					} else if (Math.random() <= 0.4 && Math.random() > 0.2) {
						if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).isEmpty()) {
							Px = ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).getX() + Mth.nextInt(RandomSource.create(), -30, 30);
							Pz = ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
							if (!world.isClientSide()) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.rumble")), SoundSource.AMBIENT, 4, 1);
									} else {
										_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz,
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.rumble")), SoundSource.AMBIENT, 4, 1, false);
									}
								}
							}
						}
					} else if (Math.random() <= 0.2) {
						if (!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).isEmpty()) {
							Px = ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).getX() + Mth.nextInt(RandomSource.create(), -30, 30);
							Pz = ((Entity) world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).stream().sorted(new Object() {
								Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
									return Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_x, _y, _z));
								}
							}.compareDistOf(x, y, z)).findFirst().orElse(null)).getZ() + Mth.nextInt(RandomSource.create(), -30, 30);
							if (!world.isClientSide()) {
								if (world instanceof Level _level) {
									if (!_level.isClientSide()) {
										_level.playSound(null, BlockPos.containing(Px, world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5, Pz),
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.wind")), SoundSource.AMBIENT, 4, 1);
									} else {
										_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz,
												ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:ambient.distant.wind")), SoundSource.AMBIENT, 4, 1, false);
									}
								}
							}
						}
					}
				}
				if (entity instanceof AmalgamationEntity _datEntSetI)
					_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_ScreamTimer, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ScreamTimer) : 0) - 1));
			}
		}
	}
}
