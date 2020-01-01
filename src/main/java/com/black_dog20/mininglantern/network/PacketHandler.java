package com.black_dog20.mininglantern.network;

import com.black_dog20.mininglantern.network.message.MessageSyncLanternCapability;
import com.black_dog20.mininglantern.network.message.MessageUpdateHasLantern;
import com.black_dog20.mininglantern.network.message.MessageUpdateLanternState;
import com.black_dog20.mininglantern.reference.Reference;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkRegistry;
import net.minecraftforge.fml.network.simple.SimpleChannel;


public class PacketHandler {

	private static ResourceLocation net = new ResourceLocation(Reference.MOD_ID,"net");
	public static SimpleChannel network;

	public static void init() {
		network = NetworkRegistry.ChannelBuilder.named(net).
        clientAcceptedVersions(s -> true).
        serverAcceptedVersions(s -> true).
        networkProtocolVersion(() -> "1").
		simpleChannel();
		
		network.messageBuilder(MessageSyncLanternCapability.class, 1).
		decoder(MessageSyncLanternCapability::fromBytes).
		encoder(MessageSyncLanternCapability::toBytes).
		consumer(MessageSyncLanternCapability::onMessage).
		add();
	
		network.messageBuilder(MessageUpdateLanternState.class, 2).
		decoder(MessageUpdateLanternState::fromBytes).
		encoder(MessageUpdateLanternState::toBytes).
		consumer(MessageUpdateLanternState::onMessage).
		add();
		
		network.messageBuilder(MessageUpdateHasLantern.class, 3).
		decoder(MessageUpdateHasLantern::fromBytes).
		encoder(MessageUpdateHasLantern::toBytes).
		consumer(MessageUpdateHasLantern::onMessage).
		add();
		
	}

}
