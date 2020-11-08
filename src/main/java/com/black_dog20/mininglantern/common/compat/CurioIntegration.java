package com.black_dog20.mininglantern.common.compat;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import org.apache.commons.lang3.tuple.ImmutableTriple;
import top.theillusivec4.curios.api.CuriosAPI;
import top.theillusivec4.curios.api.imc.CurioIMCMessage;

public class CurioIntegration extends EquipmentHandler {

    @SubscribeEvent
    public static void sendImc(InterModEnqueueEvent evt) {
        InterModComms.sendTo("curios", CuriosAPI.IMC.REGISTER_TYPE, () -> new CurioIMCMessage("belt"));
    }

    @Override
    protected boolean playerHave(PlayerEntity player, Item item) {
        boolean inCurios = CuriosAPI.getCurioEquipped(item, player)
                .map(ImmutableTriple::getRight).isPresent();


        return inCurios || player.inventory.hasItemStack(item.getDefaultInstance());
    }
}
