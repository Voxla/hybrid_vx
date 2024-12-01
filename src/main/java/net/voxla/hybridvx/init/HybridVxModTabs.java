
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.voxla.hybridvx.init;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.CreativeModeTabEvent;

import net.minecraft.world.item.CreativeModeTabs;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HybridVxModTabs {
	@SubscribeEvent
	public static void buildTabContentsVanilla(CreativeModeTabEvent.BuildContents tabData) {
		if (tabData.getTab() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
			tabData.accept(HybridVxModBlocks.CRIMSON_CAMPFIRE.get().asItem());
		} else if (tabData.getTab() == CreativeModeTabs.COMBAT) {
			tabData.accept(HybridVxModItems.VILE_BLADE.get());
		} else if (tabData.getTab() == CreativeModeTabs.SPAWN_EGGS) {
			tabData.accept(HybridVxModItems.HYBRID_SPAWN_EGG.get());
		} else if (tabData.getTab() == CreativeModeTabs.INGREDIENTS) {
			tabData.accept(HybridVxModItems.CRIMSON_FLAME.get());
			tabData.accept(HybridVxModItems.VILE_FLESH.get());
			tabData.accept(HybridVxModItems.VILE_BONE.get());
		} else if (tabData.getTab() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
			tabData.accept(HybridVxModItems.VILE_MIRROR.get());
		}
	}
}
