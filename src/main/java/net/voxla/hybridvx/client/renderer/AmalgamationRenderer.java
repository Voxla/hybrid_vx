
package net.voxla.hybridvx.client.renderer;

import software.bernie.geckolib.renderer.GeoEntityRenderer;
import software.bernie.geckolib.cache.object.BakedGeoModel;

import net.voxla.hybridvx.entity.model.AmalgamationModel;
import net.voxla.hybridvx.entity.layer.AmalgamationLayer;
import net.voxla.hybridvx.entity.AmalgamationEntity;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;

import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.blaze3d.vertex.PoseStack;

public class AmalgamationRenderer extends GeoEntityRenderer<AmalgamationEntity> {
	public AmalgamationRenderer(EntityRendererProvider.Context renderManager) {
		super(renderManager, new AmalgamationModel());
		this.shadowRadius = 0.7f;
		this.addRenderLayer(new AmalgamationLayer(this));
	}

	@Override
	public RenderType getRenderType(AmalgamationEntity animatable, ResourceLocation texture, MultiBufferSource bufferSource, float partialTick) {
		return RenderType.entityTranslucent(getTextureLocation(animatable));
	}

	@Override
	public void preRender(PoseStack poseStack, AmalgamationEntity entity, BakedGeoModel model, MultiBufferSource bufferSource, VertexConsumer buffer, boolean isReRender, float partialTick, int packedLight, int packedOverlay, float red, float green,
			float blue, float alpha) {
		float scale = 1f;
		this.scaleHeight = scale;
		this.scaleWidth = scale;
		super.preRender(poseStack, entity, model, bufferSource, buffer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
