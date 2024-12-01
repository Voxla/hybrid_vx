package net.voxla.hybridvx.client.gui;

import net.voxla.hybridvx.world.inventory.AmalgamConfigGuiMenu;
import net.voxla.hybridvx.network.AmalgamConfigGuiButtonMessage;
import net.voxla.hybridvx.HybridVxMod;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Checkbox;
import net.minecraft.client.gui.components.Button;

import java.util.HashMap;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.systems.RenderSystem;

public class AmalgamConfigGuiScreen extends AbstractContainerScreen<AmalgamConfigGuiMenu> {
	private final static HashMap<String, Object> guistate = AmalgamConfigGuiMenu.guistate;
	private final Level world;
	private final int x, y, z;
	private final Player entity;
	EditBox mininput;
	EditBox maxinput;
	EditBox spawnchance;
	Checkbox paniccheck;
	Checkbox hallujumpcheck;
	Checkbox chasecheck;
	Checkbox mftfsoundcheck;
	Button button_done;
	Button button_reset;
	Button button_cancel;

	public AmalgamConfigGuiScreen(AmalgamConfigGuiMenu container, Inventory inventory, Component text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.imageWidth = 300;
		this.imageHeight = 240;
	}

	@Override
	public boolean isPauseScreen() {
		return true;
	}

	private static final ResourceLocation texture = new ResourceLocation("hybrid_vx:textures/screens/amalgam_config_gui.png");

	@Override
	public void render(PoseStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		mininput.render(ms, mouseX, mouseY, partialTicks);
		maxinput.render(ms, mouseX, mouseY, partialTicks);
		spawnchance.render(ms, mouseX, mouseY, partialTicks);
		this.renderTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void renderBg(PoseStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.setShaderColor(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		RenderSystem.setShaderTexture(0, texture);
		this.blit(ms, this.leftPos, this.topPos, 0, 0, this.imageWidth, this.imageHeight, this.imageWidth, this.imageHeight);
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeContainer();
			return true;
		}
		if (mininput.isFocused())
			return mininput.keyPressed(key, b, c);
		if (maxinput.isFocused())
			return maxinput.keyPressed(key, b, c);
		if (spawnchance.isFocused())
			return spawnchance.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void containerTick() {
		super.containerTick();
		mininput.tick();
		maxinput.tick();
		spawnchance.tick();
	}

	@Override
	protected void renderLabels(PoseStack poseStack, int mouseX, int mouseY) {
		this.font.draw(poseStack, Component.translatable("gui.hybrid_vx.amalgam_config_gui.label_hybrid_spawntime_min"), 8, 23, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.hybrid_vx.amalgam_config_gui.label_hybrid_config"), 8, 5, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.hybrid_vx.amalgam_config_gui.label_hybrid_spawntime_max"), 8, 59, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.hybrid_vx.amalgam_config_gui.label_can_have_panic_attacks"), 8, 95, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.hybrid_vx.amalgam_config_gui.label_hallucination_jumpscare"), 8, 131, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.hybrid_vx.amalgam_config_gui.label_hybrid_spawn_chance"), 8, 167, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.hybrid_vx.amalgam_config_gui.label_hybrid_chase_music"), 157, 23, -12829636);
		this.font.draw(poseStack, Component.translatable("gui.hybrid_vx.amalgam_config_gui.label_hybrid_mftf_ai_spawn_sound"), 157, 59, -12829636);
	}

	@Override
	public void init() {
		super.init();
		mininput = new EditBox(this.font, this.leftPos + 9, this.topPos + 36, 118, 18, Component.translatable("gui.hybrid_vx.amalgam_config_gui.mininput"));
		mininput.setMaxLength(32767);
		guistate.put("text:mininput", mininput);
		this.addWidget(this.mininput);
		maxinput = new EditBox(this.font, this.leftPos + 9, this.topPos + 72, 118, 18, Component.translatable("gui.hybrid_vx.amalgam_config_gui.maxinput"));
		maxinput.setMaxLength(32767);
		guistate.put("text:maxinput", maxinput);
		this.addWidget(this.maxinput);
		spawnchance = new EditBox(this.font, this.leftPos + 9, this.topPos + 180, 118, 18, Component.translatable("gui.hybrid_vx.amalgam_config_gui.spawnchance"));
		spawnchance.setMaxLength(32767);
		guistate.put("text:spawnchance", spawnchance);
		this.addWidget(this.spawnchance);
		button_done = Button.builder(Component.translatable("gui.hybrid_vx.amalgam_config_gui.button_done"), e -> {
			if (true) {
				HybridVxMod.PACKET_HANDLER.sendToServer(new AmalgamConfigGuiButtonMessage(0, x, y, z));
				AmalgamConfigGuiButtonMessage.handleButtonAction(entity, 0, x, y, z);
			}
		}).bounds(this.leftPos + 245, this.topPos + 215, 46, 20).build();
		guistate.put("button:button_done", button_done);
		this.addRenderableWidget(button_done);
		button_reset = Button.builder(Component.translatable("gui.hybrid_vx.amalgam_config_gui.button_reset"), e -> {
			if (true) {
				HybridVxMod.PACKET_HANDLER.sendToServer(new AmalgamConfigGuiButtonMessage(1, x, y, z));
				AmalgamConfigGuiButtonMessage.handleButtonAction(entity, 1, x, y, z);
			}
		}).bounds(this.leftPos + 190, this.topPos + 215, 51, 20).build();
		guistate.put("button:button_reset", button_reset);
		this.addRenderableWidget(button_reset);
		button_cancel = Button.builder(Component.translatable("gui.hybrid_vx.amalgam_config_gui.button_cancel"), e -> {
			if (true) {
				HybridVxMod.PACKET_HANDLER.sendToServer(new AmalgamConfigGuiButtonMessage(2, x, y, z));
				AmalgamConfigGuiButtonMessage.handleButtonAction(entity, 2, x, y, z);
			}
		}).bounds(this.leftPos + 129, this.topPos + 215, 56, 20).build();
		guistate.put("button:button_cancel", button_cancel);
		this.addRenderableWidget(button_cancel);
		paniccheck = new Checkbox(this.leftPos + 8, this.topPos + 107, 20, 20, Component.translatable("gui.hybrid_vx.amalgam_config_gui.paniccheck"), false);
		guistate.put("checkbox:paniccheck", paniccheck);
		this.addRenderableWidget(paniccheck);
		hallujumpcheck = new Checkbox(this.leftPos + 8, this.topPos + 143, 20, 20, Component.translatable("gui.hybrid_vx.amalgam_config_gui.hallujumpcheck"), false);
		guistate.put("checkbox:hallujumpcheck", hallujumpcheck);
		this.addRenderableWidget(hallujumpcheck);
		chasecheck = new Checkbox(this.leftPos + 157, this.topPos + 35, 20, 20, Component.translatable("gui.hybrid_vx.amalgam_config_gui.chasecheck"), false);
		guistate.put("checkbox:chasecheck", chasecheck);
		this.addRenderableWidget(chasecheck);
		mftfsoundcheck = new Checkbox(this.leftPos + 157, this.topPos + 71, 20, 20, Component.translatable("gui.hybrid_vx.amalgam_config_gui.mftfsoundcheck"), false);
		guistate.put("checkbox:mftfsoundcheck", mftfsoundcheck);
		this.addRenderableWidget(mftfsoundcheck);
	}
}
