package com.black_dog20.mininglantern.common.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class LanternCapabilityStorage implements IStorage<ILanternCapabilityHandler>{
	
	public INBT writeNBT(Capability<ILanternCapabilityHandler> capability, ILanternCapabilityHandler instance, Direction side) {
		CompoundNBT nbt = new CompoundNBT();
		nbt.putBoolean("hasLantern", instance.getHasLantern());
		nbt.putBoolean("lanternOn", instance.getHasLanternOn());
		
		return nbt;
	}

	public void readNBT(Capability<ILanternCapabilityHandler> capability, ILanternCapabilityHandler instance, Direction side, INBT nbt) {
		instance.setHasLantern(((CompoundNBT) nbt).getBoolean("hasLantern"));
		instance.setHasLanternOn(((CompoundNBT) nbt).getBoolean("lanternOn"));
		
	}

}
