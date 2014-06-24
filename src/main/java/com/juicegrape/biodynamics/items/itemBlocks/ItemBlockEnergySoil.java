package com.juicegrape.biodynamics.items.itemBlocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

import com.juicegrape.biodynamics.blocks.BlockInfo;

public class ItemBlockEnergySoil extends ItemBlock {

	public ItemBlockEnergySoil(Block block) {
		super(block);
		setHasSubtypes(true);
		setUnlocalizedName(BlockInfo.ENERGETICSOIL);
	}
	
	@Override
	public int getMetadata (int damageValue) {
		return damageValue;
	}
	
	@Override
	public String getUnlocalizedName(ItemStack stack) {
		return super.getUnlocalizedName() + stack.getItemDamage();
	}

}
