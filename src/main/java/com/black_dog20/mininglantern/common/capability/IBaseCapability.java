package com.black_dog20.mininglantern.common.capability;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;

public interface IBaseCapability<T> {

	void copyTo(T other);
	void updateClient(PlayerEntity player);
	
	void readFromNBT(CompoundNBT nbt);
	CompoundNBT writeToNBT();
}
