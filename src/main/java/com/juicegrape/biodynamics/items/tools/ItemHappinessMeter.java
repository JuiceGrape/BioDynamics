package com.juicegrape.biodynamics.items.tools;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.blocks.common.BioGeneratorBlock;
import com.juicegrape.biodynamics.items.common.BioItem;

public class ItemHappinessMeter extends BioItem {

	public ItemHappinessMeter(String name) {
		super(name);
		this.setMaxStackSize(1);
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack stack, EntityPlayer player, World world, int x, int y, int z, int side, float hitX, float hitY, float hitZ) {
		if (stack.getItem() instanceof ItemHappinessMeter) {
			Block block = world.getBlock(x, y, z);
			if (block instanceof BioGeneratorBlock) {
				int happiness = ((BioGeneratorBlock)block).getHappiness(world, x, y, z);
				String text;
				if (happiness <= 25) {
					text = "This plant seems quite sad";
				} else if (happiness > 25 && happiness <= 75){
					text = "This plant is satisfied";
				} else {
					text = "This plant is happy";
				}
				player.addChatComponentMessage(new ChatComponentText(text));
				return true;
			}
		}
		return false;
		
	}
	
	

}
