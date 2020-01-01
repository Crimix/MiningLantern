package com.black_dog20.mininglantern.proxies;

public interface IProxy {
	
	void registerRendersPreInit();

	void registerKeyBindings();
	
	void registerKeyInputHandler();

	void registerRendersInit();
}
