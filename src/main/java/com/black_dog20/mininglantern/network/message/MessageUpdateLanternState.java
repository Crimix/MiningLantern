package com.black_dog20.mininglantern.network.message;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdateLanternState implements IMessage, IMessageHandler<MessageUpdateLanternState, IMessage>{

	public MessageUpdateLanternState() {}
	
	@Override
	public IMessage onMessage(MessageUpdateLanternState message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() -> {
			ILanternCapabilityHandler mh = LanternCapabilityHandler.instanceFor(player);
			if(mh!= null)
				mh.setHasLanternOn(!mh.getHasLanternOn());
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
	}

	@Override
	public void toBytes(ByteBuf buf) {
	}

}
