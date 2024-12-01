package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.world.entity.Entity;
import net.minecraft.sounds.SoundSource;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

public class AmalgamChaseMusicPlayerProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		com.google.gson.JsonObject amaljsonobj = new com.google.gson.JsonObject();
		File hybridconfig = new File("");
		hybridconfig = new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "hybridvxconfig.json");
		{
			try {
				BufferedReader bufferedReader = new BufferedReader(new FileReader(hybridconfig));
				StringBuilder jsonstringbuilder = new StringBuilder();
				String line;
				while ((line = bufferedReader.readLine()) != null) {
					jsonstringbuilder.append(line);
				}
				bufferedReader.close();
				amaljsonobj = new com.google.gson.Gson().fromJson(jsonstringbuilder.toString(), com.google.gson.JsonObject.class);
				if (amaljsonobj.get("hybrid_chase_music").getAsBoolean() == true) {
					if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ChaseMusicTimer) : 0) > 0
							&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_Attacking) : 0) > 0) {
						if (entity instanceof AmalgamationEntity _datEntSetI)
							_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_ChaseMusicTimer, (int) ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ChaseMusicTimer) : 0) - 1));
					}
					if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ChaseMusicTimer) : 0) == 0
							&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_Attacking) : 0) > 0) {
						if (entity instanceof AmalgamationEntity _datEntSetI)
							_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_ChaseMusicTimer, 112);
						if (!world.isClientSide()) {
							if (world instanceof Level _level) {
								if (!_level.isClientSide()) {
									_level.playSound(null, BlockPos.containing(x, y, z), ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:music.hybrid.chase")), SoundSource.RECORDS, 4, 1);
								} else {
									_level.playLocalSound(x, y, z, ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:music.hybrid.chase")), SoundSource.RECORDS, 4, 1, false);
								}
							}
						}
					}
					if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_ChaseMusicTimer) : 0) > 0
							&& (entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_Attacking) : 0) == 0) {
						if (entity instanceof AmalgamationEntity _datEntSetI)
							_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_ChaseMusicTimer, 0);
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
