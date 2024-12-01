
package net.voxla.hybridvx.client.screens;

import org.checkerframework.checker.units.qual.h;

import net.voxla.hybridvx.procedures.PanicAttackBarProcProcedure;
import net.voxla.hybridvx.procedures.FearBarProc5Procedure;
import net.voxla.hybridvx.procedures.FearBarProc4Procedure;
import net.voxla.hybridvx.procedures.FearBarProc3Procedure;
import net.voxla.hybridvx.procedures.FearBarProc2Procedure;
import net.voxla.hybridvx.procedures.FearBarProc1Procedure;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.ScreenEvent;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.Minecraft;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber({Dist.CLIENT})
public class FearBarOverlayOverlay {
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(ScreenEvent.Render.Post event) {
		if (event.getScreen() instanceof InventoryScreen) {
			int w = event.getScreen().width;
			int h = event.getScreen().height;
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
			if (true) {
				if (FearBarProc1Procedure.execute(entity)) {
					RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/fearicon1.png"));
					Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -16, h / 2 + 77, 0, 0, 32, 32, 32, 32);
				}
				if (FearBarProc2Procedure.execute(entity)) {
					RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/fearicon1.png"));
					Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -16, h / 2 + 77, 0, 0, 32, 32, 32, 32);
				}
				if (FearBarProc3Procedure.execute(entity)) {
					RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/fearicon2.png"));
					Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -16, h / 2 + 77, 0, 0, 32, 32, 32, 32);
				}
				if (FearBarProc4Procedure.execute(entity)) {
					RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/fearicon3.png"));
					Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -16, h / 2 + 77, 0, 0, 32, 32, 32, 32);
				}
				if (FearBarProc5Procedure.execute(entity)) {
					RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/fearicon4.png"));
					Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -16, h / 2 + 77, 0, 0, 32, 32, 32, 32);
				}
				if (PanicAttackBarProcProcedure.execute(world, x, y, z, entity)) {
					RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/fearicon5.png"));
					Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -16, h / 2 + 77, 0, 0, 32, 32, 32, 32);
				}
				if (FearBarProc1Procedure.execute(entity)) {
					RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/fearbar0.png"));
					Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -24, h / 2 + 80, 0, 0, 48, 16, 48, 16);
				}
				if (FearBarProc2Procedure.execute(entity)) {
					RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/fearbar1.png"));
					Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -24, h / 2 + 80, 0, 0, 48, 16, 48, 16);
				}
				if (FearBarProc3Procedure.execute(entity)) {
					RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/fearbar2.png"));
					Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -24, h / 2 + 80, 0, 0, 48, 16, 48, 16);
				}
				if (FearBarProc4Procedure.execute(entity)) {
					RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/fearbar3.png"));
					Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -24, h / 2 + 80, 0, 0, 48, 16, 48, 16);
				}
				if (FearBarProc5Procedure.execute(entity)) {
					RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/fearbar4.png"));
					Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -24, h / 2 + 80, 0, 0, 48, 16, 48, 16);
				}
				if (PanicAttackBarProcProcedure.execute(world, x, y, z, entity)) {
					RenderSystem.setShaderTexture(0, new ResourceLocation("hybrid_vx:textures/screens/fearbar_overcharge.png"));
					Minecraft.getInstance().gui.blit(event.getPoseStack(), w / 2 + -24, h / 2 + 80, 0, 0, 48, 16, 48, 16);
				}
			}
			RenderSystem.depthMask(true);
			RenderSystem.defaultBlendFunc();
			RenderSystem.enableDepthTest();
			RenderSystem.disableBlend();
			RenderSystem.setShaderColor(1, 1, 1, 1);
		}
	}
}
