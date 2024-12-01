package net.voxla.hybridvx.entity.model;

import software.bernie.geckolib.model.GeoModel;

import net.voxla.hybridvx.entity.VoidGatewayHallucinationEntity;

import net.minecraft.resources.ResourceLocation;

public class VoidGatewayHallucinationModel extends GeoModel<VoidGatewayHallucinationEntity> {
	@Override
	public ResourceLocation getAnimationResource(VoidGatewayHallucinationEntity entity) {
		return new ResourceLocation("hybrid_vx", "animations/voidgateway.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(VoidGatewayHallucinationEntity entity) {
		return new ResourceLocation("hybrid_vx", "geo/voidgateway.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(VoidGatewayHallucinationEntity entity) {
		return new ResourceLocation("hybrid_vx", "textures/entities/" + entity.getTexture() + ".png");
	}

}
