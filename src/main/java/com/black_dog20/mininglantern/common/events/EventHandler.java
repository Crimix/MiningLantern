package com.black_dog20.mininglantern.common.events;

import com.black_dog20.mininglantern.MiningLantern;
import com.black_dog20.mininglantern.common.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.common.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.common.utility.Helper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraftforge.event.entity.living.LivingSpawnEvent;
import net.minecraftforge.eventbus.api.Event;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

import java.util.List;

@EventBusSubscriber(modid = MiningLantern.MOD_ID)
public class EventHandler {

	private static int range = 8;
	
	@SubscribeEvent(priority = EventPriority.LOWEST)
	public static void onEntityCheckSpawn(LivingSpawnEvent.CheckSpawn event)
	{
		if(event.getResult() == Event.Result.ALLOW) {
			if(event.isSpawner()) return;
			List<PlayerEntity> list = Helper.getPlayersInRadius(event.getEntity(), range);
			for (PlayerEntity entityPlayer : list) {
				ILanternCapabilityHandler lch = LanternCapabilityHandler.instanceFor(entityPlayer);
				if(lch != null && lch.getHasLantern() && lch.getHasLanternOn()) {
					event.setResult(Event.Result.DENY);
				}
			}
		}
	}	
}
