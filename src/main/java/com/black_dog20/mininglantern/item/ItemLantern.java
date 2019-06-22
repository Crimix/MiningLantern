package com.black_dog20.mininglantern.item;

import java.util.List;

import javax.annotation.Nullable;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.client.settings.Keybindings;
import com.black_dog20.mininglantern.init.ModItems;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ItemLantern extends ItemBase {

	public ItemLantern(String name) {
		super(name);
		this.setMaxStackSize(1);
		MinecraftForge.EVENT_BUS.register(this);
		
		this.addPropertyOverride(new ResourceLocation("state"), new IItemPropertyGetter() {
			
			@Override
			public float apply(ItemStack stack, World worldIn, EntityLivingBase entityIn) {
				if(entityIn != null){
					ILanternCapabilityHandler mh = entityIn.getCapability(LanternCapabilityHandler.CAP, null);
					if(mh != null && !mh.getHasLantern() && !mh.getHasLanternOn()){
						return 0;
					}
				}
				
				return -1;
			}
		});
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		super.addInformation(stack, worldIn, tooltip, flagIn);
		
		tooltip.add(I18n.format("tooltip.mininglantern:activate", TextFormatting.BLUE, Keybindings.TOGGLE_LIGHT.getDisplayName(), TextFormatting.GRAY));
		if(Loader.isModLoaded("albedo"))
			tooltip.add(I18n.format("tooltip.mininglantern:loaded"));
		else
			tooltip.add(I18n.format("tooltip.mininglantern:notloaded"));
	}	
	
	
	@SubscribeEvent
	public void onPlayerUpdate(LivingUpdateEvent event){
		if(event.getEntity() instanceof EntityPlayer && !event.getEntity().world.isRemote) {
			EntityPlayer player = (EntityPlayer)event.getEntity();
			ILanternCapabilityHandler mh = LanternCapabilityHandler.instanceFor(player);
			if(mh != null && !mh.getHasLantern() && player.inventory.hasItemStack(new ItemStack(ModItems.lantern)))
				mh.setHasLantern(true);
			else if(mh != null && mh.getHasLantern() && !player.inventory.hasItemStack(new ItemStack(ModItems.lantern)))
				mh.setHasLantern(false);
			}
	}
}
