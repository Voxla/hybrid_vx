package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.loading.FMLPaths;

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

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

public class AmalgamationOnInitialEntitySpawnProcedure {
	public static void execute(LevelAccessor world, double x, double y, double z, Entity entity) {
		if (entity == null)
			return;
		double Px = 0;
		double Pz = 0;
		com.google.gson.JsonObject amaljsonobj = new com.google.gson.JsonObject();
		File hybridconfig = new File("");
		if (y > 50) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_AmalgamAI, Mth.nextInt(RandomSource.create(), 0, 4));
		} else if (y <= 50) {
			if (entity instanceof AmalgamationEntity _datEntSetI)
				_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_AmalgamAI, Mth.nextInt(RandomSource.create(), 1, 3));
			if (entity instanceof AmalgamationEntity _datEntSetL)
				_datEntSetL.getEntityData().set(AmalgamationEntity.DATA_SpawnedUnderground, true);
		}
		if (entity instanceof AmalgamationEntity _datEntSetI)
			_datEntSetI.getEntityData().set(AmalgamationEntity.DATA_AmalgamAI1Determin, Mth.nextInt(RandomSource.create(), 0, 1));
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
				if (amaljsonobj.get("hybrid_mftf_ai_spawn_sound").getAsBoolean() == true) {
					if ((entity instanceof AmalgamationEntity _datEntI ? _datEntI.getEntityData().get(AmalgamationEntity.DATA_AmalgamAI) : 0) == 0
							&& !world.getEntitiesOfClass(Player.class, AABB.ofSize(new Vec3(x, y, z), 600, 600, 600), e -> true).isEmpty()) {
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
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.spawn_mftf_ai")), SoundSource.RECORDS, 4, 1);
								} else {
									_level.playLocalSound(Px, (world.getHeight(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, (int) Px, (int) Pz) + 1.5), Pz,
											ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("hybrid_vx:entity.hybrid.spawn_mftf_ai")), SoundSource.RECORDS, 4, 1, false);
								}
							}
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
