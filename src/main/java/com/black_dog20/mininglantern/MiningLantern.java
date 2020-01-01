package com.black_dog20.mininglantern;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityStorage;
import com.black_dog20.mininglantern.config.Config;
import com.black_dog20.mininglantern.handler.CapabilityHandler;
import com.black_dog20.mininglantern.handler.LightHandler;
import com.black_dog20.mininglantern.network.PacketHandler;
import com.black_dog20.mininglantern.proxies.ClientProxy;
import com.black_dog20.mininglantern.proxies.IProxy;
import com.black_dog20.mininglantern.proxies.ServerProxy;
import com.black_dog20.mininglantern.reference.Reference;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Reference.MOD_ID)
public class MiningLantern {

	public static MiningLantern instance;
	public static final Logger LOGGER = LogManager.getLogger();
	public static IProxy Proxy;
	
	public MiningLantern() {
		instance = this;
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::doClientStuff);
        Proxy = DistExecutor.runForDist(()->()->new ClientProxy(), ()->()->new ServerProxy());
        
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::config);
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.SPEC);
        
        MinecraftForge.EVENT_BUS.register(this);
    }

	private void setup(FMLCommonSetupEvent event) {
		PacketHandler.init();
		MinecraftForge.EVENT_BUS.register(new CapabilityHandler());
		CapabilityManager.INSTANCE.register(ILanternCapabilityHandler.class, new LanternCapabilityStorage(), new LanternCapabilityHandler.Factory());
		if(ModList.get().isLoaded("albedo"))
			MinecraftForge.EVENT_BUS.register(new LightHandler());
		LOGGER.info("Setup Complete!");
	}
	
    private void doClientStuff(final FMLClientSetupEvent event) {
        // do something that can only be done on the client
		Proxy.registerKeyBindings();
		Proxy.registerRendersPreInit();
		Proxy.registerKeyInputHandler();
		Proxy.registerRendersInit();
    }
    
    public void config(ModConfig.ModConfigEvent event)
    {
        if (event.getConfig().getSpec() == Config.SPEC)
            Config.load();
    }
}
