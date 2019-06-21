package com.black_dog20.mininglantern.capability;

import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.Capability.IStorage;

public class LanternCapabilityStorage implements IStorage<ILanternCapabilityHandler>{
	
	public NBTTagCompound writeNBT(Capability<ILanternCapabilityHandler> capability, ILanternCapabilityHandler instance, EnumFacing side) {
		NBTTagCompound nbt = new NBTTagCompound();
		nbt.setBoolean("hasLantern", instance.getHasLantern());
		nbt.setBoolean("lanternOn", instance.getHasLanternOn());
		
		return nbt;
	}

	public void readNBT(Capability<ILanternCapabilityHandler> capability, ILanternCapabilityHandler instance, EnumFacing side, NBTBase nbt) {
		instance.setHasLantern(((NBTTagCompound) nbt).getBoolean("hasLantern"));
		instance.setHasLanternOn(((NBTTagCompound) nbt).getBoolean("lanternOn"));
		
	}

}
