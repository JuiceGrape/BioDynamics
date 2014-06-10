package com.juicegrape.biodynamics.recipes;

import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.juicegrape.biodynamics.blocks.ModBlocks;
import com.juicegrape.biodynamics.items.ModItems;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonRecipes {
	
	public static void initCommonRecipes() {
		
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.enertreePlanks, 4), new Object[] {
			new ItemStack(ModBlocks.enertreeLogs)
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.specialBonemeal, 2, 0), new Object[] {
			new ItemStack(Items.dye, 1, 15), new ItemStack(Items.dye, 1, 15), new ItemStack(Items.redstone)
		});
		
	}

}
