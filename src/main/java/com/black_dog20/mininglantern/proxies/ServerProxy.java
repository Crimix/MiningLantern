package com.black_dog20.mininglantern.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;

public class ServerProxy extends CommonProxy {
	
	@Override
	public void registerRendersPreInit() {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void registerRendersInit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void registerKeyBindings() {
		// TODO Auto-generated method stub
	}

	@Override
	public boolean isSinglePlayer() {
		return false;
	}
	
	@Override
	public EntityPlayer getPlayerFromMessageContext(MessageContext ctx) {
		return ctx.getServerHandler().player;
	}

	@Override
	public EntityPlayer getPlayerByIDFromMessageContext(int id, MessageContext ctx) {
		// TODO Auto-generated method stub
		return null;
	}
}
