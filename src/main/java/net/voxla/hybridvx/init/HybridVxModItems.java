
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.voxla.hybridvx.init;

import net.voxla.hybridvx.item.VileMirrorItem;
import net.voxla.hybridvx.item.VileFleshItem;
import net.voxla.hybridvx.item.VileBoneItem;
import net.voxla.hybridvx.item.VileBladeItem;
import net.voxla.hybridvx.item.CrimsonFlameItem;
import net.voxla.hybridvx.item.AmalgamConfigWarningItemItem;
import net.voxla.hybridvx.item.AmalgamConfigTooltipItem7Item;
import net.voxla.hybridvx.item.AmalgamConfigTooltipItem6Item;
import net.voxla.hybridvx.item.AmalgamConfigTooltipItem5Item;
import net.voxla.hybridvx.item.AmalgamConfigTooltipItem4Item;
import net.voxla.hybridvx.item.AmalgamConfigTooltipItem3Item;
import net.voxla.hybridvx.item.AmalgamConfigTooltipItem1Item;
import net.voxla.hybridvx.HybridVxMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;

public class HybridVxModItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, HybridVxMod.MODID);
	public static final RegistryObject<Item> CRIMSON_CAMPFIRE = block(HybridVxModBlocks.CRIMSON_CAMPFIRE);
	public static final RegistryObject<Item> CRIMSON_FLAME = REGISTRY.register("crimson_flame", () -> new CrimsonFlameItem());
	public static final RegistryObject<Item> CRIMSON_FIRE = block(HybridVxModBlocks.CRIMSON_FIRE);
	public static final RegistryObject<Item> HYBRID_SPAWN_EGG = REGISTRY.register("hybrid_spawn_egg", () -> new ForgeSpawnEggItem(HybridVxModEntities.HYBRID, -11845066, -13224394, new Item.Properties()));
	public static final RegistryObject<Item> HYBRID_VX_REGEN_BLOCK = block(HybridVxModBlocks.HYBRID_VX_REGEN_BLOCK);
	public static final RegistryObject<Item> VILE_BLADE = REGISTRY.register("vile_blade", () -> new VileBladeItem());
	public static final RegistryObject<Item> VILE_MIRROR = REGISTRY.register("vile_mirror", () -> new VileMirrorItem());
	public static final RegistryObject<Item> VILE_FLESH = REGISTRY.register("vile_flesh", () -> new VileFleshItem());
	public static final RegistryObject<Item> VILE_BONE = REGISTRY.register("vile_bone", () -> new VileBoneItem());
	public static final RegistryObject<Item> AMALGAM_CONFIG_WARNING_ITEM = REGISTRY.register("amalgam_config_warning_item", () -> new AmalgamConfigWarningItemItem());
	public static final RegistryObject<Item> AMALGAM_CONFIG_TOOLTIP_ITEM_1 = REGISTRY.register("amalgam_config_tooltip_item_1", () -> new AmalgamConfigTooltipItem1Item());
	public static final RegistryObject<Item> AMALGAM_CONFIG_TOOLTIP_ITEM_3 = REGISTRY.register("amalgam_config_tooltip_item_3", () -> new AmalgamConfigTooltipItem3Item());
	public static final RegistryObject<Item> AMALGAM_CONFIG_TOOLTIP_ITEM_4 = REGISTRY.register("amalgam_config_tooltip_item_4", () -> new AmalgamConfigTooltipItem4Item());
	public static final RegistryObject<Item> AMALGAM_CONFIG_TOOLTIP_ITEM_5 = REGISTRY.register("amalgam_config_tooltip_item_5", () -> new AmalgamConfigTooltipItem5Item());
	public static final RegistryObject<Item> AMALGAM_CONFIG_TOOLTIP_ITEM_6 = REGISTRY.register("amalgam_config_tooltip_item_6", () -> new AmalgamConfigTooltipItem6Item());
	public static final RegistryObject<Item> AMALGAM_CONFIG_TOOLTIP_ITEM_7 = REGISTRY.register("amalgam_config_tooltip_item_7", () -> new AmalgamConfigTooltipItem7Item());

	// Start of user code block custom items
	// End of user code block custom items
	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}
