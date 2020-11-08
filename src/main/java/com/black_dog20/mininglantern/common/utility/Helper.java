package com.black_dog20.mininglantern.common.utility;

import com.google.common.base.Predicate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.AxisAlignedBB;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class Helper {
	
	public static Predicate<Entity> isPlayer(){
		return ((x) -> x instanceof PlayerEntity);
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
	
	public static List<PlayerEntity> getPlayersInRadius(Entity enity, int raduis){
		List<Entity> list =	enity.world.getEntitiesInAABBexcluding(enity, getAxisAlignedBB(enity, raduis), isPlayer());
		List<PlayerEntity> res = new ArrayList<PlayerEntity>();
		for (Entity entity : list)
			if(entity instanceof PlayerEntity)
				res.add((PlayerEntity)entity);
		return res;
	}
	
	public static List<Entity> getEnityInRadius(Entity enity, int raduis, @Nullable Predicate <? super Entity > predicate){
		List<Entity> list =	enity.world.getEntitiesInAABBexcluding(enity, getAxisAlignedBB(enity, raduis), predicate);
		return list;
	}

}
