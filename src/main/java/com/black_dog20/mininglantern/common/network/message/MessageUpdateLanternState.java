package com.black_dog20.mininglantern.common.network.message;

import com.black_dog20.mininglantern.common.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.common.capability.LanternCapabilityHandler;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class MessageUpdateLanternState {

	public MessageUpdateLanternState() {}

	public static void encode(MessageUpdateLanternState msg, PacketBuffer buffer) {
	}

	public static MessageUpdateLanternState decode(PacketBuffer buffer) {
		return new MessageUpdateLanternState();
	}

	public static class Handler {
		public static void handle(MessageUpdateLanternState msg, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> {
				ServerPlayerEntity player = ctx.get().getSender();
				if (player == null)
					return;
				ILanternCapabilityHandler mh = LanternCapabilityHandler.instanceFor(player);
				if(mh!= null)
					mh.setHasLanternOn(!mh.getHasLanternOn());
			});

			ctx.get().setPacketHandled(true);
		}
	}

}
