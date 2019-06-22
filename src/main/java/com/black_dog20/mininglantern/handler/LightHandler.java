package com.black_dog20.mininglantern.handler;

import java.util.List;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.config.ModConfig;
import com.google.common.base.Predicate;

import elucent.albedo.event.RenderEntityEvent;
import elucent.albedo.lighting.Light;
import elucent.albedo.lighting.LightManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;
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
		if(!ModConfig.others_light) return;
        World world = Minecraft.getMinecraft().world;
        if (world == null) return;
        Entity e = event.getEntity();
        if(!(e instanceof EntityPlayer)) return;
        EntityPlayer player = (EntityPlayer)e;
        EntityPlayerSP theplayer = Minecraft.getMinecraft().player;
        List<Entity> list = theplayer.world.getEntitiesInAABBexcluding(theplayer, getAxisAlignedBB(theplayer, 30), isPlayer());
        ILanternCapabilityHandler lch = LanternCapabilityHandler.instanceFor(player);
        if(!player.equals(theplayer) && !list.contains(player) && lch != null && lch.getHasLantern() && lch.getHasLanternOn()) {
        	Light l = Light.builder().color(255, 255, 204, .005F).pos(player).radius(16F).build();
        	LightManager.lights.add(l);
        }
    }
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent(priority=EventPriority.HIGH)
	public void onRenderWorld(RenderWorldLastEvent event){
		EntityPlayerSP player = Minecraft.getMinecraft().player;
		ILanternCapabilityHandler lch = LanternCapabilityHandler.instanceFor(player);
		if(lch != null && lch.getHasLantern() && lch.getHasLanternOn()) {
        	Light l = Light.builder().color(255, 255, 204, .005F).pos(player).radius(16F).build();
        	LightManager.lights.add(l);
        }
		if(ModConfig.others_light) {
		List<Entity> list = player.world.getEntitiesInAABBexcluding(player, getAxisAlignedBB(player, 30), isPlayer());
		for (Entity entity : list) {
			if(entity instanceof EntityPlayer) {
				ILanternCapabilityHandler lch2 = LanternCapabilityHandler.instanceFor((EntityPlayer)entity);
		        if(lch2 != null && lch2.getHasLantern() && lch2.getHasLanternOn()) {
		        	Light l = Light.builder().color(255, 255, 204, .005F).pos(entity).radius(16F).build();
		        	LightManager.lights.add(l);
		        }
			}
		}
		}
		
	}
	
	private Predicate<Entity> isPlayer(){
		return ((x) -> {return x instanceof EntityPlayer;});
	}
	
	private AxisAlignedBB getAxisAlignedBB(EntityPlayer player, int radius) {
		return new AxisAlignedBB(
				player.posX-radius, 
				player.posY-radius, 
				player.posZ-radius, 
				player.posX+radius, 
				player.posY+radius, 
				player.posZ+radius);
	}
}
