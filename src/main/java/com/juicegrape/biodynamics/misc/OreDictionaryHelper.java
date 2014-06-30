package com.juicegrape.biodynamics.misc;

import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;


/**
 * 
 * @author JuiceGrape
 *
 */
public class OreDictionaryHelper {
	
	public static boolean isStackEqual(ItemStack stack1, ItemStack stack2) {
		return OreDictionary.itemMatches(stack1, stack2, false);
//		return OreDictionary.getOreName(OreDictionary.getOreID(stack1)) != null && OreDictionary.getOreName(OreDictionary.getOreID(stack2)) != null && OreDictionary.getOreName(OreDictionary.getOreID(stack1)).equals(OreDictionary.getOreName(OreDictionary.getOreID(stack2)));
	}
	
	public static boolean isIronDust(ItemStack stack) {
		for (int id : OreDictionary.getOreIDs(stack)) {
			if (OreDictionary.getOreName(id).equals("dustIron")) {
				return true;
			}
		}
		return false;
	}

}
