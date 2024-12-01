
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.voxla.hybridvx.init;

import net.voxla.hybridvx.block.entity.HybridVXRegenBlockBlockEntity;
import net.voxla.hybridvx.block.entity.CrimsonFireBlockEntity;
import net.voxla.hybridvx.HybridVxMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.Block;

public class HybridVxModBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, HybridVxMod.MODID);
	public static final RegistryObject<BlockEntityType<?>> CRIMSON_FIRE = register("crimson_fire", HybridVxModBlocks.CRIMSON_FIRE, CrimsonFireBlockEntity::new);
	public static final RegistryObject<BlockEntityType<?>> HYBRID_VX_REGEN_BLOCK = register("hybrid_vx_regen_block", HybridVxModBlocks.HYBRID_VX_REGEN_BLOCK, HybridVXRegenBlockBlockEntity::new);

	private static RegistryObject<BlockEntityType<?>> register(String registryname, RegistryObject<Block> block, BlockEntityType.BlockEntitySupplier<?> supplier) {
		return REGISTRY.register(registryname, () -> BlockEntityType.Builder.of(supplier, block.get()).build(null));
	}
}
