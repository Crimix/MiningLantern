package com.black_dog20.mininglantern.capability;

public interface ILanternCapabilityHandler extends IBaseCapability<ILanternCapabilityHandler> {

	void setHasLantern(boolean hasMagnetOn);
	boolean getHasLantern();
	void setHasLanternOn(boolean hasBelt);
	boolean getHasLanternOn();
	
	void markDirty();
}
