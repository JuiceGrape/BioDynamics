package com.juicegrape.biodynamics.recipes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

import com.juicegrape.biodynamics.items.ModItems;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameRegistry;

public class IngotCrushRecipes {
	
	public static Map<String, ItemStack> recipes = new HashMap<String, ItemStack>();
	
	
	
	private static ItemStack getOredictItem(String ingot) {
		List<ItemStack> results = OreDictionary.getOres(ingot);
		if (!results.isEmpty()) {
			return results.get(0);
		} else {
			return null;
		}

	}

	private static boolean hasBoth(String ingot) {
		boolean hasIngot = false;
		boolean hasDust = false;
		for (String entry : OreDictionary.getOreNames()) {
			if (entry.contains(ingot)) {
				if (entry.startsWith("ingot")) {
					hasIngot = true;
				} else if (entry.startsWith("dust")) {
					hasDust = true;
				}
			}
		}
		return hasIngot && hasDust;
	}


	public static void init() {
		List<String> ingots = new ArrayList<String>();

		for (String entry : OreDictionary.getOreNames()) {
			if (entry.contains("ingot")) {
				String name = entry.substring(5);
				if (!ingots.contains(name) && hasBoth(name)) {
					ingots.add(name);
				}
			}
		}

		Iterator<String> itr = ingots.iterator();

		while (itr.hasNext()) {
			String ing = itr.next();
			ItemStack dust = getOredictItem("dust" + ing);
			String ingot = "ingot" + ing;
			if (hasBoth(ing) && dust != null) {
				recipes.put(ingot, dust);
			}
		}
	}

}
