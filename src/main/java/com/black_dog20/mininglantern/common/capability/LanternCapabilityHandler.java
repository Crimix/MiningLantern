package com.black_dog20.mininglantern.common.capability;

import com.black_dog20.mininglantern.common.network.PacketHandler;
import com.black_dog20.mininglantern.common.network.message.MessageSyncLanternCapability;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.concurrent.Callable;

public class LanternCapabilityHandler implements ILanternCapabilityHandler, ICapabilitySerializable<CompoundNBT> {

	public static final int SIZE = 29;
	
	@CapabilityInject(ILanternCapabilityHandler.class) 
	public static final Capability<ILanternCapabilityHandler> CAP = null;
	private LazyOptional<ILanternCapabilityHandler> instance = LazyOptional.of(() -> CAP.getDefaultInstance());
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
	public void updateClient(PlayerEntity player){
		if(!player.world.isRemote && dirty){
			PacketHandler.NETWORK.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), new MessageSyncLanternCapability(player, this));
			dirty = false;
		}
	}
	
	public static ILanternCapabilityHandler instanceFor(PlayerEntity player) {
		return player.getCapability(CAP, null)
				.orElse(null);
	}


	@Override
	public <T> LazyOptional<T> getCapability(Capability<T> capability, Direction facing) {
		return CAP.orEmpty(capability, instance);
	}

	@Override
	public CompoundNBT serializeNBT() {
		return (CompoundNBT) CAP.getStorage().writeNBT(CAP, this, null);
	}

	@Override
	public void deserializeNBT(CompoundNBT nbt) {
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
	public void readFromNBT(CompoundNBT nbt) {
		hasLantern = nbt.getBoolean("hasLantern");
		lanternOn = nbt.getBoolean("lanternOn");
	}

	@Override
	public CompoundNBT writeToNBT() {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putBoolean("hasLantern", hasLantern);
		nbt.putBoolean("lanternOn", lanternOn);
		
		return nbt;
	}


}
