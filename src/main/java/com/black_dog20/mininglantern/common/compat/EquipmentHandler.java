package com.black_dog20.mininglantern.common.compat;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public abstract class EquipmentHandler {
    public static EquipmentHandler instance;

    public static void init() {
        if(ModList.get().isLoaded("curios")) {
            instance = new CurioIntegration();
            FMLJavaModLoadingContext.get().getModEventBus().register(CurioIntegration.class);
            MinecraftForge.EVENT_BUS.register(CurioIntegration.class);
        } else {
            instance = new InventoryEquipmentHandler();
            FMLJavaModLoadingContext.get().getModEventBus().register(InventoryEquipmentHandler.class);
            MinecraftForge.EVENT_BUS.register(InventoryEquipmentHandler.class);
        }
    }

    public static boolean doesPlayerHave(PlayerEntity player, Item stack) {
        return instance.playerHave(player, stack);
    }

    protected abstract boolean playerHave(PlayerEntity player, Item stack);
}
