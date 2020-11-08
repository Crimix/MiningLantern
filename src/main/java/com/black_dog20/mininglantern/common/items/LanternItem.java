package com.black_dog20.mininglantern.common.items;

import com.black_dog20.mininglantern.client.keybinds.Keybinds;
import com.black_dog20.mininglantern.common.capability.ILanternCapabilityHandler;
import com.black_dog20.mininglantern.common.capability.LanternCapabilityHandler;
import com.black_dog20.mininglantern.common.compat.EquipmentHandler;
import com.black_dog20.mininglantern.common.utility.NBTTags;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LanternItem extends Item {


	public LanternItem(Properties builder) {
		super(builder);
		MinecraftForge.EVENT_BUS.register(this);
		this.addPropertyOverride(new ResourceLocation("state"), new IItemPropertyGetter() {

			@Override
			public float call(ItemStack stack, World worldIn, LivingEntity entityIn) {
				if(entityIn != null){
					ILanternCapabilityHandler mh = entityIn.getCapability(LanternCapabilityHandler.CAP, null).orElse(null);
					if(mh != null && !mh.getHasLanternOn()){
						return 0;
					}
				}
				else if(stack.hasTag()){
					CompoundNBT nbt = stack.getTag();
					if(nbt.contains(NBTTags.ACTIVE)) {
						if(!nbt.getBoolean(NBTTags.ACTIVE))
							return 0;
					}
				}

				return -1;
			}
		});
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(new TranslationTextComponent("tooltip.mininglantern:activate", capitalizeFirstLetter(Keybinds.TOGGLE_LIGHT.getLocalizedName().toLowerCase())).applyTextStyle(TextFormatting.DARK_GRAY));
		super.addInformation(stack, world, tooltip, flag);
	}

	private String capitalizeFirstLetter(String text) {
		if(text == null || text.isEmpty())
			return "";
		return Arrays.stream(text.split(" "))
				.map(s -> s.substring(0, 1).toUpperCase() + s.substring(1))
				.collect(Collectors.joining(" "));
	}
	
	@SubscribeEvent
	public void onPlayerUpdate(LivingUpdateEvent event){
		if(event.getEntity() instanceof PlayerEntity && !event.getEntity().world.isRemote) {
			PlayerEntity player = (PlayerEntity)event.getEntity();
			ILanternCapabilityHandler mh = LanternCapabilityHandler.instanceFor(player);
			if(mh != null && !mh.getHasLantern() && EquipmentHandler.doesPlayerHave(player, ModItems.MINING_LANTERN.get()))
				mh.setHasLantern(true);
			else if(mh != null && mh.getHasLantern() && !EquipmentHandler.doesPlayerHave(player, ModItems.MINING_LANTERN.get()))
				mh.setHasLantern(false);
			}
	}

}
