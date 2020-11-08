package com.black_dog20.mininglantern.common.items;

import com.black_dog20.mininglantern.MiningLantern;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {
	public static final Item.Properties ITEM_GROUP = new Item.Properties().group(MiningLantern.itemGroup);
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, MiningLantern.MOD_ID);

	public static final RegistryObject<Item> MINING_LANTERN = ITEMS.register("mining_lantern", ()-> new LanternItem(ITEM_GROUP.maxStackSize(1)));
}
