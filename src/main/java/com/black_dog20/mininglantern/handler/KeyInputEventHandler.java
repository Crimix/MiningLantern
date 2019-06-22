package com.black_dog20.mininglantern.handler;

import com.black_dog20.mininglantern.client.settings.Keybindings;
import com.black_dog20.mininglantern.network.PacketHandler;
import com.black_dog20.mininglantern.network.message.MessageUpdateLanternState;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.InputEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class KeyInputEventHandler {

	@SubscribeEvent
	public void handleKeyInputEvent(InputEvent.KeyInputEvent event) {
		if(Keybindings.TOGGLE_LIGHT.isPressed()){
			PacketHandler.network.sendToServer(new MessageUpdateLanternState());
		}
	}
}
