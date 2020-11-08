package com.black_dog20.mininglantern.common.network.message;

import com.black_dog20.mininglantern.common.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.common.capability.LanternCapabilityHandler;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.UUID;
import java.util.function.Supplier;

public class MessageSyncLanternCapability{

	private final UUID uuid;
	private final CompoundNBT nbt;
	
	public MessageSyncLanternCapability(PlayerEntity playerEntity, ILanternCapabilityHandler mh) {
		this(playerEntity.getUniqueID(), mh.writeToNBT());
	}

	public MessageSyncLanternCapability(UUID uuid, CompoundNBT nbt) {
		this.uuid = uuid;
		this.nbt = nbt;
	}

	public static void encode(MessageSyncLanternCapability msg, PacketBuffer buffer) {
		buffer.writeUniqueId(msg.uuid);
		buffer.writeCompoundTag(msg.nbt);
	}

	public static MessageSyncLanternCapability decode(PacketBuffer buffer) {
		return new MessageSyncLanternCapability(buffer.readUniqueId(), buffer.readCompoundTag());
	}

	public static class Handler {
		public static void handle(MessageSyncLanternCapability msg, Supplier<NetworkEvent.Context> ctx) {
			ctx.get().enqueueWork(() -> DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> {
				PlayerEntity player = Minecraft.getInstance().world.getPlayerByUuid(msg.uuid);
				ILanternCapabilityHandler mh = LanternCapabilityHandler.instanceFor(player);
				if(mh != null){
					mh.readFromNBT(msg.nbt);
				}
			}));

			ctx.get().setPacketHandled(true);
		}
	}

}
