package com.black_dog20.mininglantern.network.message;

import com.black_dog20.mininglantern.MiningLantern;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncLanternCapabilityTracking implements IMessage, IMessageHandler<MessageSyncLanternCapabilityTracking, IMessage>{

	private NBTTagCompound nbt;
	private int id;
	
	public MessageSyncLanternCapabilityTracking() {}
	
	public MessageSyncLanternCapabilityTracking(ILanternCapabilityHandler mh, EntityPlayer player) {
		nbt = mh.writeToNBT();
		id = player.getEntityId();
	}
	
	
	@Override
	public IMessage onMessage(MessageSyncLanternCapabilityTracking message, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(() -> {
			EntityPlayer playerTrack = MiningLantern.Proxy.getPlayerByIDFromMessageContext(message.id, ctx);
			ILanternCapabilityHandler mh = LanternCapabilityHandler.instanceFor(playerTrack);
			if(mh != null){
				mh.readFromNBT(message.nbt);
			}
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		id = buf.readInt();
		nbt = ByteBufUtils.readTag(buf);
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeInt(id);
		ByteBufUtils.writeTag(buf, nbt);
	}

}
