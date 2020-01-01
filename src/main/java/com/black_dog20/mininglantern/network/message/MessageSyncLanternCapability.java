package com.black_dog20.mininglantern.network.message;

import java.util.function.Supplier;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

public class MessageSyncLanternCapability {

	public NBTTagCompound nbt;
	
	public MessageSyncLanternCapability() {}
	
	public MessageSyncLanternCapability(ILanternCapabilityHandler mh) {
		nbt = mh.writeToNBT();
	}
	
	
	public static void onMessage(MessageSyncLanternCapability message, Supplier<NetworkEvent.Context> context) {
		Minecraft.getInstance().addScheduledTask(() -> {
			EntityPlayer player = context.get().getSender();
			ILanternCapabilityHandler mh = LanternCapabilityHandler.instanceFor(player);
			if(mh != null){
				mh.readFromNBT(message.nbt);
			}
		});
		context.get().setPacketHandled(true);
	}

	public static MessageSyncLanternCapability fromBytes(PacketBuffer buf) {
		MessageSyncLanternCapability res = new MessageSyncLanternCapability();
		res.nbt = buf.readCompoundTag();
		return res;
		
	}

	public void toBytes(PacketBuffer buf) {
		buf.writeCompoundTag(nbt);
	}

}
