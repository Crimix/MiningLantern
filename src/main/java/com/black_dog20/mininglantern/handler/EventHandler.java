package com.black_dog20.mininglantern.handler;

import java.util.List;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.reference.Reference;
import com.black_dog20.mininglantern.utility.Helper;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event.Result;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = Reference.MOD_ID)
public class EventHandler {

	private static int range = 8;
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onEntityCheckSpawn(LivingSpawnEvent.CheckSpawn event)
	{
		if(event.getResult() == Result.ALLOW) {
			if(event.isSpawner()) return;
			List<EntityPlayer> list = Helper.getPlayersInRadius(event.getEntity(), range);
			for (EntityPlayer entityPlayer : list) {
				ILanternCapabilityHandler lch = LanternCapabilityHandler.instanceFor(entityPlayer);
				if(lch != null && lch.getHasLantern() && lch.getHasLanternOn()) {
					event.setResult(Result.DENY);
				}
			}
		}
	}	
}
