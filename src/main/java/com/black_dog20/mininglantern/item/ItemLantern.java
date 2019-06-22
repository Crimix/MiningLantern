package com.black_dog20.mininglantern.item;

import com.black_dog20.mininglantern.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.init.ModItems;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

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
