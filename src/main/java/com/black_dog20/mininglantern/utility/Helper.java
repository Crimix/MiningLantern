package com.black_dog20.mininglantern.utility;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Predicate;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.AxisAlignedBB;

public class Helper {
	
	public static Predicate<Entity> isPlayer(){
		return ((x) -> {return x instanceof EntityPlayer;});
	}
	
	public static AxisAlignedBB getAxisAlignedBB(Entity enity, int radius) {
		return new AxisAlignedBB(
				enity.posX-radius, 
				enity.posY-radius, 
				enity.posZ-radius, 
				enity.posX+radius, 
				enity.posY+radius, 
				enity.posZ+radius);
	}
	
	public static List<EntityPlayer> getPlayersInRadius(Entity enity, int raduis){
		List<Entity> list =	enity.world.getEntitiesInAABBexcluding(enity, getAxisAlignedBB(enity, raduis), isPlayer());
		List<EntityPlayer> res = new ArrayList<EntityPlayer>();
		for (Entity entity : list)
			if(entity instanceof EntityPlayer)
				res.add((EntityPlayer)entity);
		return res;
	}
	
	public static List<Entity> getEnityInRadius(Entity enity, int raduis, @Nullable Predicate <? super Entity > predicate){
		List<Entity> list =	enity.world.getEntitiesInAABBexcluding(enity, getAxisAlignedBB(enity, raduis), predicate);
		return list;
	}

}
