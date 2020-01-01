package com.black_dog20.mininglantern.handler;

import java.util.function.Supplier;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.network.PacketHandler;
import com.black_dog20.mininglantern.network.message.MessageSyncLanternCapability;
import com.black_dog20.mininglantern.reference.Reference;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.PacketDistributor.PacketTarget;

public class CapabilityHandler {

	@SubscribeEvent 
	public void addPlayerCapabilities(AttachCapabilitiesEvent<Entity> entity) {
		if(entity.getObject() instanceof EntityPlayer){
			entity.addCapability(new ResourceLocation(Reference.MOD_ID, "LanternCapabilityHandler"), new LanternCapabilityHandler());
		}
	}

	@SubscribeEvent 
	public void persistPlayerCapabilities(PlayerEvent.Clone e) {
			ILanternCapabilityHandler newCap = e.getEntityPlayer().getCapability(LanternCapabilityHandler.CAP, null).orElse(null);
			ILanternCapabilityHandler oldCap = e.getOriginal().getCapability(LanternCapabilityHandler.CAP, null).orElse(null);

			if(oldCap != null)
				oldCap.copyTo(newCap);
	}
	
	@SubscribeEvent 
	public void OnPlayerStartTrackingPlayer(PlayerEvent.StartTracking event){
		if(event.getTarget().world.isRemote) return;
		if(event.getTarget() instanceof EntityPlayer){
			EntityPlayer trackedPlayer = (EntityPlayer) event.getTarget();
			EntityPlayer trackingPlayer = event.getEntityPlayer();
			PacketTarget dist = PacketDistributor.PLAYER.with((Supplier<EntityPlayerMP>) trackingPlayer);
			PacketHandler.network.send(dist, new MessageSyncLanternCapability(LanternCapabilityHandler.instanceFor(trackedPlayer)));
		}
	}
	
	@SubscribeEvent
	public void OnPlayerCapabilityUpdate(LivingUpdateEvent event) {
		if (event.getEntity() instanceof EntityPlayer) {
			EntityPlayer player = (EntityPlayer) event.getEntity();
			if(!player.world.isRemote) {
				ILanternCapabilityHandler mh = player.getCapability(LanternCapabilityHandler.CAP, null).orElse(null);
				if(mh != null){
					mh.updateClient(player);
				}
			}
		}
	}
}
