package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.entity.MidnightLurkerHallucinationEntity;

import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;

public class MidnightLurkerHallucinationOnInitialEntitySpawnProcedure {
	public static void execute(Entity entity) {
		if (entity == null)
			return;
		if (entity instanceof MidnightLurkerHallucinationEntity _datEntSetI)
			_datEntSetI.getEntityData().set(MidnightLurkerHallucinationEntity.DATA_NaturalDisappearTimer, Mth.nextInt(RandomSource.create(), 600, 1700));
		if (entity instanceof MidnightLurkerHallucinationEntity _datEntSetI)
			_datEntSetI.getEntityData().set(MidnightLurkerHallucinationEntity.DATA_AnimationDeterminer, Mth.nextInt(RandomSource.create(), 0, 3));
		if (entity instanceof MidnightLurkerHallucinationEntity _datEntSetI)
			_datEntSetI.getEntityData().set(MidnightLurkerHallucinationEntity.DATA_RunChance, Mth.nextInt(RandomSource.create(), 0, 3));
		if (entity instanceof MidnightLurkerHallucinationEntity _datEntSetI)
			_datEntSetI.getEntityData().set(MidnightLurkerHallucinationEntity.DATA_CanBeSeenChance, Mth.nextInt(RandomSource.create(), 0, 3));
	}
}
