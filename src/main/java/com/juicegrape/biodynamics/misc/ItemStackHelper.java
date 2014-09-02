package com.juicegrape.biodynamics.misc;

import net.minecraft.item.ItemStack;

public class ItemStackHelper {
	
	public static boolean areStacksEqualIgnoreSize(ItemStack stack1, ItemStack stack2) {
		ItemStack stack1copy = stack1.copy();
		stack1copy.stackSize = stack2.stackSize;
		return ItemStack.areItemStacksEqual(stack1copy, stack2);
	}

}
