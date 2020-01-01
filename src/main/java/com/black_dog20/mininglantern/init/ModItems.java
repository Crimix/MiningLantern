package com.black_dog20.mininglantern.init;

import com.black_dog20.mininglantern.item.ItemLantern;
import com.black_dog20.mininglantern.reference.Reference;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.IForgeRegistry;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID)
public class ModItems {
	public static final Item lantern = new ItemLantern("miningLantern");
	
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> evt) {
		IForgeRegistry<Item> r = evt.getRegistry();

		r.register(lantern);
	}
}
