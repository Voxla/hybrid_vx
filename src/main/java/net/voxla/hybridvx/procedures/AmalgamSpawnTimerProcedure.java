package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.network.HybridVxModVariables;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.Level;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.server.level.ServerLevel;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileReader;
import java.io.File;
import java.io.BufferedReader;

@Mod.EventBusSubscriber
public class AmalgamSpawnTimerProcedure {
	@SubscribeEvent
	public static void onWorldTick(TickEvent.LevelTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			execute(event, event.level);
		}
	}

	public static void execute(LevelAccessor world) {
		execute(null, world);
	}

	private static void execute(@Nullable Event event, LevelAccessor world) {
		File hybridconfig = new File("");
		com.google.gson.JsonObject amaljsonobj = new com.google.gson.JsonObject();
		if (world instanceof ServerLevel _origLevel) {
			LevelAccessor _worldorig = world;
			world = _origLevel.getServer().getLevel(Level.OVERWORLD);
			if (world != null) {
				if ((world instanceof Level _lvl0 && _lvl0.isDay()) == true && HybridVxModVariables.WorldVariables.get(world).IsDaytimeA == false) {
					HybridVxModVariables.WorldVariables.get(world).IsDaytimeA = true;
					HybridVxModVariables.WorldVariables.get(world).syncData(world);
				} else if ((world instanceof Level _lvl1 && _lvl1.isDay()) == false && HybridVxModVariables.WorldVariables.get(world).IsDaytimeA == true) {
					HybridVxModVariables.WorldVariables.get(world).IsDaytimeA = false;
					HybridVxModVariables.WorldVariables.get(world).syncData(world);
				}
				if (world.dayTime() > 24000 && HybridVxModVariables.WorldVariables.get(world).HasOneDayPassedA == false) {
					HybridVxModVariables.WorldVariables.get(world).HasOneDayPassedA = true;
					HybridVxModVariables.WorldVariables.get(world).syncData(world);
				}
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
						if (HybridVxModVariables.WorldVariables.get(world).HasOneDayPassedA == true) {
							if (HybridVxModVariables.WorldVariables.get(world).AmalgamSpawnTimer > 0) {
								if (HybridVxModVariables.WorldVariables.get(world).AmalgamSpawnTimer == 20) {
									HybridVxModVariables.WorldVariables.get(world).AmalgamSpawnChance = Mth.nextInt(RandomSource.create(), 0, (int) amaljsonobj.get("hybrid_spawn_chance").getAsDouble());
									HybridVxModVariables.WorldVariables.get(world).syncData(world);
								}
								HybridVxModVariables.WorldVariables.get(world).AmalgamSpawnTimer = HybridVxModVariables.WorldVariables.get(world).AmalgamSpawnTimer - 1;
								HybridVxModVariables.WorldVariables.get(world).syncData(world);
							}
							if (HybridVxModVariables.WorldVariables.get(world).AmalgamSpawnTimer == 0) {
								HybridVxModVariables.WorldVariables.get(world).AmalgamSpawnTimer = Mth.nextInt(RandomSource.create(), (int) (amaljsonobj.get("hybrid_spawntime_min").getAsDouble() * 20),
										(int) (amaljsonobj.get("hybrid_spawntime_max").getAsDouble() * 20));
								HybridVxModVariables.WorldVariables.get(world).syncData(world);
							}
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
			world = _worldorig;
		}
	}
}
