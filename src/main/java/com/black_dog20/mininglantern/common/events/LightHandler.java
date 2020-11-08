package com.black_dog20.mininglantern.common.events;

import com.black_dog20.mininglantern.Config;
import com.black_dog20.mininglantern.MiningLantern;
import com.black_dog20.mininglantern.common.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.common.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.common.utility.Helper;
import net.hypherionmc.hypcore.api.Light;
import net.hypherionmc.hypcore.event.RenderEntityEvent;
import net.hypherionmc.hypcore.lighting.LightManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MiningLantern.MOD_ID)
public class LightHandler {

	private static int playerRange = 30;
	private static float lightRange = 8F;

	@SubscribeEvent
	public static void onEntityRender(RenderEntityEvent event) {
		if(!Config.OTHER_LIGHTS.get()) return;
		World world = Minecraft.getInstance().world;
		if (world == null) return;
		Entity e = event.getEntity();
		if(!(e instanceof PlayerEntity)) return;
		PlayerEntity player = (PlayerEntity)e;
		ClientPlayerEntity theplayer = Minecraft.getInstance().player;
		List<PlayerEntity> list = Helper.getPlayersInRadius(player, playerRange);
		ILanternCapabilityHandler lch = LanternCapabilityHandler.instanceFor(player);
		if(!player.equals(theplayer) && !list.contains(player) && lch != null && lch.getHasLantern() && lch.getHasLanternOn()) {
			Light l = Light.builder().color(255, 255, 204, .005F).pos(player).radius(lightRange).build();
			LightManager.lights.add(l);
		}
	}

	@SubscribeEvent(priority= EventPriority.HIGH)
	public static void onRenderWorld(RenderWorldLastEvent event){
		PlayerEntity player = Minecraft.getInstance().player;
		ILanternCapabilityHandler lch = LanternCapabilityHandler.instanceFor(player);
		if(lch != null && lch.getHasLantern() && lch.getHasLanternOn()) {
			Light l = Light.builder().color(255, 255, 204, .005F).pos(player).radius(lightRange).build();
			LightManager.lights.add(l);
		}
		if(Config.OTHER_LIGHTS.get()) {
			List<PlayerEntity> list = Helper.getPlayersInRadius(player, playerRange);
			for (PlayerEntity entity : list) {
				ILanternCapabilityHandler lch2 = LanternCapabilityHandler.instanceFor(entity);
				if(lch2 != null && lch2.getHasLantern() && lch2.getHasLanternOn()) {
					Light l = Light.builder().color(255, 255, 204, .005F).pos(entity).radius(lightRange).build();
					LightManager.lights.add(l);
				}
			}
		}

	}


}
