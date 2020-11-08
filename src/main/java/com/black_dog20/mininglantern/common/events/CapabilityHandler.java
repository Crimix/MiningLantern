package com.black_dog20.mininglantern.common.events;

import com.black_dog20.mininglantern.MiningLantern;
import com.black_dog20.mininglantern.common.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.common.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.common.network.PacketHandler;
import com.black_dog20.mininglantern.common.network.message.MessageSyncLanternCapability;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.network.PacketDistributor;

@Mod.EventBusSubscriber(modid = MiningLantern.MOD_ID)
public class CapabilityHandler {

	@SubscribeEvent
	public static void addPlayerCapabilities(AttachCapabilitiesEvent<Entity> entity) {
		if(entity.getObject() instanceof PlayerEntity){
			entity.addCapability(new ResourceLocation(MiningLantern.MOD_ID, "lantern_capability_handler"), new LanternCapabilityHandler());
		}
	}

	@SubscribeEvent 
	public static void persistPlayerCapabilities(PlayerEvent.Clone e) {
			ILanternCapabilityHandler newCap = e.getPlayer().getCapability(LanternCapabilityHandler.CAP, null).orElse(null);
			ILanternCapabilityHandler oldCap = e.getOriginal().getCapability(LanternCapabilityHandler.CAP, null).orElse(null);

			if(oldCap != null && newCap != null)
				oldCap.copyTo(newCap);
	}
	
	@SubscribeEvent 
	public static void onPlayerStartTrackingPlayer(PlayerEvent.StartTracking event){
		if(event.getTarget().world.isRemote) return;
		if(event.getTarget() instanceof PlayerEntity){
			PlayerEntity trackedPlayer = (PlayerEntity) event.getTarget();
			PlayerEntity trackingPlayer = event.getPlayer();
			PacketHandler.NETWORK.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) trackingPlayer), new MessageSyncLanternCapability(trackedPlayer, LanternCapabilityHandler.instanceFor(trackedPlayer)));
		}
	}
	
	@SubscribeEvent
	public static void OnPlayerCapabilityUpdate(LivingUpdateEvent event) {
		if (event.getEntity() instanceof PlayerEntity) {
			PlayerEntity player = (PlayerEntity) event.getEntity();
			if(!player.world.isRemote) {
				ILanternCapabilityHandler mh = player.getCapability(LanternCapabilityHandler.CAP, null).orElse(null);
				if(mh != null){
					mh.updateClient(player);
				}
			}
		}
	}
}
