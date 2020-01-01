package com.black_dog20.mininglantern.capability;

import java.util.concurrent.Callable;
import java.util.function.Supplier;

import com.black_dog20.mininglantern.network.PacketHandler;
import com.black_dog20.mininglantern.network.message.MessageSyncLanternCapability;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.PacketDistributor.PacketTarget;

public class LanternCapabilityHandler implements ILanternCapabilityHandler, ICapabilitySerializable<NBTTagCompound> {
	
	@CapabilityInject(ILanternCapabilityHandler.class) 
	public static final Capability<ILanternCapabilityHandler> CAP = null;
	private boolean hasLantern = false;
	private boolean lanternOn = false;
	private boolean dirty = false;
	
	@Override
	public void markDirty() {
		dirty = true;
	}
	
	@Override
	public void setHasLantern(boolean hasLantern) {
		this.hasLantern = hasLantern;
		dirty = true;
	}
	
	@Override
	public boolean getHasLantern() {
		return hasLantern;
	}
	
	@Override
	public void setHasLanternOn(boolean hasLanternOn) {
		lanternOn = hasLanternOn;
		dirty = true;
	}

	@Override
	public boolean getHasLanternOn() {
		return lanternOn;
	}

	@Override
	public void copyTo(ILanternCapabilityHandler other) {
		other.setHasLantern(hasLantern);
		other.setHasLanternOn(lanternOn);
	}
	
	@Override
	public void updateClient(EntityPlayer player){
		if(!player.world.isRemote && dirty){
			
			
			PacketTarget dist = PacketDistributor.TRACKING_ENTITY_AND_SELF.with((Supplier<Entity>) player);
			PacketHandler.network.send(dist, new MessageSyncLanternCapability(this));
			dirty = false;
		}
	}
	
	public static ILanternCapabilityHandler instanceFor(EntityPlayer player) { 
		return player.getCapability(CAP, null).orElse(null); 
	}

	

	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, EnumFacing facing) {
		return CAP.orEmpty(capability, LazyOptional.of(() -> this));
	}

	@Override
	public NBTTagCompound serializeNBT() {
		return (NBTTagCompound) CAP.getStorage().writeNBT(CAP, this, null);
	}

	@Override
	public void deserializeNBT(NBTTagCompound nbt) {
		CAP.getStorage().readNBT(CAP, this, null, nbt);
		this.dirty = true;
	}
	
	public static final class Factory implements Callable<LanternCapabilityHandler>
	{
		  @Override
		  public LanternCapabilityHandler call() throws Exception
		  {
		    return new LanternCapabilityHandler();
		  }
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		hasLantern = nbt.getBoolean("hasLantern");
		lanternOn = nbt.getBoolean("lanternOn");
	}

	@Override
	public NBTTagCompound writeToNBT() {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean("hasLantern", hasLantern);
		nbt.setBoolean("lanternOn", lanternOn);
		
		return nbt;
	}


}
