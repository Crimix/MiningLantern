package com.black_dog20.mininglantern.client.events;

import com.black_dog20.mininglantern.MiningLantern;
import com.black_dog20.mininglantern.client.keybinds.Keybinds;
import com.black_dog20.mininglantern.common.network.PacketHandler;
import com.black_dog20.mininglantern.common.network.message.MessageUpdateLanternState;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;


@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = MiningLantern.MOD_ID, value = Dist.CLIENT)
public class KeyEventHandler {

	@SubscribeEvent
	public static void onEvent(InputEvent.KeyInputEvent event) {
		if (Minecraft.getInstance().currentScreen == null) {
			if(Keybinds.TOGGLE_LIGHT.isPressed()){
				PacketHandler.sendToServer(new MessageUpdateLanternState());
			}
		}
	}
}
