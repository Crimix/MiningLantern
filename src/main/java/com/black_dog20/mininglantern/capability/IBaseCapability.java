package com.black_dog20.mininglantern.capability;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;

public interface IBaseCapability<T> {

	void copyTo(T other);
	void updateClient(EntityPlayer player);
	
	void readFromNBT(NBTTagCompound nbt);
	NBTTagCompound writeToNBT();
}
