package com.black_dog20.mininglantern.common.compat;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;

public class InventoryEquipmentHandler extends EquipmentHandler {

    @Override
    protected boolean playerHave(PlayerEntity player, Item item) {
        return player.inventory.hasItemStack(item.getDefaultInstance());
    }
}
