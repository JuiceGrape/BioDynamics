package com.juicegrape.biodynamics.misc;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

public class OreDictionaryHelper {
	
	public static boolean isStackEqual(ItemStack stack1, ItemStack stack2) {
		return OreDictionary.getOreName(OreDictionary.getOreID(stack1)) != null && OreDictionary.getOreName(OreDictionary.getOreID(stack2)) != null && OreDictionary.getOreName(OreDictionary.getOreID(stack1)).equals(OreDictionary.getOreName(OreDictionary.getOreID(stack2)));
	}

}
