package com.black_dog20.mininglantern.client.settings;

import org.lwjgl.glfw.GLFW;

import com.black_dog20.mininglantern.reference.Names;

import net.minecraft.client.settings.KeyBinding;

public class Keybindings {
	
	public static KeyBinding TOGGLE_LIGHT = new KeyBinding(Names.Keys.TOGGLE_LIGHT, GLFW.GLFW_KEY_V, Names.Keys.CATEGORY);

}
