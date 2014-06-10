package com.juicegrape.biodynamics.recipes;

import net.minecraft.item.ItemStack;

import com.juicegrape.biodynamics.blocks.ModBlocks;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonRecipes {
	
	public static void initCommonRecipes() {
		
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.enertreePlanks, 4), new Object[] {
			new ItemStack(ModBlocks.enertreeLogs)
		});
		
	}

}
