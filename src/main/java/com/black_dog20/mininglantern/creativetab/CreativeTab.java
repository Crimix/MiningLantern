package com.black_dog20.mininglantern.creativetab;

import com.black_dog20.mininglantern.init.ModItems;
import com.black_dog20.mininglantern.reference.NBTTags;
import com.black_dog20.mininglantern.reference.Reference;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class CreativeTab{

	public static final CreativeTabs TAB = new CreativeTabs(Reference.MOD_ID.toLowerCase()) {

		@Override
		public ItemStack getTabIconItem() {
			ItemStack stack = new ItemStack(ModItems.lantern);
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setBoolean(NBTTags.ACTIVE, true);
			stack.setTagCompound(nbt);
			return stack;
		}
		@Override
		public ItemStack getIconItemStack() {
			return getTabIconItem();
		};

		@Override
		public String getTranslatedTabLabel() {
			return Reference.MOD_NAME;
		}
	};

}
