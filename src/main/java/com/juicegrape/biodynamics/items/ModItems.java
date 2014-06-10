package com.juicegrape.biodynamics.items;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.item.Item;

public class ModItems {
	
	public static Item specialBonemeal;

	public static void init() {
		
		specialBonemeal = new ItemSpecialBonemeals(ItemInfo.SPECIALBONEMEAL);
		GameRegistry.registerItem(specialBonemeal, ItemInfo.SPECIALBONEMEAL);
		

	}

}
