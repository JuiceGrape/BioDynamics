package com.juicegrape.biodynamics.recipes;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

import com.juicegrape.biodynamics.blocks.ModBlocks;
import com.juicegrape.biodynamics.items.ModItems;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 
 * @author JuiceGrape
 *
 */
public class CommonRecipes {
	
	
	public static void initCommonRecipes() {
		
		//Redstone Bonemeal
		GameRegistry.addShapelessRecipe(new ItemStack(ModBlocks.enertreePlanks, 4), new Object[] {
			new ItemStack(ModBlocks.enertreeLogs)
		});
		
		//Gold Bonemeal
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.specialBonemeal, 2, 0), new Object[] {
			new ItemStack(Items.dye, 1, 15), new ItemStack(Items.dye, 1, 15), new ItemStack(Items.redstone)
		});
		
		//Organics iron
		//dust to ingot
		GameRegistry.addSmelting(new ItemStack(ModItems.craftingItem, 1, 3), new ItemStack(ModItems.craftingItem, 1, 1), 200);
		//ingots to nugget
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.craftingItem, 9, 2), new ItemStack (ModItems.craftingItem, 1, 1));
		//nugget to ingots
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.craftingItem, 1, 1), new Object[] {
			"aaa",
			"aaa",
			"aaa",
			Character.valueOf('a'), new ItemStack(ModItems.craftingItem, 1, 2)
		});
		//block to ingots
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.craftingItem, 9, 1), new ItemStack (ModBlocks.mineralBlocks, 1, 1));
		//ingots to block
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.mineralBlocks, 1, 1), new Object[] {
			"aaa",
			"aaa",
			"aaa",
			Character.valueOf('a'), new ItemStack(ModItems.craftingItem, 1, 1)
		});
		
		//Organics gold
		//dust to ingot
		GameRegistry.addSmelting(new ItemStack(ModItems.craftingItem, 1, 7), new ItemStack(ModItems.craftingItem, 1, 5), 200);
		//ingot to nuggets
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.craftingItem, 9, 6), new ItemStack (ModItems.craftingItem, 1, 5));
		//nugget to ingots
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.craftingItem, 1, 5), new Object[] {
			"aaa",
			"aaa",
			"aaa",
			Character.valueOf('a'), new ItemStack(ModItems.craftingItem, 1, 6)
		});
		//block to ingots
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.craftingItem, 9, 5), new ItemStack (ModBlocks.mineralBlocks, 1, 2));
		//ingots to block
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.mineralBlocks, 1, 2), new Object[] {
			"aaa",
			"aaa",
			"aaa",
			Character.valueOf('a'), new ItemStack(ModItems.craftingItem, 1, 5)
		});
		
		//Pink diamond
		//block to gems
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.craftingItem, 9, 0), new ItemStack (ModBlocks.mineralBlocks, 1, 0));
		//gems to block
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.mineralBlocks, 1, 0), new Object[] {
			"aaa",
			"aaa",
			"aaa",
			Character.valueOf('a'), new ItemStack(ModItems.craftingItem, 1, 0)
		});
		
		
		
		//Redstone soil
		GameRegistry.addRecipe(new ItemStack(ModBlocks.energysoil, 1, 0), new Object[] {
			"rdr",
			"drd",
			"rdr",
			Character.valueOf('r'), new ItemStack(ModItems.specialBonemeal, 1, 0),
			Character.valueOf('d'), new ItemStack(Blocks.dirt)
		});
		
		//Enertree furnace recipe
		GameRegistry.addRecipe(new ItemStack(ModBlocks.enertreeFurnace), new Object[] {
			"l l",
			"lrl",
			"www",
			Character.valueOf('l'), new ItemStack(ModBlocks.enertreeLogs),
			Character.valueOf('r'), new ItemStack(Blocks.redstone_block),
			Character.valueOf('w'), new ItemStack(ModBlocks.enertreePlanks)
		});
		
		//Tier 1 cable
		GameRegistry.addRecipe(new ItemStack(ModBlocks.cableblock, 4, 0), new Object[] {
			"iri",
			"www",
			"iri",
			Character.valueOf('i'), new ItemStack(ModItems.craftingItem, 1, 1),
			Character.valueOf('r'), new ItemStack(Items.redstone),
			Character.valueOf('w'), new ItemStack(ModBlocks.enertreePlanks)
		});
		
	}

}
