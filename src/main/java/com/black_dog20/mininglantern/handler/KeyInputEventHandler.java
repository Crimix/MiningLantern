package com.black_dog20.mininglantern.handler;

import com.black_dog20.mininglantern.client.settings.Keybindings;
import com.black_dog20.mininglantern.network.PacketHandler;
import com.black_dog20.mininglantern.network.message.MessageUpdateLanternState;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

@OnlyIn(Dist.CLIENT)
public class KeyInputEventHandler {

	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		if(Keybindings.TOGGLE_LIGHT.isPressed()){
			PacketHandler.network.sendToServer(new MessageUpdateLanternState());
		}
	}
}
