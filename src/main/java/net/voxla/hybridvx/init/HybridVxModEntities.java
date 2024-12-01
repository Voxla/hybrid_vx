
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.voxla.hybridvx.init;

import net.voxla.hybridvx.entity.VoidGatewayHallucinationEntity;
import net.voxla.hybridvx.entity.MidnightLurkerHallucinationEntity;
import net.voxla.hybridvx.entity.MidnightLurkerFootstepHallucinationEntity;
import net.voxla.hybridvx.entity.AmalgamationEntity;
import net.voxla.hybridvx.HybridVxMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;

import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class HybridVxModEntities {
	public static final DeferredRegister<EntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, HybridVxMod.MODID);
	public static final RegistryObject<EntityType<AmalgamationEntity>> HYBRID = register("hybrid", EntityType.Builder.<AmalgamationEntity>of(AmalgamationEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64)
			.setUpdateInterval(3).setCustomClientFactory(AmalgamationEntity::new).fireImmune().sized(0.7f, 2.8f));
	public static final RegistryObject<EntityType<MidnightLurkerHallucinationEntity>> MIDNIGHT_LURKER_HALLUCINATION = register("midnight_lurker_hallucination",
			EntityType.Builder.<MidnightLurkerHallucinationEntity>of(MidnightLurkerHallucinationEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(MidnightLurkerHallucinationEntity::new).fireImmune().sized(0.7f, 2.5f));
	public static final RegistryObject<EntityType<VoidGatewayHallucinationEntity>> VOID_GATEWAY_HALLUCINATION = register("void_gateway_hallucination",
			EntityType.Builder.<VoidGatewayHallucinationEntity>of(VoidGatewayHallucinationEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(VoidGatewayHallucinationEntity::new).fireImmune().sized(0.6f, 1.4f));
	public static final RegistryObject<EntityType<MidnightLurkerFootstepHallucinationEntity>> MIDNIGHT_LURKER_FOOTSTEP_HALLUCINATION = register("midnight_lurker_footstep_hallucination",
			EntityType.Builder.<MidnightLurkerFootstepHallucinationEntity>of(MidnightLurkerFootstepHallucinationEntity::new, MobCategory.MONSTER).setShouldReceiveVelocityUpdates(true).setTrackingRange(64).setUpdateInterval(3)
					.setCustomClientFactory(MidnightLurkerFootstepHallucinationEntity::new).fireImmune().sized(0.6f, 1.4f));

	private static <T extends Entity> RegistryObject<EntityType<T>> register(String registryname, EntityType.Builder<T> entityTypeBuilder) {
		return REGISTRY.register(registryname, () -> (EntityType<T>) entityTypeBuilder.build(registryname));
	}

	@SubscribeEvent
	public static void init(FMLCommonSetupEvent event) {
		event.enqueueWork(() -> {
			AmalgamationEntity.init();
			MidnightLurkerHallucinationEntity.init();
			VoidGatewayHallucinationEntity.init();
			MidnightLurkerFootstepHallucinationEntity.init();
		});
	}

	@SubscribeEvent
	public static void registerAttributes(EntityAttributeCreationEvent event) {
		event.put(HYBRID.get(), AmalgamationEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_HALLUCINATION.get(), MidnightLurkerHallucinationEntity.createAttributes().build());
		event.put(VOID_GATEWAY_HALLUCINATION.get(), VoidGatewayHallucinationEntity.createAttributes().build());
		event.put(MIDNIGHT_LURKER_FOOTSTEP_HALLUCINATION.get(), MidnightLurkerFootstepHallucinationEntity.createAttributes().build());
	}
}
