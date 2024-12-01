package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.commands.arguments.EntityAnchorArgument;

import java.util.stream.Collectors;
import java.util.List;
import java.util.Comparator;

public class AmalgamationClimbProcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (world.getBlockState(BlockPos.containing(x + 1, y + 0, z)).canOcclude() && world.getBlockState(BlockPos.containing(x + 1, y + 1, z)).canOcclude() && world.getBlockState(BlockPos.containing(x + 1, y + 2, z)).canOcclude()
				|| world.getBlockState(BlockPos.containing(x - 1, y + 0, z)).canOcclude() && world.getBlockState(BlockPos.containing(x - 1, y + 1, z)).canOcclude() && world.getBlockState(BlockPos.containing(x - 1, y + 2, z)).canOcclude()
				|| world.getBlockState(BlockPos.containing(x, y + 0, z + 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 1, z + 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 2, z + 1)).canOcclude()
				|| world.getBlockState(BlockPos.containing(x, y + 0, z - 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 1, z - 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 2, z - 1)).canOcclude()) {
			if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(100 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
							.collect(Collectors.toList());
					for (Entity entityiterator : _entfound) {
						if (entityiterator == (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null)) {
							if (entityiterator.getY() > entity.getY()) {
								if (entity instanceof AmalgamationEntity _datEntSetL)
									_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_Climbing, true);
							}
						}
					}
				}
			}
		} else if (!world.getBlockState(BlockPos.containing(x + 1, y + 0, z)).canOcclude() && (entity.getDirection()) == Direction.EAST) {
			if (entity instanceof AmalgamationEntity _datEntSetL)
				_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_Climbing, false);
		} else if (!world.getBlockState(BlockPos.containing(x - 1, y + 0, z)).canOcclude() && (entity.getDirection()) == Direction.WEST) {
			if (entity instanceof AmalgamationEntity _datEntSetL)
				_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_Climbing, false);
		} else if (!world.getBlockState(BlockPos.containing(x, y + 0, z + 1)).canOcclude() && (entity.getDirection()) == Direction.SOUTH) {
			if (entity instanceof AmalgamationEntity _datEntSetL)
				_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_Climbing, false);
		} else if (!world.getBlockState(BlockPos.containing(x, y + 0, z - 1)).canOcclude() && (entity.getDirection()) == Direction.NORTH) {
			if (entity instanceof AmalgamationEntity _datEntSetL)
				_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_Climbing, false);
		}
		if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
			{
				final Vec3 _center = new Vec3(x, y, z);
				List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(100 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
						.collect(Collectors.toList());
				for (Entity entityiterator : _entfound) {
					if (entityiterator == (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null)) {
						if (entityiterator.getY() < entity.getY()) {
							if (entity instanceof AmalgamationEntity _datEntSetL)
								_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_Climbing, false);
						}
					}
				}
			}
		}
		if ((entity instanceof AmalgamationEntity _datEntL44 && _datEntL44.getEntityData().get(AmalgamationEntity.DATA_Climbing)) == true) {
			if (!((entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null) == null)) {
				{
					final Vec3 _center = new Vec3(x, y, z);
					List<Entity> _entfound = world.getEntitiesOfClass(Entity.class, new AABB(_center, _center).inflate(100 / 2d), e -> true).stream().sorted(Comparator.comparingDouble(_entcnd -> _entcnd.distanceToSqr(_center)))
							.collect(Collectors.toList());
					for (Entity entityiterator : _entfound) {
						if (entity.getDeltaMovement().y() < 0.1 && entityiterator == (entity instanceof Mob _mobEnt ? (Entity) _mobEnt.getTarget() : null)) {
							entity.lookAt(EntityAnchorArgument.Anchor.EYES, new Vec3((entityiterator.getX()), (entityiterator.getY() + 1.7), (entityiterator.getZ())));
						}
					}
				}
			}
			if (world.getBlockState(BlockPos.containing(x + 1, y + 0, z)).canOcclude() && (!world.getBlockState(BlockPos.containing(x, y + 2, z)).canOcclude() || !world.getBlockState(BlockPos.containing(x, y + 3, z)).canOcclude())
					&& (entity.getDirection()) == Direction.EAST) {
				entity.setDeltaMovement(new Vec3(0.2, 0.2, (entity.getDeltaMovement().z() / 4)));
				entity.fallDistance = 0;
			} else if (world.getBlockState(BlockPos.containing(x - 1, y + 0, z)).canOcclude() && (!world.getBlockState(BlockPos.containing(x, y + 2, z)).canOcclude() || !world.getBlockState(BlockPos.containing(x, y + 3, z)).canOcclude())
					&& (entity.getDirection()) == Direction.WEST) {
				entity.setDeltaMovement(new Vec3((-0.2), 0.2, (entity.getDeltaMovement().z() / 4)));
				entity.fallDistance = 0;
			} else if (world.getBlockState(BlockPos.containing(x, y + 0, z + 1)).canOcclude() && (!world.getBlockState(BlockPos.containing(x, y + 2, z)).canOcclude() || !world.getBlockState(BlockPos.containing(x, y + 3, z)).canOcclude())
					&& (entity.getDirection()) == Direction.SOUTH) {
				entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() / 4), 0.2, 0.2));
				entity.fallDistance = 0;
			} else if (world.getBlockState(BlockPos.containing(x, y + 0, z - 1)).canOcclude() && (!world.getBlockState(BlockPos.containing(x, y + 2, z)).canOcclude() || !world.getBlockState(BlockPos.containing(x, y + 3, z)).canOcclude())
					&& (entity.getDirection()) == Direction.NORTH) {
				entity.setDeltaMovement(new Vec3((entity.getDeltaMovement().x() / 4), 0.2, (-0.2)));
				entity.fallDistance = 0;
			}
		}
		if (entity instanceof AmalgamationEntity) {
			if (world.getBlockState(BlockPos.containing(x + 1, y + 0, z)).canOcclude() && world.getBlockState(BlockPos.containing(x + 1, y + 1, z)).canOcclude() && world.getBlockState(BlockPos.containing(x + 1, y + 2, z)).canOcclude()
					&& !(!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 5, 5, 5), e -> true).isEmpty()) && (entity.getDirection()) == Direction.EAST) {
				if (entity.getDeltaMovement().y() == 0.2) {
					if (entity instanceof AmalgamationEntity) {
						((AmalgamationEntity) entity).setAnimation("animation.amalgamation.climb");
					}
					{
						Entity _ent = entity;
						_ent.setYRot(-90);
						_ent.setXRot(entity.getXRot());
						_ent.setYBodyRot(_ent.getYRot());
						_ent.setYHeadRot(_ent.getYRot());
						_ent.yRotO = _ent.getYRot();
						_ent.xRotO = _ent.getXRot();
						if (_ent instanceof LivingEntity _entity) {
							_entity.yBodyRotO = _entity.getYRot();
							_entity.yHeadRotO = _entity.getYRot();
						}
					}
				}
			} else if (world.getBlockState(BlockPos.containing(x - 1, y + 0, z)).canOcclude() && world.getBlockState(BlockPos.containing(x - 1, y + 1, z)).canOcclude() && world.getBlockState(BlockPos.containing(x - 1, y + 2, z)).canOcclude()
					&& !(!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 5, 5, 5), e -> true).isEmpty()) && (entity.getDirection()) == Direction.WEST) {
				if (entity.getDeltaMovement().y() == 0.2) {
					if (entity instanceof AmalgamationEntity) {
						((AmalgamationEntity) entity).setAnimation("animation.amalgamation.climb");
					}
					{
						Entity _ent = entity;
						_ent.setYRot(90);
						_ent.setXRot(entity.getXRot());
						_ent.setYBodyRot(_ent.getYRot());
						_ent.setYHeadRot(_ent.getYRot());
						_ent.yRotO = _ent.getYRot();
						_ent.xRotO = _ent.getXRot();
						if (_ent instanceof LivingEntity _entity) {
							_entity.yBodyRotO = _entity.getYRot();
							_entity.yHeadRotO = _entity.getYRot();
						}
					}
				}
			} else if (world.getBlockState(BlockPos.containing(x, y + 0, z + 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 1, z + 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 2, z + 1)).canOcclude()
					&& !(!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 5, 5, 5), e -> true).isEmpty()) && (entity.getDirection()) == Direction.SOUTH) {
				if (entity.getDeltaMovement().y() == 0.2) {
					if (entity instanceof AmalgamationEntity) {
						((AmalgamationEntity) entity).setAnimation("animation.amalgamation.climb");
					}
					{
						Entity _ent = entity;
						_ent.setYRot(0);
						_ent.setXRot(entity.getXRot());
						_ent.setYBodyRot(_ent.getYRot());
						_ent.setYHeadRot(_ent.getYRot());
						_ent.yRotO = _ent.getYRot();
						_ent.xRotO = _ent.getXRot();
						if (_ent instanceof LivingEntity _entity) {
							_entity.yBodyRotO = _entity.getYRot();
							_entity.yHeadRotO = _entity.getYRot();
						}
					}
				}
			} else if (world.getBlockState(BlockPos.containing(x, y + 0, z - 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 1, z - 1)).canOcclude() && world.getBlockState(BlockPos.containing(x, y + 2, z - 1)).canOcclude()
					&& !(!world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 5, 5, 5), e -> true).isEmpty()) && (entity.getDirection()) == Direction.NORTH) {
				if (entity.getDeltaMovement().y() == 0.2) {
					if (entity instanceof AmalgamationEntity) {
						((AmalgamationEntity) entity).setAnimation("animation.amalgamation.climb");
					}
					{
						Entity _ent = entity;
						_ent.setYRot(180);
						_ent.setXRot(entity.getXRot());
						_ent.setYBodyRot(_ent.getYRot());
						_ent.setYHeadRot(_ent.getYRot());
						_ent.yRotO = _ent.getYRot();
						_ent.xRotO = _ent.getXRot();
						if (_ent instanceof LivingEntity _entity) {
							_entity.yBodyRotO = _entity.getYRot();
							_entity.yHeadRotO = _entity.getYRot();
						}
					}
				}
			} else {
				if ((((AmalgamationEntity) entity).animationprocedure).equals("animation.amalgamation.climb")) {
					if (entity instanceof AmalgamationEntity) {
						((AmalgamationEntity) entity).setAnimation("empty");
					}
				}
			}
		}
	}
}
