package com.black_dog20.mininglantern.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public interface IProxy {
	
	void registerRendersPreInit();

	void registerKeyBindings();
	
	void registerKeyInputHandler();

	EntityPlayer getPlayerFromMessageContext(MessageContext ctx);

	EntityPlayer getPlayerByIDFromMessageContext(int id, MessageContext ctx);

	void registerRendersInit();
}
