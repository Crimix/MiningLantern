package com.black_dog20.mininglantern.common.capability;

public interface ILanternCapabilityHandler extends IBaseCapability<ILanternCapabilityHandler> {

	void setHasLantern(boolean hasLantern);
	boolean getHasLantern();
	void setHasLanternOn(boolean hasLanternOn);
	boolean getHasLanternOn();
	
	void markDirty();
}
