package com.black_dog20.mininglantern.client.render;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class LanternRender implements LayerRenderer<EntityPlayer>{

	@Override
	public void doRenderLayer(EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		ILanternCapabilityHandler mh = player.getCapability(LanternCapabilityHandler.CAP, null);
		if(mh != null && mh.getHasLantern()) {
			float Myscale = 1F / 2.2F;
			GlStateManager.pushMatrix();
			if(player.isSneaking()){
				GlStateManager.translate(0.03F, 0.6F, 0.25F);
				GlStateManager.rotate(90F / (float) Math.PI, 1.0F, 0.0F, 0.0F);
			}else {
				GlStateManager.translate(0.03F, 0.5F, 0.032F);
			}
			GlStateManager.scale(Myscale, Myscale, Myscale);
			GlStateManager.popMatrix();
			GlStateManager.pushMatrix();
			GlStateManager.scale(0.1, 0.1, 0.1);
			if(player.isSneaking()){
				GlStateManager.translate(-0.055F, 7.1F, 1.1F);
				GlStateManager.rotate(90F / (float) Math.PI, 1.0F, 0.0F, 0.0F);
			}
			else {
				GlStateManager.translate(-0.055F, 5.3F, -1.4F);
			}
			GlStateManager.rotate(180F, 0.0F, 0.0F, 1.0F);
				Minecraft.getMinecraft().getRenderItem().renderItem(new ItemStack(Items.ACACIA_BOAT) , ItemCameraTransforms.TransformType.FIXED);
			GlStateManager.popMatrix();
		}
		
	}

	@Override
	public boolean shouldCombineTextures() {
		// TODO Auto-generated method stub
		return false;
	}

}
