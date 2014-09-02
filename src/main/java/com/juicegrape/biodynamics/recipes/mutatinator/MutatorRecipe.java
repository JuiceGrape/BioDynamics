package com.juicegrape.biodynamics.recipes.mutatinator;

import net.minecraft.item.ItemStack;

public class MutatorRecipe {
	
	ItemStack mainInput;
	ItemStack[] otherInputs;
	ItemStack output;
	
	public MutatorRecipe(ItemStack output, ItemStack main, ItemStack... others) {
		mainInput = main;
		otherInputs = others;
		this.output = output;
	}
	
	public int getOtherMaterialAmounts() {return otherInputs.length;}
	
	public boolean hasOtherInputs() {return getOtherMaterialAmounts() > 0;}
	
	public ItemStack getOutput() {return output;}
	
	public boolean isRecipeCorrect(ItemStack stack) {
		return true;
	}

	
	
}
