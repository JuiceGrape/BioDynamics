package com.juicegrape.biodynamics.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.item.Item;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModItems {
	
	public static Item specialBonemeal;
	
	public static List<Item> buckets = new ArrayList<Item>();;
	public static Item[] bucketsArray;

	public static void init() {
		
		specialBonemeal = new ItemSpecialBonemeals(ItemInfo.SPECIALBONEMEAL);
		GameRegistry.registerItem(specialBonemeal, ItemInfo.SPECIALBONEMEAL);
		
		if (!buckets.isEmpty()) {
			bucketsArray = buckets.toArray(new Item[0]);
		}

	}

}
