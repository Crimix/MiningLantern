package com.black_dog20.mininglantern.network.message;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;

import io.netty.buffer.ByteBuf;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.IMessage;
import net.minecraftforge.fml.common.network.simpleimpl.IMessageHandler;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class MessageUpdateHasLantern implements IMessage, IMessageHandler<MessageUpdateHasLantern, IMessage>{

	public MessageUpdateHasLantern() {}
	
	private boolean hasLantern = false;
	
	public MessageUpdateHasLantern(boolean hasLantern) {
		this.hasLantern = hasLantern;
	}
	
	@Override
	public IMessage onMessage(MessageUpdateHasLantern message, MessageContext ctx) {
		EntityPlayer player = ctx.getServerHandler().player;
		player.getServer().addScheduledTask(() -> {
			ILanternCapabilityHandler mh = LanternCapabilityHandler.instanceFor(player);
			if(mh!= null)
				mh.setHasLantern(message.hasLantern);
		});
		return null;
	}

	@Override
	public void fromBytes(ByteBuf buf) {
		hasLantern = buf.readBoolean();
	}

	@Override
	public void toBytes(ByteBuf buf) {
		buf.writeBoolean(hasLantern);
	}

}
