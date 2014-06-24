package com.juicegrape.biodynamics.items.itemBlocks;

import com.juicegrape.biodynamics.blocks.BlockInfo;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMinerals extends ItemBlock {

	public ItemBlockMinerals(Block block) {
		super(block);
		setHasSubtypes(true);
		setUnlocalizedName(BlockInfo.MINERALBLOCK);
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return "tile." + BlockInfo.MINERALBLOCKS[stack.getItemDamage()];
	}

}
