package com.black_dog20.mininglantern.client;

import com.black_dog20.mininglantern.MiningLantern;
import com.black_dog20.mininglantern.client.keybinds.Keybinds;
import com.black_dog20.mininglantern.client.renders.LanternRender;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.PlayerRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber( modid = MiningLantern.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientStartup {

    @SubscribeEvent
    public static void setupClient(FMLClientSetupEvent event) {
        ClientRegistry.registerKeyBinding(Keybinds.TOGGLE_LIGHT);
        PlayerRenderer playerRendererDefault = Minecraft.getInstance().getRenderManager().getSkinMap().get("default");
        PlayerRenderer playerRendererSlim = Minecraft.getInstance().getRenderManager().getSkinMap().get("slim");

        playerRendererDefault.addLayer(new LanternRender<>(playerRendererDefault));
        playerRendererSlim.addLayer(new LanternRender<>(playerRendererSlim));
    }
}
