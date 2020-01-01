package com.black_dog20.mininglantern.handler;

import java.util.List;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.config.Config;
import com.black_dog20.mininglantern.utility.Helper;

import com.hrznstudio.albedo.event.RenderEntityEvent;
import com.hrznstudio.albedo.lighting.Light;
import com.hrznstudio.albedo.lighting.LightManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class LightHandler {

	private static int playerRange = 30;
	private static float lightRange = 16F;
	
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent
	public void onEntityRender(RenderEntityEvent event) {
		if(!Config.others_light) return;
		World world = Minecraft.getInstance().world;
		if (world == null) return;
		Entity e = event.getEntity();
		if(!(e instanceof EntityPlayer)) return;
		EntityPlayer player = (EntityPlayer)e;
		EntityPlayerSP theplayer = Minecraft.getInstance().player;
		List<EntityPlayer> list = Helper.getPlayersInRadius(player, playerRange);
		ILanternCapabilityHandler lch = LanternCapabilityHandler.instanceFor(player);
		if(!player.equals(theplayer) && !list.contains(player) && lch != null && lch.getHasLantern() && lch.getHasLanternOn()) {
			Light l = Light.builder().color(255, 255, 204, .005F).pos(player).radius(lightRange).build();
			LightManager.lights.add(l);
		}
	}

	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(priority=EventPriority.HIGH)
	public void onRenderWorld(RenderWorldLastEvent event){
		EntityPlayerSP player = Minecraft.getInstance().player;
		ILanternCapabilityHandler lch = LanternCapabilityHandler.instanceFor(player);
		if(lch != null && lch.getHasLantern() && lch.getHasLanternOn()) {
			Light l = Light.builder().color(255, 255, 204, .005F).pos(player).radius(lightRange).build();
			LightManager.lights.add(l);
		}
		if(Config.others_light) {
			List<EntityPlayer> list = Helper.getPlayersInRadius(player, playerRange);
			for (EntityPlayer entity : list) {
				ILanternCapabilityHandler lch2 = LanternCapabilityHandler.instanceFor((EntityPlayer)entity);
				if(lch2 != null && lch2.getHasLantern() && lch2.getHasLanternOn()) {
					Light l = Light.builder().color(255, 255, 204, .005F).pos(entity).radius(lightRange).build();
					LightManager.lights.add(l);
				}
			}
		}

	}


}
