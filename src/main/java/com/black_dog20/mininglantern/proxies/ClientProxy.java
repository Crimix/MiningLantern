package com.black_dog20.mininglantern.proxies;

import com.black_dog20.mininglantern.client.render.LanternRender;
import com.black_dog20.mininglantern.client.settings.Keybindings;
import com.black_dog20.mininglantern.handler.KeyInputEventHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.client.registry.ClientRegistry;

public class ClientProxy extends CommonProxy {

	
	@Override
	public void registerRendersPreInit() {
	}
	
	@Override
	public void registerRendersInit() {
		((RenderPlayer)Minecraft.getInstance().getRenderManager().getSkinMap().get("default")).addLayer(new LanternRender());
		((RenderPlayer)Minecraft.getInstance().getRenderManager().getSkinMap().get("slim")).addLayer(new LanternRender());
	}
	
	@Override
	public void registerKeyBindings() {
		ClientRegistry.registerKeyBinding(Keybindings.TOGGLE_LIGHT);
	}

	@Override
	public void registerKeyInputHandler() {
		MinecraftForge.EVENT_BUS.register(new KeyInputEventHandler());
	}

}
