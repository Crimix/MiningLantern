package com.black_dog20.mininglantern;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityStorage;
import com.black_dog20.mininglantern.compatibility.Compatibility;
import com.black_dog20.mininglantern.handler.CapabilityHandler;
import com.black_dog20.mininglantern.handler.LightHandler;
import com.black_dog20.mininglantern.network.PacketHandler;
import com.black_dog20.mininglantern.proxies.IProxy;
import com.black_dog20.mininglantern.reference.Reference;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, version = Reference.VERSION, acceptedMinecraftVersions = Reference.MC_VERSIONS, dependencies = Reference.DEPENDENCIES)
public class MiningLantern {

	@Mod.Instance(Reference.MOD_ID)
	public static MiningLantern instance = new MiningLantern();
	public static Logger logger;
	public static boolean modOnServer = false;

	@SidedProxy(clientSide = Reference.CLIENT_PROXY_CLASS, serverSide = Reference.SERVER_PROXY_CLASS)
	public static IProxy Proxy;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		logger = event.getModLog();
		PacketHandler.init();
		Proxy.registerKeyBindings();
		Proxy.registerRendersPreInit();
		PacketHandler.init();
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		logger.info("Pre Initialization Complete!");
	}

	@Mod.EventHandler
	public void init(FMLInitializationEvent event) {
		Proxy.registerKeyInputHandler();
		CapabilityManager.INSTANCE.register(ILanternCapabilityHandler.class, new LanternCapabilityStorage(), new LanternCapabilityHandler.Factory());
		Proxy.registerRendersInit();
		logger.info("Initialization Complete!");
}

	@Mod.EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		logger.info("Post Initialization Complete!");
		if(Loader.isModLoaded("albedo"))
			MinecraftForge.EVENT_BUS.register(new LightHandler());
		Compatibility.init();
	}
}
