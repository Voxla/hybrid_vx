
package net.voxla.hybridvx.client.renderer;

import net.voxla.hybridvx.entity.MidnightLurkerFootstepHallucinationEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.layers.HumanoidArmorLayer;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.HumanoidModel;

public class MidnightLurkerFootstepHallucinationRenderer extends HumanoidMobRenderer<MidnightLurkerFootstepHallucinationEntity, HumanoidModel<MidnightLurkerFootstepHallucinationEntity>> {
	public MidnightLurkerFootstepHallucinationRenderer(EntityRendererProvider.Context context) {
		super(context, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER)), 0f);
		this.addLayer(new HumanoidArmorLayer(this, new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_INNER_ARMOR)), new HumanoidModel(context.bakeLayer(ModelLayers.PLAYER_OUTER_ARMOR)), context.getModelManager()));
	}

	@Override
	public ResourceLocation getTextureLocation(MidnightLurkerFootstepHallucinationEntity entity) {
		return new ResourceLocation("hybrid_vx:textures/entities/notexture.png");
	}
}
