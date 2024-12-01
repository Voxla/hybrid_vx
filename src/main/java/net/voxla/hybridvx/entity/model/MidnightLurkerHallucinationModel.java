package net.voxla.hybridvx.entity.model;

import software.bernie.geckolib.model.data.EntityModelData;
import software.bernie.geckolib.model.GeoModel;
import software.bernie.geckolib.core.animation.AnimationState;
import software.bernie.geckolib.core.animatable.model.CoreGeoBone;
import software.bernie.geckolib.constant.DataTickets;

import net.voxla.hybridvx.entity.MidnightLurkerHallucinationEntity;

import net.minecraft.util.Mth;
import net.minecraft.resources.ResourceLocation;

public class MidnightLurkerHallucinationModel extends GeoModel<MidnightLurkerHallucinationEntity> {
	@Override
	public ResourceLocation getAnimationResource(MidnightLurkerHallucinationEntity entity) {
		return new ResourceLocation("hybrid_vx", "animations/ml_hallucination.animation.json");
	}

	@Override
	public ResourceLocation getModelResource(MidnightLurkerHallucinationEntity entity) {
		return new ResourceLocation("hybrid_vx", "geo/ml_hallucination.geo.json");
	}

	@Override
	public ResourceLocation getTextureResource(MidnightLurkerHallucinationEntity entity) {
		return new ResourceLocation("hybrid_vx", "textures/entities/" + entity.getTexture() + ".png");
	}

	@Override
	public void setCustomAnimations(MidnightLurkerHallucinationEntity animatable, long instanceId, AnimationState animationState) {
		CoreGeoBone head = getAnimationProcessor().getBone("head_r");
		if (head != null) {
			EntityModelData entityData = (EntityModelData) animationState.getData(DataTickets.ENTITY_MODEL_DATA);
			head.setRotX(entityData.headPitch() * Mth.DEG_TO_RAD);
			head.setRotY(entityData.netHeadYaw() * Mth.DEG_TO_RAD);
		}

	}
}
