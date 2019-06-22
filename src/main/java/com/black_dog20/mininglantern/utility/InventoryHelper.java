package com.black_dog20.mininglantern.utility;

import baubles.api.BaublesApi;
import baubles.api.cap.IBaublesItemHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Loader;

public class InventoryHelper {
	
	public static boolean doesPlayerHave(EntityPlayer player, ItemStack stack) {
		if(Loader.isModLoaded("baubles")) {
			IBaublesItemHandler baubles = BaublesApi.getBaublesHandler(player);
			for(int i = 0; i < baubles.getSlots(); i++) {
				ItemStack baubleStack = baubles.getStackInSlot(i);
				if(baubleStack != null && baubleStack.isItemEqual(stack))
					return true;
			}
		}
		
		return player.inventory.hasItemStack(stack);
	}
}
