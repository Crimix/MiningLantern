package com.black_dog20.mininglantern.compatibility;

import net.minecraftforge.fml.common.Loader;

public class Compatibility {

    public static void init() {
        if (Loader.isModLoaded("darknesslib")) {
            DarknessLibCompat.init();
        }
    }
}
