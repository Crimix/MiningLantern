package com.black_dog20.mininglantern.creativetab;

import com.black_dog20.mininglantern.init.ModItems;
import com.black_dog20.mininglantern.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTab{

	public static final CreativeTabs TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {

		@Override
		public ItemStack getTabIconItem() {
			return new ItemStack(ModItems.lantern);
		}

		@Override
		public String getTranslatedTabLabel() {
			return Reference.MOD_NAME;
		}
	};

}
