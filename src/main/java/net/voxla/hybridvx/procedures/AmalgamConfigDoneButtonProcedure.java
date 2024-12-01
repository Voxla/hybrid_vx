package net.voxla.hybridvx.procedures;

import org.checkerframework.checker.units.qual.s;

import net.voxla.hybridvx.world.inventory.AmalgamConfigGuiMenu;

import net.minecraftforge.fml.loading.FMLPaths;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.Entity;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;

import java.util.HashMap;

import java.io.IOException;
import java.io.FileWriter;
import java.io.File;

public class AmalgamConfigDoneButtonProcedure {
	public static void execute(Entity entity, HashMap guistate) {
		if (entity == null || guistate == null)
			return;
		com.google.gson.JsonObject amaljsonobj = new com.google.gson.JsonObject();
		File hybridconfig = new File("");
		if (entity instanceof Player _plr0 && _plr0.containerMenu instanceof AmalgamConfigGuiMenu) {
			hybridconfig = new File((FMLPaths.GAMEDIR.get().toString() + "/config/"), File.separator + "hybridvxconfig.json");
			if (hybridconfig.exists()) {
				try {
					hybridconfig.getParentFile().mkdirs();
					hybridconfig.createNewFile();
				} catch (IOException exception) {
					exception.printStackTrace();
				}
				if ((guistate.containsKey("text:mininput") ? ((EditBox) guistate.get("text:mininput")).getValue() : "").equals("")) {
					amaljsonobj.addProperty("hybrid_spawntime_min", 60);
				} else {
					amaljsonobj.addProperty("hybrid_spawntime_min", new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(guistate.containsKey("text:mininput") ? ((EditBox) guistate.get("text:mininput")).getValue() : ""));
				}
				if ((guistate.containsKey("text:maxinput") ? ((EditBox) guistate.get("text:maxinput")).getValue() : "").equals("")) {
					amaljsonobj.addProperty("hybrid_spawntime_max", 240);
				} else {
					amaljsonobj.addProperty("hybrid_spawntime_max", new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(guistate.containsKey("text:maxinput") ? ((EditBox) guistate.get("text:maxinput")).getValue() : ""));
				}
				amaljsonobj.addProperty(
						"a random number is generated between min and max for the spawn time, the spawn numbers are in seconds so set how many seconds you want it to take for him to spawn on min and max, WARNING do not make max time lower than min time otherwise it will cause bugs or crash the game",
						"");
				if (guistate.containsKey("checkbox:paniccheck") ? ((Checkbox) guistate.get("checkbox:paniccheck")).selected() : false) {
					amaljsonobj.addProperty("can_have_panic_attacks", true);
				} else {
					amaljsonobj.addProperty("can_have_panic_attacks", false);
				}
				if (guistate.containsKey("checkbox:hallujumpcheck") ? ((Checkbox) guistate.get("checkbox:hallujumpcheck")).selected() : false) {
					amaljsonobj.addProperty("hallucination_jumpscare", true);
				} else {
					amaljsonobj.addProperty("hallucination_jumpscare", false);
				}
				if ((guistate.containsKey("text:spawnchance") ? ((EditBox) guistate.get("text:spawnchance")).getValue() : "").equals("")) {
					amaljsonobj.addProperty("hybrid_spawn_chance", 2);
				} else {
					amaljsonobj.addProperty("hybrid_spawn_chance", new Object() {
						double convert(String s) {
							try {
								return Double.parseDouble(s.trim());
							} catch (Exception e) {
							}
							return 0;
						}
					}.convert(guistate.containsKey("text:spawnchance") ? ((EditBox) guistate.get("text:spawnchance")).getValue() : ""));
				}
				amaljsonobj.addProperty(
						"if you want a higher chance of the hybrid spawning set the spawn chance to a lower number, if you want a lower chance for the hybrid to spawn set the spawn chance to a higher number, WARNING do not set this number below 1 otherwise it may bug or crash the game",
						"");
				if (guistate.containsKey("checkbox:chasecheck") ? ((Checkbox) guistate.get("checkbox:chasecheck")).selected() : false) {
					amaljsonobj.addProperty("hybrid_chase_music", true);
				} else {
					amaljsonobj.addProperty("hybrid_chase_music", false);
				}
				if (guistate.containsKey("checkbox:mftfsoundcheck") ? ((Checkbox) guistate.get("checkbox:mftfsoundcheck")).selected() : false) {
					amaljsonobj.addProperty("hybrid_mftf_ai_spawn_sound", true);
				} else {
					amaljsonobj.addProperty("hybrid_mftf_ai_spawn_sound", false);
				}
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
			if (entity instanceof Player _player)
				_player.closeContainer();
		}
	}
}
