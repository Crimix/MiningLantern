package com.black_dog20.mininglantern.network.message;

import java.util.function.Supplier;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageUpdateLanternState{

	public MessageUpdateLanternState() {}
	
	public static void onMessage(MessageUpdateLanternState message, Supplier<NetworkEvent.Context> context) {
		EntityPlayer player = context.get().getSender();
		player.getServer().addScheduledTask(() -> {
			ILanternCapabilityHandler mh = LanternCapabilityHandler.instanceFor(player);
			if(mh!= null)
				mh.setHasLanternOn(!mh.getHasLanternOn());
		});
		context.get().setPacketHandled(true);
	}

	public static MessageUpdateLanternState fromBytes(PacketBuffer buf) {
		return new MessageUpdateLanternState();
	}

	public void toBytes(PacketBuffer buf) {
	}

}
