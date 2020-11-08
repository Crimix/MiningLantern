package com.black_dog20.mininglantern.client.renders;

import com.black_dog20.mininglantern.common.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.common.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.common.items.ModItems;
import com.black_dog20.mininglantern.common.utility.NBTTags;
import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class LanternRender<T extends PlayerEntity, M extends BipedModel<T>> extends LayerRenderer<T, M>{

	public LanternRender(IEntityRenderer<T, M> renderer) {
		super(renderer);
	}

	@Override
	public void render(PlayerEntity player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch, float scaleIn) {
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
			ItemStack renderStack = new ItemStack(ModItems.MINING_LANTERN.get());
			CompoundNBT nbt = renderStack.getOrCreateTag();
			nbt.putBoolean(NBTTags.ACTIVE, mh.getHasLanternOn());
			renderStack.setTag(nbt);

			if(!player.getHeldItemMainhand().getItem().equals(ModItems.MINING_LANTERN.get()) && !player.getHeldItemOffhand().getItem().equals(ModItems.MINING_LANTERN.get()))
				Minecraft.getInstance().getItemRenderer().renderItem(renderStack , ItemCameraTransforms.TransformType.FIXED);
			GlStateManager.popMatrix();
		}
	}

	@Override
	public boolean shouldCombineTextures() {
		return false;
	}

}
