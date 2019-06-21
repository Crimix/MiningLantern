package com.black_dog20.mininglantern.network.message;

import com.black_dog20.mininglantern.MiningLantern;
import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraftforge.fml.common.network.ByteBufUtils;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageSyncLanternCapability implements IMessage, IMessageHandler<MessageSyncLanternCapability, IMessage>{

	private NBTTagCompound nbt;
	
	public MessageSyncLanternCapability() {}
	
	public MessageSyncLanternCapability(ILanternCapabilityHandler mh) {
		nbt = mh.writeToNBT();
	}
	
	
	@Override
	public IMessage onMessage(MessageSyncLanternCapability message, MessageContext ctx) {
		Minecraft.getMinecraft().addScheduledTask(() -> {
			EntityPlayer player = MiningLantern.Proxy.getPlayerFromMessageContext(ctx);
			ILanternCapabilityHandler mh = LanternCapabilityHandler.instanceFor(player);
			if(mh != null){
				mh.readFromNBT(message.nbt);
			}
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		nbt = ByteBufUtils.readTag(buf);
		
	}

	@Override
	public void toBytes(ByteBuf buf) {
		ByteBufUtils.writeTag(buf, nbt);
	}

}
