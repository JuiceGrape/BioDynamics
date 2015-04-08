package com.juicegrape.biodynamics.recipes.mutatinator;

import java.util.ArrayList;

import com.juicegrape.biodynamics.blocks.ModBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public class MutatorRecipe {
	
	public static ArrayList<MutatorRecipe> recipes;
	Object main;
	Object[] inputs;
	ItemStack output;
	
	int reqHeat;
	int reqPower;
	
	public MutatorRecipe(Object mainInput, ItemStack output, int heat, int power, Object... objects) {
		main = mainInput;
		this.output = output;
		inputs = objects;
		reqHeat = heat;
		reqPower = power;
		
	}
	
	public int getOtherRecipeLength() { return inputs.length;}
	public ItemStack getOutput() {return output;}
	public int getHeat() { return reqHeat; }
	public int getPower() { return reqPower; }	
	public boolean requiresExtraInput() { return getOtherRecipeLength() > 0;}
	
	public boolean hasMainItem(ItemStack stack) {
		if (main instanceof ItemStack) {
			if (((ItemStack)main).isItemEqual(stack)) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasItem(ItemStack stack) {
		if (getOtherRecipeLength() == 0) 
			return false;
		for (Object obj : inputs) {
			if (obj instanceof ItemStack) {
				ItemStack objstack = (ItemStack)obj;
				if (objstack.isItemEqual(stack)) {
					return true;
				}
			}
		}
		return false;
	}

	
	
	public static void init() {
		recipes = new ArrayList<MutatorRecipe>();
		
		recipes.add(new MutatorRecipe(new ItemStack(Blocks.bedrock), new ItemStack(ModBlocks.burningflower), 800, 5000, new Object[]{
			new ItemStack(Items.egg)
		}));
	}
	
	
	
	
	
}
