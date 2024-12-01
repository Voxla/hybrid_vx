package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.Direction;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class PlayerCanSeeAmalgamProcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double raytrace_distance = 0;
		boolean entity_found = false;
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(320 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if (entityiterator instanceof Player) {
					raytrace_distance = 0;
					for (int index0 = 0; index0 < 160; index0++) {
						if (!world.getEntitiesOfClass(AmalgamationEntity.class,
								AABB.ofSize(new Vec3(
										(entityiterator.level.clip(new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER,
												ClipContext.Fluid.NONE, entityiterator)).getBlockPos().getX()),
										(entityiterator.level.clip(new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER,
												ClipContext.Fluid.NONE, entityiterator)).getBlockPos().getY()),
										(entityiterator.level.clip(new ClipContext(entityiterator.getEyePosition(1f), entityiterator.getEyePosition(1f).add(entityiterator.getViewVector(1f).scale(raytrace_distance)), ClipContext.Block.COLLIDER,
												ClipContext.Fluid.NONE, entityiterator)).getBlockPos().getZ())),
										1, 1, 1),
								e -> true).isEmpty()) {
							entity_found = true;
						} else {
							entity_found = false;
							raytrace_distance = raytrace_distance + 1;
						}
					}
					if (entity_found || !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 10, 10, 10), e -> true).isEmpty()) {
						if ((entity instanceof AmalgamationEntity _datEntL6 && _datEntL6.getEntityData().get(AmalgamationEntity.DATA_IsBeingLookedAt)) == false) {
							if (entity instanceof AmalgamationEntity _datEntSetL)
								_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_IsBeingLookedAt, true);
						}
					} else {
						if ((entity instanceof AmalgamationEntity _datEntL8 && _datEntL8.getEntityData().get(AmalgamationEntity.DATA_IsBeingLookedAt)) == true) {
							if (entity instanceof AmalgamationEntity _datEntSetL)
								_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_IsBeingLookedAt, false);
						}
					}
				}
			}
		}
		{
			final Vec3 _center = new Vec3(x, y, z);
			List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(150 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center))).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if (entityiterator instanceof Player) {
					if ((entity.getDirection()) == Direction.SOUTH && (entityiterator.getDirection()) == Direction.NORTH || (entity.getDirection()) == Direction.NORTH && (entityiterator.getDirection()) == Direction.SOUTH
							|| (entity.getDirection()) == Direction.EAST && (entityiterator.getDirection()) == Direction.WEST || (entity.getDirection()) == Direction.WEST && (entityiterator.getDirection()) == Direction.EAST
							|| !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 6, 6, 6), e -> true).isEmpty()) {
						if ((entity instanceof AmalgamationEntity _datEntL29 && _datEntL29.getEntityData().get(AmalgamationEntity.DATA_IsBeingSeen)) == false) {
							if (entity instanceof AmalgamationEntity _datEntSetL)
								_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_IsBeingSeen, true);
						}
					} else {
						if ((entity instanceof AmalgamationEntity _datEntL31 && _datEntL31.getEntityData().get(AmalgamationEntity.DATA_IsBeingSeen)) == true) {
							if (entity instanceof AmalgamationEntity _datEntSetL)
								_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_IsBeingSeen, false);
						}
					}
				}
			}
		}
		if (!(!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 200, 200, 200), e -> true).isEmpty())) {
			if ((entity instanceof AmalgamationEntity _datEntL35 && _datEntL35.getEntityData().get(AmalgamationEntity.DATA_IsBeingLookedAt)) == true) {
				if (entity instanceof AmalgamationEntity _datEntSetL)
					_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_IsBeingLookedAt, false);
			}
		}
		if (!(!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 150, 150, 150), e -> true).isEmpty())) {
			if ((entity instanceof AmalgamationEntity _datEntL38 && _datEntL38.getEntityData().get(AmalgamationEntity.DATA_IsBeingSeen)) == true) {
				if (entity instanceof AmalgamationEntity _datEntSetL)
					_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_IsBeingSeen, false);
			}
		}
	}
}
