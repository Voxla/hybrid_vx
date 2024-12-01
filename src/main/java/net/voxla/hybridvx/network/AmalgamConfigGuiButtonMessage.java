
package net.voxla.hybridvx.network;

import net.voxla.hybridvx.world.inventory.AmalgamConfigGuiMenu;
import net.voxla.hybridvx.procedures.AmalgamConfigResetButtonProcedure;
import net.voxla.hybridvx.procedures.AmalgamConfigDoneButtonProcedure;
import net.voxla.hybridvx.procedures.AmalgamConfigCancelButtonProcedure;
import net.voxla.hybridvx.HybridVxMod;

import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import net.minecraft.world.level.Level;
import net.minecraft.world.entity.player.Player;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.core.BlockPos;

import java.util.function.Supplier;
import java.util.HashMap;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class AmalgamConfigGuiButtonMessage {
	private final int buttonID, x, y, z;

	public AmalgamConfigGuiButtonMessage(FriendlyByteBuf buffer) {
		this.buttonID = buffer.readInt();
		this.x = buffer.readInt();
		this.y = buffer.readInt();
		this.z = buffer.readInt();
	}

	public AmalgamConfigGuiButtonMessage(int buttonID, int x, int y, int z) {
		this.buttonID = buttonID;
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public static void buffer(AmalgamConfigGuiButtonMessage message, FriendlyByteBuf buffer) {
		buffer.writeInt(message.buttonID);
		buffer.writeInt(message.x);
		buffer.writeInt(message.y);
		buffer.writeInt(message.z);
	}

	public static void handler(AmalgamConfigGuiButtonMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
		NetworkEvent.Context context = contextSupplier.get();
		context.enqueueWork(() -> {
			Player entity = context.getSender();
			int buttonID = message.buttonID;
			int x = message.x;
			int y = message.y;
			int z = message.z;
			handleButtonAction(entity, buttonID, x, y, z);
		});
		context.setPacketHandled(true);
	}

	public static void handleButtonAction(Player entity, int buttonID, int x, int y, int z) {
		Level world = entity.level;
		HashMap guistate = AmalgamConfigGuiMenu.guistate;
		// security measure to prevent arbitrary chunk generation
		if (!world.hasChunkAt(new BlockPos(x, y, z)))
			return;
		if (buttonID == 0) {

			AmalgamConfigDoneButtonProcedure.execute(entity, guistate);
		}
		if (buttonID == 1) {

			AmalgamConfigResetButtonProcedure.execute(entity);
		}
		if (buttonID == 2) {

			AmalgamConfigCancelButtonProcedure.execute(entity);
		}
	}

	@SubscribeEvent
	public static void registerMessage(FMLCommonSetupEvent event) {
		HybridVxMod.addNetworkMessage(AmalgamConfigGuiButtonMessage.class, AmalgamConfigGuiButtonMessage::buffer, AmalgamConfigGuiButtonMessage::new, AmalgamConfigGuiButtonMessage::handler);
	}
}
