
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.voxla.hybridvx.init;

import net.voxla.hybridvx.block.HybridVXRegenBlockBlock;
import net.voxla.hybridvx.block.CrimsonFireBlock;
import net.voxla.hybridvx.block.CrimsonCampfireBlock;
import net.voxla.hybridvx.HybridVxMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.Block;

public class HybridVxModBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, HybridVxMod.MODID);
	public static final RegistryObject<Block> CRIMSON_CAMPFIRE = REGISTRY.register("crimson_campfire", () -> new CrimsonCampfireBlock());
	public static final RegistryObject<Block> CRIMSON_FIRE = REGISTRY.register("crimson_fire", () -> new CrimsonFireBlock());
	public static final RegistryObject<Block> HYBRID_VX_REGEN_BLOCK = REGISTRY.register("hybrid_vx_regen_block", () -> new HybridVXRegenBlockBlock());
	// Start of user code block custom blocks
	// End of user code block custom blocks
}
