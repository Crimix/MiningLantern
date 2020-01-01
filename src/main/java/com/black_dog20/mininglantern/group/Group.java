package com.black_dog20.mininglantern.group;

import com.black_dog20.mininglantern.init.ModItems;
import com.black_dog20.mininglantern.reference.NBTTags;
import com.black_dog20.mininglantern.reference.Reference;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class Group{

	public static final ItemGroup TAB = new ItemGroup(Reference.MOD_ID.toLowerCase()) {

		@Override
		public String getTabLabel() {
			return Reference.MOD_NAME;
		}
		@Override
		public ItemStack createIcon() {
			ItemStack stack = new ItemStack(ModItems.lantern);
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setBoolean(NBTTags.ACTIVE, true);
			stack.setTag(nbt);
			return stack;
		}
	};

}
