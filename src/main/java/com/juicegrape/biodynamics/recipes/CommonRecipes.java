package com.juicegrape.biodynamics.recipes;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapedOreRecipe;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.juicegrape.biodynamics.blocks.ModBlocks;
import com.juicegrape.biodynamics.items.ItemInfo;
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
		GameRegistry.addSmelting(new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_iron_dust")), new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_iron_ingot")), 200);
		//ingots to nugget
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.craftingItem, 9, ItemInfo.getCraftingItem("organic_iron_nugget")), new ItemStack (ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_iron_ingot")));
		//nugget to ingots
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_iron_ingot")), new Object[] {
			"aaa",
			"aaa",
			"aaa",
			Character.valueOf('a'), new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_iron_nugget"))
		});
		//block to ingots
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.craftingItem, 9, ItemInfo.getCraftingItem("organic_iron_ingot")), new ItemStack (ModBlocks.mineralBlocks, 1, 1));
		//ingots to block
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.mineralBlocks, 1, 1), new Object[] {
			"aaa",
			"aaa",
			"aaa",
			Character.valueOf('a'), new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_iron_ingot"))
		});
		
		//Organics gold
		//dust to ingot
		GameRegistry.addSmelting(new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_gold_dust")), new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_gold_ingot")), 200);
		//ingot to nuggets
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.craftingItem, 9, ItemInfo.getCraftingItem("organic_gold_nugget")), new ItemStack (ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_gold_ingot")));
		//nugget to ingots
		GameRegistry.addShapedRecipe(new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_gold_ingot")), new Object[] {
			"aaa",
			"aaa",
			"aaa",
			Character.valueOf('a'), new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_gold_nugget"))
		});
		//block to ingots
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.craftingItem, 9, ItemInfo.getCraftingItem("organic_gold_ingot")), new ItemStack (ModBlocks.mineralBlocks, 1, 2));
		//ingots to block
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.mineralBlocks, 1, 2), new Object[] {
			"aaa",
			"aaa",
			"aaa",
			Character.valueOf('a'), new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_gold_ingot"))
		});
		
		//Pink diamond
		//block to gems
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.craftingItem, 9, ItemInfo.getCraftingItem("pink_diamond")), new ItemStack (ModBlocks.mineralBlocks, 1, 0));
		//gems to block
		GameRegistry.addShapedRecipe(new ItemStack(ModBlocks.mineralBlocks, 1, 0), new Object[] {
			"aaa",
			"aaa",
			"aaa",
			Character.valueOf('a'), new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("pink_diamond"))
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
		
		//Mortar and pestle
		GameRegistry.addRecipe(new ShapedOreRecipe(ModItems.mortarAndPestle, new Object[] {
				"b/b",
				" b ",
				Character.valueOf('/'), new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("pestle")),
				Character.valueOf('b'), "ingotIron"
		}));
		
		//Mortar and pestle
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("pestle")), new Object[] {
				"  /",
				" / ",
				"d  ",
				Character.valueOf('/'), "stickWood",
				Character.valueOf('d'), "gemDiamond"	
		}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(new ItemStack(ModBlocks.mutatinator), new Object[] {
			"III",
			"TFT",
			"gCg",
			Character.valueOf('I'), new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_iron_ingot")),
			Character.valueOf('T'), new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("craft_tank")),
			Character.valueOf('F'), Blocks.furnace,
			Character.valueOf('g'), new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_gold_nugget")),
			Character.valueOf('C'), Items.cauldron
		}));
		
		GameRegistry.addRecipe(new ItemStack(ModItems.craftingItem, 1, 10), new Object[] {
			" I ",
			"IGI",
			" I ",
			Character.valueOf('I'), Items.iron_ingot,
			Character.valueOf('G'), Blocks.glass
		});
		
		GameRegistry.addShapelessRecipe(new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("glass_dust")), new ItemStack (Blocks.glass), new ItemStack(ModItems.mortarAndPestle, 1, OreDictionary.WILDCARD_VALUE));
		
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("glass_dust")), new ItemStack(ModItems.mortarAndPestle, 1, OreDictionary.WILDCARD_VALUE), "blockGlassColorless"));
		
		GameRegistry.addSmelting(new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_glass_dust")), new ItemStack(ModBlocks.naturalglass, 1), 200);
		
		ArmourToolRecipes.init();
		
		
	}

}
