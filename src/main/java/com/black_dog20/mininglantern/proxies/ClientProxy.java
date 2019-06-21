package com.black_dog20.mininglantern.proxies;

import com.black_dog20.mininglantern.client.render.LanternRender;
import com.black_dog20.mininglantern.client.settings.Keybindings;
import com.black_dog20.mininglantern.handler.KeyInputEventHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.MessageContext;
import net.minecraftforge.fml.relauncher.Side;

public class ClientProxy extends CommonProxy {

	
	@Override
	public void registerRendersPreInit() {
	}
	
	@Override
	public void registerRendersInit() {
		((RenderPlayer)Minecraft.getMinecraft().getRenderManager().getSkinMap().get("default")).addLayer(new LanternRender());
		((RenderPlayer)Minecraft.getMinecraft().getRenderManager().getSkinMap().get("slim")).addLayer(new LanternRender());
	}
	
	@Override
	public void registerKeyBindings() {
		ClientRegistry.registerKeyBinding(Keybindings.TOGGLE_LIGHT);
	}

	@Override
	public void registerKeyInputHandler() {
		MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
	}
	
	@Override
	public EntityPlayer getPlayerFromMessageContext(MessageContext ctx) {
		switch (ctx.side) {
		case CLIENT:
			EntityPlayer entityClientPlayerMP = Minecraft.getMinecraft().player;
			return entityClientPlayerMP;
		case SERVER:
			EntityPlayer entityPlayerMP = ctx.getServerHandler().player;
			return entityPlayerMP;
		}
		return null;
	}

	@Override
	public EntityPlayer getPlayerByIDFromMessageContext(int id, MessageContext ctx) {
		if (ctx.side == Side.CLIENT) {
			EntityPlayer entityClientPlayer = (EntityPlayer) Minecraft.getMinecraft().world.getEntityByID(id);
			return entityClientPlayer;
		}
		return null;
	}

}
