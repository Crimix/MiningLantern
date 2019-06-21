package com.black_dog20.mininglantern.event;

import elucent.albedo.event.RenderEntityEvent;
import elucent.albedo.lighting.Light;
import elucent.albedo.lighting.LightManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class LightHandler {

	@SideOnly(Side.CLIENT)
	@SubscribeEvent
    public void onEntityRender(RenderEntityEvent event) {
        World world = Minecraft.getMinecraft().world;
        if (world == null) return;
        Entity e = event.getEntity();
        if(!(e instanceof EntityPlayer)) return;

        if(!e.equals(Minecraft.getMinecraft().player)) {
        	Light l = Light.builder().color(255, 255, 204, .005F).pos(e).radius(16F).build();
        	LightManager.lights.add(l);
        }
    }
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.HIGH)
	public void onRenderWorld(RenderWorldLastEvent event){
		EntityPlayerSP player = Minecraft.getMinecraft().player;
    	Light l = Light.builder().color(255, 255, 204, .005F).pos(player).radius(16F).build();
    	LightManager.lights.add(l);
	}
}
