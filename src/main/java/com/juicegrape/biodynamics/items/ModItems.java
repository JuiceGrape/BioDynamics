package com.juicegrape.biodynamics.items;

import java.util.ArrayList;
import java.util.List;

import com.juicegrape.biodynamics.biodynamics;

import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
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
	
	public static Item organicIronHelmet;
	public static Item organicIronBody;
	public static Item organicIronLegs;
	public static Item organicIronBoots;
	
	public static Item organicGoldHelmet;
	public static Item organicGoldBody;
	public static Item organicGoldLegs;
	public static Item organicGoldBoots;
	
	public static Item pinkDiamondHelmet;
	public static Item pinkDiamondBody;
	public static Item pinkDiamondLegs;
	public static Item pinkDiamondBoots;

	public static void init() {
		
		specialBonemeal = new ItemSpecialBonemeals(ItemInfo.SPECIALBONEMEAL);
		GameRegistry.registerItem(specialBonemeal, ItemInfo.SPECIALBONEMEAL);
		
		craftingItem = new ItemCraftingItem(ItemInfo.CRAFTINGITEM);
		GameRegistry.registerItem(craftingItem, ItemInfo.CRAFTINGITEM);
		
		if (!buckets.isEmpty()) {
			bucketsArray = buckets.toArray(new Item[0]);
		}
		
		organicIronHelmet = new ItemOrganicArmour(ItemInfo.ORGANICIRONARMOUR[0], ItemArmor.ArmorMaterial.IRON, biodynamics.proxy.addArmor(ItemInfo.ORGANICIRONARMOURRENDER), 0);
		organicIronBody = new ItemOrganicArmour(ItemInfo.ORGANICIRONARMOUR[1], ItemArmor.ArmorMaterial.IRON, biodynamics.proxy.addArmor(ItemInfo.ORGANICIRONARMOURRENDER), 1);
		organicIronLegs = new ItemOrganicArmour(ItemInfo.ORGANICIRONARMOUR[2], ItemArmor.ArmorMaterial.IRON, biodynamics.proxy.addArmor(ItemInfo.ORGANICIRONARMOURRENDER), 2);
		organicIronBoots = new ItemOrganicArmour(ItemInfo.ORGANICIRONARMOUR[3], ItemArmor.ArmorMaterial.IRON, biodynamics.proxy.addArmor(ItemInfo.ORGANICIRONARMOURRENDER), 3);
		
		GameRegistry.registerItem(organicIronHelmet, ItemInfo.ORGANICIRONARMOUR[0]);
		GameRegistry.registerItem(organicIronBody, ItemInfo.ORGANICIRONARMOUR[1]);
		GameRegistry.registerItem(organicIronLegs, ItemInfo.ORGANICIRONARMOUR[2]);
		GameRegistry.registerItem(organicIronBoots, ItemInfo.ORGANICIRONARMOUR[3]);
		
		organicGoldHelmet = new ItemOrganicArmour(ItemInfo.ORGANICGOLDARMOUR[0], ItemArmor.ArmorMaterial.GOLD, biodynamics.proxy.addArmor(ItemInfo.ORGANICGOLDARMOURRENDER), 0);
		organicGoldBody = new ItemOrganicArmour(ItemInfo.ORGANICGOLDARMOUR[1], ItemArmor.ArmorMaterial.GOLD, biodynamics.proxy.addArmor(ItemInfo.ORGANICGOLDARMOURRENDER), 1);
		organicGoldLegs = new ItemOrganicArmour(ItemInfo.ORGANICGOLDARMOUR[2], ItemArmor.ArmorMaterial.GOLD, biodynamics.proxy.addArmor(ItemInfo.ORGANICGOLDARMOURRENDER), 2);
		organicGoldBoots = new ItemOrganicArmour(ItemInfo.ORGANICGOLDARMOUR[3], ItemArmor.ArmorMaterial.GOLD, biodynamics.proxy.addArmor(ItemInfo.ORGANICGOLDARMOURRENDER), 3);
		
		GameRegistry.registerItem(organicGoldHelmet, ItemInfo.ORGANICGOLDARMOUR[0]);
		GameRegistry.registerItem(organicGoldBody, ItemInfo.ORGANICGOLDARMOUR[1]);
		GameRegistry.registerItem(organicGoldLegs, ItemInfo.ORGANICGOLDARMOUR[2]);
		GameRegistry.registerItem(organicGoldBoots, ItemInfo.ORGANICGOLDARMOUR[3]);
		
		pinkDiamondHelmet = new ItemOrganicArmour(ItemInfo.PINKDIAMONDARMOUR[0], ItemArmor.ArmorMaterial.DIAMOND, biodynamics.proxy.addArmor(ItemInfo.PINKDIAMONDARMOURRENDER), 0);
		pinkDiamondBody = new ItemOrganicArmour(ItemInfo.PINKDIAMONDARMOUR[1], ItemArmor.ArmorMaterial.DIAMOND, biodynamics.proxy.addArmor(ItemInfo.PINKDIAMONDARMOURRENDER), 1);
		pinkDiamondLegs = new ItemOrganicArmour(ItemInfo.PINKDIAMONDARMOUR[2], ItemArmor.ArmorMaterial.DIAMOND, biodynamics.proxy.addArmor(ItemInfo.PINKDIAMONDARMOURRENDER), 2);
		pinkDiamondBoots = new ItemOrganicArmour(ItemInfo.PINKDIAMONDARMOUR[3], ItemArmor.ArmorMaterial.DIAMOND, biodynamics.proxy.addArmor(ItemInfo.PINKDIAMONDARMOURRENDER), 3);
		
		GameRegistry.registerItem(pinkDiamondHelmet, ItemInfo.PINKDIAMONDARMOUR[0]);
		GameRegistry.registerItem(pinkDiamondBody, ItemInfo.PINKDIAMONDARMOUR[1]);
		GameRegistry.registerItem(pinkDiamondLegs, ItemInfo.PINKDIAMONDARMOUR[2]);
		GameRegistry.registerItem(pinkDiamondBoots, ItemInfo.PINKDIAMONDARMOUR[3]);


	}
	
	public static void initOreDict() {
		OreDictionary.registerOre("dustIron", new ItemStack(craftingItem, 1, 4));
		OreDictionary.registerOre("dustGold", new ItemStack(craftingItem, 1, 8));
		
		OreDictionary.registerOre("dustOrganicIron", new ItemStack(craftingItem, 1, 3));
		OreDictionary.registerOre("dustOrganicGold", new ItemStack(craftingItem, 1, 7));
		
		OreDictionary.registerOre("ingotOrganicIron", new ItemStack(craftingItem, 1, 1));
		OreDictionary.registerOre("ingotOrganicGold", new ItemStack(craftingItem, 1, 5));
		
		OreDictionary.registerOre("nuggetOrganicIron", new ItemStack(craftingItem, 1, 2));
		OreDictionary.registerOre("nuggetOrganicGold", new ItemStack(craftingItem, 1, 6));
	}

}
