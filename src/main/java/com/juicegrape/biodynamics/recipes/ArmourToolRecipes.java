package com.juicegrape.biodynamics.recipes;

import com.juicegrape.biodynamics.items.ItemInfo;
import com.juicegrape.biodynamics.items.ModItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.ShapedOreRecipe;

public class ArmourToolRecipes {
	
	public static void addArmourRecipes(ItemStack material, ItemStack helm, ItemStack body, ItemStack legs, ItemStack boots) {
		GameRegistry.addRecipe(helm, new Object[] {
				"mmm",
				"m m",
				Character.valueOf('m'), material
		});
		GameRegistry.addRecipe(body, new Object[] {
				"m m",
				"mmm",
				"mmm",
				Character.valueOf('m'), material
		});
		GameRegistry.addRecipe(legs, new Object[] {
				"mmm",
				"m m",
				"m m",
				Character.valueOf('m'), material
		});
		GameRegistry.addRecipe(boots, new Object[] {
				"m m",
				"m m",
				Character.valueOf('m'), material
		});
	}
	
	public static void addTools(ItemStack material, ItemStack sword, ItemStack axe, ItemStack shovel, ItemStack pickaxe, ItemStack hoe) {
		GameRegistry.addRecipe(new ShapedOreRecipe(sword, new Object[] {
				"m",
				"m",
				"s",
				Character.valueOf('m'), material,
				Character.valueOf('s'), "stickWood"
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(axe, new Object[] {
				"mm",
				"ms",
				" s",
				Character.valueOf('m'), material,
				Character.valueOf('s'), "stickWood"
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(shovel, new Object[] {
				"m",
				"s",
				"s",
				Character.valueOf('m'), material,
				Character.valueOf('s'), "stickWood"
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(pickaxe, new Object[] {
				"mmm",
				" s ",
				" s ",
				Character.valueOf('m'), material,
				Character.valueOf('s'), "stickWood"
			}));
		
		GameRegistry.addRecipe(new ShapedOreRecipe(hoe, new Object[] {
				"mm",
				" s",
				" s",
				Character.valueOf('m'), material,
				Character.valueOf('s'), "stickWood"
			}));
		
		
	}
	
	public static void init() {
		//pink diamond armour
		addArmourRecipes(new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("pink_diamond")),
				new ItemStack(ModItems.pinkDiamondHelmet),
				new ItemStack(ModItems.pinkDiamondBody),
				new ItemStack(ModItems.pinkDiamondLegs),
				new ItemStack(ModItems.pinkDiamondBoots));
		
		//organic iron armour
		addArmourRecipes(new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_iron_ingot")),
				new ItemStack(ModItems.organicIronHelmet),
				new ItemStack(ModItems.organicIronBody),
				new ItemStack(ModItems.organicIronLegs),
				new ItemStack(ModItems.organicIronBoots));
		
		//organic gold armour
		addArmourRecipes(new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_gold_ingot")),
				new ItemStack(ModItems.organicGoldHelmet),
				new ItemStack(ModItems.organicGoldBody),
				new ItemStack(ModItems.organicGoldLegs),
				new ItemStack(ModItems.organicGoldBoots));
		
		//pink diamond tools
		addTools(new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("pink_diamond")),
				new ItemStack(ModItems.pinkDiamondSword),
				new ItemStack(ModItems.pinkDiamondAxe),
				new ItemStack(ModItems.pinkDiamondShovel),
				new ItemStack(ModItems.pinkDiamondPickaxe),
				new ItemStack(ModItems.pinkDiamondHoe));
		
		//organic iron tools
		addTools(new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_iron_ingot")),
				new ItemStack(ModItems.organicIronSword),
				new ItemStack(ModItems.organicIronAxe),
				new ItemStack(ModItems.organicIronShovel),
				new ItemStack(ModItems.organicIronPickaxe),
				new ItemStack(ModItems.organicIronHoe));
		
		//organic gold tools
		addTools(new ItemStack(ModItems.craftingItem, 1, ItemInfo.getCraftingItem("organic_gold_ingot")),
				new ItemStack(ModItems.organicGoldSword),
				new ItemStack(ModItems.organicGoldAxe),
				new ItemStack(ModItems.organicGoldShovel),
				new ItemStack(ModItems.organicGoldPickaxe),
				new ItemStack(ModItems.organicGoldHoe));
		
	}

}
