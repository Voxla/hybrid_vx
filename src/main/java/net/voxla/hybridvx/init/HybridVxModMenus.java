
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.voxla.hybridvx.init;

import net.voxla.hybridvx.world.inventory.AmalgamConfigGuiMenu;
import net.voxla.hybridvx.HybridVxMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;

import net.minecraft.world.inventory.MenuType;

public class HybridVxModMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, HybridVxMod.MODID);
	public static final RegistryObject<MenuType<AmalgamConfigGuiMenu>> AMALGAM_CONFIG_GUI = REGISTRY.register("amalgam_config_gui", () -> IForgeMenuType.create(AmalgamConfigGuiMenu::new));
}
