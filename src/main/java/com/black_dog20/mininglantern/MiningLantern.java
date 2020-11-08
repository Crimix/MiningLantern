package com.black_dog20.mininglantern;

import com.black_dog20.mininglantern.common.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.common.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.common.capability.LanternCapabilityStorage;
import com.black_dog20.mininglantern.common.compat.EquipmentHandler;
import com.black_dog20.mininglantern.common.items.ModItems;
import com.black_dog20.mininglantern.common.network.PacketHandler;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLPaths;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(MiningLantern.MOD_ID)
public class MiningLantern {

	public static final String MOD_ID = "mininglantern";
	private static final Logger LOGGER = LogManager.getLogger();

	public static ItemGroup itemGroup = new ItemGroup(MiningLantern.MOD_ID) {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(ModItems.MINING_LANTERN.get());
		}
	};

	public MiningLantern() {
		IEventBus event = FMLJavaModLoadingContext.get().getModEventBus();
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, Config.CLIENT_CONFIG);
		Config.loadConfig(Config.CLIENT_CONFIG, FMLPaths.CONFIGDIR.get().resolve(MOD_ID + "-client.toml"));

		ModItems.ITEMS.register(event);

		event.addListener(this::setup);
		MinecraftForge.EVENT_BUS.register(this);
	}

	private void setup(final FMLCommonSetupEvent event){
		PacketHandler.register();
		CapabilityManager.INSTANCE.register(ILanternCapabilityHandler.class, new LanternCapabilityStorage(), new LanternCapabilityHandler.Factory());

		EquipmentHandler.init();
	}

	public static Logger getLogger() {
		return LOGGER;
	}

}
