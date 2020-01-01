package com.black_dog20.mininglantern.network.message;

import java.util.function.Supplier;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageUpdateHasLantern {

	public MessageUpdateHasLantern() {}
	
	private boolean hasLantern = false;
	
	public MessageUpdateHasLantern(boolean hasLantern) {
		this.hasLantern = hasLantern;
	}
	
	public static void onMessage(MessageUpdateHasLantern message, Supplier<NetworkEvent.Context> context) {
		EntityPlayer player = context.get().getSender();
		player.getServer().addScheduledTask(() -> {
			ILanternCapabilityHandler mh = LanternCapabilityHandler.instanceFor(player);
			if(mh!= null)
				mh.setHasLantern(message.hasLantern);
		});
		context.get().setPacketHandled(true);
	}

	public static MessageUpdateHasLantern fromBytes(PacketBuffer buf) {
		return new MessageUpdateHasLantern(buf.readBoolean());
	}

	public void toBytes(PacketBuffer buf) {
		buf.writeBoolean(hasLantern);
	}

}
