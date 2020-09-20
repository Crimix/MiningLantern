package com.black_dog20.mininglantern.compatibility;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;
import com.shinoow.darknesslib.api.DarknessLibAPI;
import net.minecraft.entity.player.EntityPlayer;

public class DarknessLibCompat {

    public static void init() {
        DarknessLibAPI.getInstance().addLightProvider(DarknessLibCompat::hasLanternOn);
    }

    private static int hasLanternOn(EntityPlayer player) {
        ILanternCapabilityHandler lch = LanternCapabilityHandler.instanceFor(player);
        if(lch != null && lch.getHasLantern() && lch.getHasLanternOn()) {
            return 15;
        } else {
            return 0;
        }
    }
}
