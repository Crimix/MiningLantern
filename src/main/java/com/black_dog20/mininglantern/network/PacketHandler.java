package com.black_dog20.mininglantern.network;

import com.black_dog20.mininglantern.network.message.MessageSyncLanternCapability;
import com.black_dog20.mininglantern.network.message.MessageSyncLanternCapabilityTracking;
import com.black_dog20.mininglantern.reference.Reference;

import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import net.minecraftforge.fml.relauncher.Side;


public class PacketHandler {

	public static final SimpleNetworkWrapper network = NetworkRegistry.INSTANCE.newSimpleChannel(Reference.MOD_ID.toLowerCase());

	public static void init() {
		network.registerMessage(MessageSyncLanternCapability.class, MessageSyncLanternCapability.class, 0, Side.CLIENT);
		network.registerMessage(MessageSyncLanternCapabilityTracking.class, MessageSyncLanternCapabilityTracking.class, 1, Side.CLIENT);
	}

}
