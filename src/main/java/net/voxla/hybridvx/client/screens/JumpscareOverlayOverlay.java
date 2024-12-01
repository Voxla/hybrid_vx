
package net.voxla.hybridvx.client.screens;

import org.checkerframework.checker.units.qual.h;

import net.voxla.hybridvx.procedures.JumpscareOverlayDisplayOverlayIngameProcedure;
import net.voxla.hybridvx.procedures.JumpscareLurkerShow9Procedure;
import net.voxla.hybridvx.procedures.JumpscareLurkerShow8Procedure;
import net.voxla.hybridvx.procedures.JumpscareLurkerShow7Procedure;
import net.voxla.hybridvx.procedures.JumpscareLurkerShow6Procedure;
import net.voxla.hybridvx.procedures.JumpscareLurkerShow5Procedure;
import net.voxla.hybridvx.procedures.JumpscareLurkerShow4Procedure;
import net.voxla.hybridvx.procedures.JumpscareLurkerShow3Procedure;
import net.voxla.hybridvx.procedures.JumpscareLurkerShow2Procedure;
import net.voxla.hybridvx.procedures.JumpscareLurkerShow1Procedure;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGuiEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class JumpscareOverlayOverlay {
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGuiEvent.Pre event) {
		int w = event.getWindow().getGuiScaledWidth();
		int h = event.getWindow().getGuiScaledHeight();
		Level world = null;
		double x = 0;
		double y = 0;
		double z = 0;
		Player entity = Minecraft.getInstance().player;
		if (entity != null) {
			world = entity.level;
			x = entity.getX();
			y = entity.getY();
			z = entity.getZ();
		}
		RenderSystem.disableDepthTest();
		RenderSystem.depthMask(false);
		RenderSystem.enableBlend();
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
		RenderSystem.setShaderColor(1, 1, 1, 1);
		if (JumpscareOverlayDisplayOverlayIngameProcedure.execute(entity)) {
			if (JumpscareLurkerShow1Procedure.execute(entity)) {
				RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/ml_hallucination_jumpscare1.png"));
				Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -227, h - 268, 0, 0, 506, 271, 506, 271);
			}
			if (JumpscareLurkerShow2Procedure.execute(entity)) {
				RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/ml_hallucination_jumpscare2.png"));
				Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -227, h - 268, 0, 0, 506, 271, 506, 271);
			}
			if (JumpscareLurkerShow3Procedure.execute(entity)) {
				RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/ml_hallucination_jumpscare3.png"));
				Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -227, h - 268, 0, 0, 506, 271, 506, 271);
			}
			if (JumpscareLurkerShow4Procedure.execute(entity)) {
				RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/ml_hallucination_jumpscare4.png"));
				Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -227, h - 268, 0, 0, 506, 271, 506, 271);
			}
			if (JumpscareLurkerShow5Procedure.execute(entity)) {
				RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/ml_hallucination_jumpscare5.png"));
				Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -227, h - 268, 0, 0, 506, 271, 506, 271);
			}
			if (JumpscareLurkerShow6Procedure.execute(entity)) {
				RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/ml_hallucination_jumpscare6.png"));
				Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -227, h - 268, 0, 0, 506, 271, 506, 271);
			}
			if (JumpscareLurkerShow7Procedure.execute(entity)) {
				RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/ml_hallucination_jumpscare7.png"));
				Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -227, h - 268, 0, 0, 506, 271, 506, 271);
			}
			if (JumpscareLurkerShow8Procedure.execute(entity)) {
				RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/ml_hallucination_jumpscare8.png"));
				Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -227, h - 268, 0, 0, 506, 271, 506, 271);
			}
			if (JumpscareLurkerShow9Procedure.execute(entity)) {
				RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/ml_hallucination_jumpscare9.png"));
				Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -227, h - 268, 0, 0, 506, 271, 506, 271);
			}
		}
		RenderSystem.depthMask(true);
		RenderSystem.defaultBlendFunc();
		RenderSystem.enableDepthTest();
		RenderSystem.disableBlend();
		RenderSystem.setShaderColor(1, 1, 1, 1);
	}
}
