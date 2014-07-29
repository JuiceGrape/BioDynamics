package com.juicegrape.biodynamics.misc;

import java.util.Random;

import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemEntityHelper {
	
	private static Random random = new Random();
	
	 public static EntityItem createItem(ItemStack itemStack, World world, int x, int y, int z) {
			float xThang = random.nextFloat() * 0.8F + 0.1F;
			float yThang = random.nextFloat() * 0.8F + 0.1F;
			float zThang = random.nextFloat() * 0.8F + 0.1F;
			EntityItem entityItem = new EntityItem(world, x + xThang, y + yThang, z + zThang, itemStack);
	        entityItem.motionX = (float) random.nextGaussian() * 0.05F;
	        entityItem.motionY = (float) random.nextGaussian() * 0.05F + 0.2F;
	        entityItem.motionZ = (float) random.nextGaussian() * 0.05F;
	        return entityItem;
		} 
	    
	   public static EntityItem createItemTowardsPlayer(ItemStack itemStack, World world, int x, int y, int z, EntityPlayer player) {
			float yThang = 1.0F;
			float fixer = 0.5F;
			EntityItem entityItem = new EntityItem(world, x + fixer, y + yThang, z + fixer, itemStack);
			float xMov = (float)(player.posX - x);
			float zMov = (float)(player.posZ - z);
			float limit = 5F;
			if (xMov < -limit) {
				xMov = -limit;
			} else if (xMov > limit) {
				xMov = limit;
			}
			if (zMov < -limit) {
				zMov = -limit;
			} else if (zMov > limit) {
				zMov = limit;
			} 
	        entityItem.motionX = xMov * 0.05F;
	        entityItem.motionY = (float) random.nextGaussian() * 0.05F + 0.2F;
	        entityItem.motionZ = zMov * 0.05F;
			return entityItem;
	    }

}
