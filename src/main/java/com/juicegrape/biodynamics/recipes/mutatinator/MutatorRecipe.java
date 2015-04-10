package com.juicegrape.biodynamics.recipes.mutatinator;

import java.util.ArrayList;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.juicegrape.biodynamics.blocks.ModBlocks;

public class MutatorRecipe {
	
	public static ArrayList<MutatorRecipe> recipes;
	Object main;
	Object[] inputs;
	ItemStack output;
	
	int reqHeat;
	int reqPower;
	
	int speed;
	public MutatorRecipe(Object mainInput, ItemStack output, int heat, int power, Object... objects) {
		main = mainInput;
		this.output = output;
		inputs = objects;
		reqHeat = heat;
		reqPower = power;
		speed = 1;
		
	}
	
	public int getOtherRecipeLength() { return inputs.length;}
	public ItemStack getOutput() {return output;}
	public int getHeat() { return reqHeat; }
	public int getPower() { return reqPower; }	
	public int getSpeed() { return speed; }
	public boolean requiresExtraInput() { return getOtherRecipeLength() > 0;}
	private MutatorRecipe setSpeed(int speed) { this.speed = speed; return this; }
	
	public boolean hasMainItem(ItemStack stack) {
		if (main instanceof ItemStack) {
			if (((ItemStack)main).isItemEqual(stack)) {
				return true;
			}
		} else if (main instanceof String) {
			String text = (String)main;
			for(int ore : OreDictionary.getOreIDs(stack)) {
				if (OreDictionary.getOreName(ore).equals(text)) {
					return true;
				}
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
			}else if (obj instanceof String) {
				String text = (String)obj;
				for(int ore : OreDictionary.getOreIDs(stack)) {
					if (OreDictionary.getOreName(ore).equals(text)) {
						return true;
					}
				}
			}
		}
		return false;
	}

	
	
	public static void init() {
		recipes = new ArrayList<MutatorRecipe>();
		recipes.add(new MutatorRecipe("treeSapling", new ItemStack(ModBlocks.enerTreeSapling, 1), 150, 500).setSpeed(2));
		recipes.add(new MutatorRecipe("treeSapling", new ItemStack(ModBlocks.enerTreeSapling, 2), 150, 500, new Object[] {
			"treeSapling"
		}).setSpeed(2));
	}
	
	
	
	
	
}
