package net.voxla.hybridvx.entity.model;

import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.constant.DataTickets;

import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;

public class AmalgamationModel extends GeoModel<AmalgamationEntity> {
	@Override
	public ResourceLocation getAnimationResource(AmalgamationEntity entity) {
		return new ResourceLocation("hybrid_vx", "animations/amalgamation.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(AmalgamationEntity entity) {
		return new ResourceLocation("hybrid_vx", "geo/amalgamation.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(AmalgamationEntity entity) {
		return new ResourceLocation("hybrid_vx", "textures/entities/" + entity.getTexture() + ".png");
	}

	@Override
	public void setCustomAnimations(AmalgamationEntity animatable, long instanceId, AnimationState animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("mftf_hctrl");
		if (head != null) {
			EntityModelData entityData = (EntityModelData) animationState.getData(DataTickets.ENTITY_MODEL_DATA);
			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}

	}
}
