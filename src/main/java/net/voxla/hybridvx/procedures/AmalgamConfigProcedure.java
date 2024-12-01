package net.voxla.hybridvx.procedures;

import net.minecraftforge.fml.loading.FMLPaths;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;

import javax.annotation.Nullable;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AmalgamConfigProcedure {
	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		execute();
	}

	public static void execute() {
		execute(null);
	}

	private static void execute(@Nullable Event event) {
		com.google.gson.JsonObject amaljsonobj = new com.google.gson.JsonObject();
		File hybridconfig = new File("");
		hybridconfig = new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "hybridvxconfig.json");
		if (!hybridconfig.exists()) {
			try {
				hybridconfig.getParentFile().mkdirs();
				hybridconfig.createNewFile();
			} catch (IOException exception) {
				exception.printStackTrace();
			}
			amaljsonobj.addProperty("hybrid_spawntime_min", 60);
			amaljsonobj.addProperty("hybrid_spawntime_max", 240);
			amaljsonobj.addProperty(
					"a random number is generated between min and max for the spawn time, the spawn numbers are in seconds so set how many seconds you want it to take for him to spawn on min and max, WARNING do not make max time lower than min time otherwise it will cause bugs or crash the game",
					"");
			amaljsonobj.addProperty("can_have_panic_attacks", true);
			amaljsonobj.addProperty("hallucination_jumpscare", true);
			amaljsonobj.addProperty("hybrid_spawn_chance", 2);
			amaljsonobj.addProperty(
					"if you want a higher chance of the hybrid spawning set the spawn chance to a lower number, if you want a lower chance for the hybrid to spawn set the spawn chance to a higher number, WARNING do not set this number below 1 otherwise it may bug or crash the game",
					"");
			amaljsonobj.addProperty("hybrid_chase_music", true);
			amaljsonobj.addProperty("hybrid_mftf_ai_spawn_sound", true);
			{
				com.google.gson.Gson mainGSONBuilderVariable = new com.google.gson.GsonBuilder().setPrettyPrinting().create();
				try {
					FileWriter fileWriter = new FileWriter(hybridconfig);
					fileWriter.write(mainGSONBuilderVariable.toJson(amaljsonobj));
					fileWriter.close();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
			}
		}
	}
}
