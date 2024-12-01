package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.entity.Entity;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

public class AmalgamSneakProcProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		if (!world.getBlockState(BlockPos.containing(x, y + 0, z)).canOcclude() && !world.getBlockState(BlockPos.containing(x, y + 1, z)).canOcclude()
				&& (world.getBlockState(BlockPos.containing(x, y + 2, z)).canOcclude() || (world.getBlockState(BlockPos.containing(x, y + 2, z))).is(BlockTags.create(new ResourceLocation("minecraft:leaves"))))) {
			if (entity.isShiftKeyDown() == false) {
				entity.setShiftKeyDown(true);
			}
			if (entity.isShiftKeyDown()) {
				if (entity.getDeltaMovement().x() != 0 || entity.getDeltaMovement().z() != 0) {
					if (entity instanceof AmalgamationEntity) {
						((AmalgamationEntity) entity).setAnimation("animation.amalgamation.runfastersneak");
					}
				} else if (entity.getDeltaMovement().x() == 0 && entity.getDeltaMovement().z() == 0) {
					if ((((AmalgamationEntity) entity).animationprocedure).equals("animation.amalgamation.runfastersneak")) {
						if (entity instanceof AmalgamationEntity) {
							((AmalgamationEntity) entity).setAnimation("empty");
						}
					}
				}
			}
		} else {
			if (entity.isShiftKeyDown() == true) {
				entity.setShiftKeyDown(false);
			}
			if ((((AmalgamationEntity) entity).animationprocedure).equals("animation.amalgamation.runfastersneak")) {
				if (entity instanceof AmalgamationEntity) {
					((AmalgamationEntity) entity).setAnimation("empty");
				}
			}
		}
	}
}
