package com.juicegrape.biodynamics.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 
 * @author JuiceGrape
 *
 */
public class ModItems {
	
	public static Item specialBonemeal;
	public static Item craftingItem;
	
	public static List<Item> buckets = new ArrayList<Item>();;
	public static Item[] bucketsArray;

	public static void init() {
		
		specialBonemeal = new ItemSpecialBonemeals(ItemInfo.SPECIALBONEMEAL);
		GameRegistry.registerItem(specialBonemeal, ItemInfo.SPECIALBONEMEAL);
		
		craftingItem = new ItemCraftingItem(ItemInfo.CRAFTINGITEM);
		GameRegistry.registerItem(craftingItem, ItemInfo.CRAFTINGITEM);
		
		if (!buckets.isEmpty()) {
			bucketsArray = buckets.toArray(new Item[0]);
		}

	}
	
	public static void initOreDict() {
		OreDictionary.registerOre("dustIron", new ItemStack(craftingItem, 1, 3));
	}

}
