package net.voxla.hybridvx.procedures;

import net.voxla.hybridvx.network.HybridVxModVariables;
import net.voxla.hybridvx.init.HybridVxModEntities;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.client.model.data.ModelData;
import net.minecraftforge.client.event.RenderLevelStageEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.RandomSource;
import net.minecraft.util.Mth;
import net.minecraft.core.BlockPos;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.LightTexture;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.Minecraft;

import javax.annotation.Nullable;

import java.util.Map;
import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

@Mod.EventBusSubscriber(value = Dist.CLIENT)
public class RenderMlsProcedure {
	private static RenderLevelStageEvent provider = null;
	private static Map<EntityType, Entity> data = new HashMap<>();

	public static void renderBlock(BlockState blockState, double x, double y, double z, float yaw, float pitch, float roll, float scale, boolean glowing) {
		ClientLevel level = Minecraft.getInstance().level;
		Vec3 pos = provider.getCamera().getPosition();
		BlockPos blockPos = BlockPos.containing(x, y, z);
		int packedLight = glowing ? LightTexture.FULL_BRIGHT : LightTexture.pack(level.getBrightness(LightLayer.BLOCK, blockPos), level.getBrightness(LightLayer.SKY, blockPos));
		PoseStack poseStack = provider.getPoseStack();
		poseStack.pushPose();
		poseStack.translate(x - pos.x(), y - pos.y(), z - pos.z());
		poseStack.mulPose(com.mojang.math.Axis.YN.rotationDegrees(yaw));
		poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(pitch));
		poseStack.mulPose(com.mojang.math.Axis.ZN.rotationDegrees(roll));
		poseStack.scale(scale, scale, scale);
		poseStack.translate(-0.5F, -0.5F, -0.5F);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		renderBlockModel(blockState, blockPos, poseStack, packedLight);
		renderBlockEntity(blockState, blockPos, poseStack, packedLight);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		poseStack.popPose();
	}

	private static void renderBlockEntity(BlockState blockState, BlockPos blockPos, PoseStack poseStack, int packedLight) {
		if (blockState.getBlock() instanceof EntityBlock entityBlock) {
			Minecraft minecraft = Minecraft.getInstance();
			ClientLevel level = minecraft.level;
			BlockEntity blockEntity = entityBlock.newBlockEntity(blockPos, blockState);
			if (blockEntity != null) {
				BlockEntityRenderer blockEntityRenderer = minecraft.getBlockEntityRenderDispatcher().getRenderer(blockEntity);
				if (blockEntityRenderer != null) {
					MultiBufferSource.BufferSource bufferSource = minecraft.renderBuffers().bufferSource();
					blockEntity.setLevel(level);
					blockEntityRenderer.render(blockEntity, 0.0F, poseStack, bufferSource, packedLight, OverlayTexture.NO_OVERLAY);
				}
			}
		}
	}

	private static void renderBlockModel(BlockState blockState, BlockPos blockPos, PoseStack poseStack, int packedLight) {
		if (blockState.getRenderShape() == RenderShape.MODEL) {
			Minecraft minecraft = Minecraft.getInstance();
			ClientLevel level = minecraft.level;
			MultiBufferSource.BufferSource bufferSource = minecraft.renderBuffers().bufferSource();
			BlockRenderDispatcher dispatcher = minecraft.getBlockRenderer();
			ModelBlockRenderer renderer = dispatcher.getModelRenderer();
			BakedModel bakedModel = dispatcher.getBlockModel(blockState);
			ModelData modelData = bakedModel.getModelData(level, blockPos, blockState, ModelData.builder().build());
			PoseStack.Pose pose = poseStack.last();
			int color = minecraft.getBlockColors().getColor(blockState, level, blockPos);
			float red = (color >> 16 & 255) / 255.0F;
			float green = (color >> 8 & 255) / 255.0F;
			float blue = (color & 255) / 255.0F;
			for (RenderType renderType : bakedModel.getRenderTypes(blockState, RandomSource.create(42), modelData)) {
				renderer.renderModel(pose, bufferSource.getBuffer(Sheets.translucentCullBlockSheet()), blockState, bakedModel, red, green, blue, packedLight, OverlayTexture.NO_OVERLAY, modelData, renderType);
			}
		}
	}

	public static void renderEntity(EntityType type, double x, double y, double z, float yaw, float pitch, float roll, float scale, boolean glowing) {
		if (type == null)
			return;
		Entity entity;
		if (data.containsKey(type)) {
			entity = data.get(type);
		} else {
			entity = type.create(Minecraft.getInstance().level);
			data.put(type, entity);
		}
		ClientLevel level = Minecraft.getInstance().level;
		BlockPos blockPos = BlockPos.containing(x, y, z);
		int packedLight = glowing ? LightTexture.FULL_BRIGHT : LightTexture.pack(level.getBrightness(LightLayer.BLOCK, blockPos), level.getBrightness(LightLayer.SKY, blockPos));
		renderEntity(entity, 0.0F, x, y, z, yaw, pitch, roll, scale, packedLight);
	}

	public static void renderEntity(Entity entity, double x, double y, double z, float yaw, float pitch, float roll, float scale, boolean glowing) {
		EntityRenderer renderer = Minecraft.getInstance().getEntityRenderDispatcher().getRenderer(entity);
		float partialTick = provider.getPartialTick();
		int packedLight = glowing ? LightTexture.FULL_BRIGHT : renderer.getPackedLightCoords(entity, partialTick);
		renderEntity(entity, partialTick, x, y, z, yaw, pitch, roll, scale, packedLight);
	}

	private static void renderEntity(Entity entity, float partialTick, double x, double y, double z, float yaw, float pitch, float roll, float scale, int packedLight) {
		if (entity == null)
			return;
		Minecraft minecraft = Minecraft.getInstance();
		MultiBufferSource.BufferSource bufferSource = minecraft.renderBuffers().bufferSource();
		EntityRenderer renderer = minecraft.getEntityRenderDispatcher().getRenderer(entity);
		Vec3 pos = provider.getCamera().getPosition();
		float offset = (entity.getBbHeight() / 2.0F) * scale;
		PoseStack poseStack = provider.getPoseStack();
		poseStack.pushPose();
		poseStack.translate(x - pos.x(), y + offset - pos.y(), z - pos.z());
		poseStack.mulPose(com.mojang.math.Axis.YN.rotationDegrees(yaw));
		poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(pitch));
		poseStack.mulPose(com.mojang.math.Axis.ZN.rotationDegrees(roll));
		poseStack.translate(0.0F, -offset, 0.0F);
		poseStack.scale(scale, scale, scale);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		renderer.render(entity, entity.getViewYRot(partialTick), partialTick, poseStack, bufferSource, packedLight);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		poseStack.popPose();
	}

	public static void renderItem(ItemStack itemStack, double x, double y, double z, float yaw, float pitch, float roll, float scale, boolean flipping, boolean glowing) {
		Minecraft minecraft = Minecraft.getInstance();
		ClientLevel level = minecraft.level;
		MultiBufferSource.BufferSource bufferSource = minecraft.renderBuffers().bufferSource();
		ItemRenderer renderer = minecraft.getItemRenderer();
		Vec3 pos = provider.getCamera().getPosition();
		int packedLight;
		if (glowing) {
			packedLight = LightTexture.FULL_BRIGHT;
		} else {
			BlockPos blockPos = BlockPos.containing(x, y, z);
			packedLight = LightTexture.pack(level.getBrightness(LightLayer.BLOCK, blockPos), level.getBrightness(LightLayer.SKY, blockPos));
		}
		PoseStack poseStack = provider.getPoseStack();
		poseStack.pushPose();
		poseStack.translate(x - pos.x(), y - pos.y(), z - pos.z());
		poseStack.mulPose(com.mojang.math.Axis.YN.rotationDegrees(yaw));
		poseStack.mulPose(com.mojang.math.Axis.XP.rotationDegrees(pitch));
		poseStack.mulPose(com.mojang.math.Axis.ZN.rotationDegrees(roll));
		poseStack.scale(scale, scale, scale);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		renderer.renderStatic(null, itemStack, ItemDisplayContext.FIXED, flipping, poseStack, bufferSource, level, packedLight, OverlayTexture.NO_OVERLAY, 0);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		poseStack.popPose();
	}

	@SubscribeEvent
	public static void renderModels(RenderLevelStageEvent event) {
		provider = event;
		if (provider.getStage() == RenderLevelStageEvent.Stage.AFTER_ENTITIES) {
			ClientLevel level = Minecraft.getInstance().level;
			Entity entity = provider.getCamera().getEntity();
			Vec3 pos = entity.getPosition((float) provider.getPartialTick());
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			execute(provider, pos.x(), pos.z(), entity, provider.getPartialTick());
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
			RenderSystem.defaultBlendFunc();
			RenderSystem.disableBlend();
			RenderSystem.enableCull();
			RenderSystem.enableDepthTest();
			RenderSystem.depthMask(true);
			RenderSystem.colorMask(true, true, true, true);
		}
	}

	public static void execute(double x, double z, Entity entity, double partialTick) {
		execute(null, x, z, entity, partialTick);
	}

	private static void execute(@Nullable Event event, double x, double z, Entity entity, double partialTick) {
		if (entity == null)
			return;
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).PanicAttackTimer > 0
				&& (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter >= 7200) {
			if (Math.random() > 0.9) {
				renderEntity(HybridVxModEntities.MIDNIGHT_LURKER_HALLUCINATION.get(), (x + Mth.nextInt(RandomSource.create(), -15, 15)), (entity.getPosition((float) partialTick).y()), (z + Mth.nextInt(RandomSource.create(), -15, 15)),
						(float) (entity.getViewYRot((float) partialTick) - 180), 0, 0, 1, false);
			}
		}
		if ((entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).PanicAttackTimer > 0
				&& (entity.getCapability(HybridVxModVariables.PLAYER_VARIABLES_CAPABILITY, null).orElse(new HybridVxModVariables.PlayerVariables())).FearMeter >= 9600) {
			renderEntity(HybridVxModEntities.MIDNIGHT_LURKER_HALLUCINATION.get(), (x + Mth.nextInt(RandomSource.create(), -15, 15)), (entity.getPosition((float) partialTick).y()), (z + Mth.nextInt(RandomSource.create(), -15, 15)),
					(float) (entity.getViewYRot((float) partialTick) - 180), 0, 0, 1, false);
			if (Math.random() > 0.9) {
				renderEntity(HybridVxModEntities.HYBRID.get(), (x + Mth.nextInt(RandomSource.create(), -15, 15)), (entity.getPosition((float) partialTick).y()), (z + Mth.nextInt(RandomSource.create(), -15, 15)),
						(float) (entity.getViewYRot((float) partialTick) - 180), 0, 0, 1, false);
			}
		}
	}
}
