package com.black_dog20.mininglantern.capability;

import java.util.concurrent.Callable;

import com.black_dog20.mininglantern.network.PacketHandler;
import com.black_dog20.mininglantern.network.message.MessageSyncLanternCapability;
import com.black_dog20.mininglantern.network.message.MessageSyncLanternCapabilityTracking;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.WorldServer;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;

public class LanternCapabilityHandler implements ILanternCapabilityHandler, ICapabilitySerializable<NBTTagCompound> {

	public static final int SIZE = 29;
	
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
	public void setHasLantern(boolean hasMagnetOn) {
		hasLantern = hasMagnetOn;
		dirty = true;
	}
	
	@Override
	public boolean getHasLantern() {
		return hasLantern;
	}
	
	@Override
	public void setHasLanternOn(boolean hasMagnetOn) {
		lanternOn = hasMagnetOn;
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
			PacketHandler.network.sendTo(new MessageSyncLanternCapability(this), (EntityPlayerMP) player);
			((WorldServer) player.world).getEntityTracker().sendToTracking(player, PacketHandler.network.getPacketFrom(new MessageSyncLanternCapabilityTracking(this, player)));
			dirty = false;
		}
	}
	
	public static ILanternCapabilityHandler instanceFor(EntityPlayer player) { 
		return player.getCapability(CAP, null); 
	}

	
	
	@Override
	public boolean hasCapability(Capability<?> capability, EnumFacing facing) {
		return capability == CAP;
	}

	@Override
	public <T> T getCapability(Capability<T> capability, EnumFacing facing) {
		return capability == CAP ? CAP.<T> cast(this) : null;

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
