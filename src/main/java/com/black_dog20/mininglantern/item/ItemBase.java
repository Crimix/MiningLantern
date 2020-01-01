package com.black_dog20.mininglantern.item;

import com.black_dog20.mininglantern.group.Group;
import com.black_dog20.mininglantern.reference.Reference;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemBase extends Item {

	public ItemBase(String name, Properties properties) {

		super(properties.group(Group.TAB));
		this.setRegistryName(new ResourceLocation(Reference.MOD_ID, name));
	}
}
