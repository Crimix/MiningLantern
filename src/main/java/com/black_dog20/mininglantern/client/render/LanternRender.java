package com.black_dog20.mininglantern.client.render;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.init.ModItems;
import com.black_dog20.mininglantern.reference.NBTTags;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;

public class LanternRender implements LayerRenderer<EntityPlayer>{

	@Override
	public void render(EntityPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scale) {
		ILanternCapabilityHandler mh = player.getCapability(LanternCapabilityHandler.CAP, null).orElse(null);
		if(mh != null && mh.getHasLantern()) {
			GlStateManager.pushMatrix();
			GlStateManager.scaled(0.5, 0.5, 0.5);
			if(player.isSneaking()){
				if(!player.inventory.armorItemInSlot(2).isEmpty())
					GlStateManager.translatef(0.2F, 1.5F, 1.05F);
				else if(!player.inventory.armorItemInSlot(1).isEmpty())
					GlStateManager.translatef(0.2F, 1.5F, 1F);
				else
					GlStateManager.translatef(0.2F, 1.5F, 0.9F);
				GlStateManager.rotatef(90F / (float) Math.PI, 1.0F, 0.0F, 0.0F);
			}
			else {
				if(!player.inventory.armorItemInSlot(2).isEmpty())
					GlStateManager.translatef(0.2F, 1.4F, 0.4F);
				else if(!player.inventory.armorItemInSlot(1).isEmpty())
					GlStateManager.translatef(0.2F, 1.4F, 0.35F);
				else
					GlStateManager.translatef(0.2F, 1.4F, 0.3F);
			}
			GlStateManager.rotatef(180F, 0.0F, 0.0F, 1.0F);
			ItemStack renderStack = new ItemStack(ModItems.lantern);
			NBTTagCompound nbt = new NBTTagCompound();
			nbt.setBoolean(NBTTags.ACTIVE, mh.getHasLanternOn());
			renderStack.setTag(nbt);
			
			if(!player.getHeldItemMainhand().getItem().equals(ModItems.lantern) && !player.getHeldItemOffhand().getItem().equals(ModItems.lantern))
				Minecraft.getInstance().getItemRenderer().renderItem(renderStack , ItemCameraTransforms.TransformType.FIXED);
			GlStateManager.popMatrix();
		}
		
	}

	@Override
	public boolean shouldCombineTextures() {
		// TODO Auto-generated method stub
		return false;
	}

}
