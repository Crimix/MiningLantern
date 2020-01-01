package com.black_dog20.mininglantern.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;

public class Config {
	
	public static final ClientConfig CONFIG;
    public static final ForgeConfigSpec SPEC;
    static {
        final Pair<ClientConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
        SPEC = specPair.getRight();
        CONFIG = specPair.getLeft();
    }

	public static boolean others_light = true;

    public static void load()
    {
    	others_light = CONFIG.others_light.get();
    }

    public static class ClientConfig
    {
        public ForgeConfigSpec.BooleanValue others_light;

        ClientConfig(ForgeConfigSpec.Builder builder) {
            builder.push("Client");
            others_light = builder
                    .comment("Show other players light")
                    .translation("config.mininglantern.otherlight")
                    .define("others_light", true);
            builder.pop();
        }
    }

}
