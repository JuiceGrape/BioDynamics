package com.juicegrape.biodynamics.items;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.items.tools.ItemHappinessMeter;
import com.juicegrape.biodynamics.items.tools.ItemOrganicAxe;
import com.juicegrape.biodynamics.items.tools.ItemOrganicHoe;
import com.juicegrape.biodynamics.items.tools.ItemOrganicPickaxe;
import com.juicegrape.biodynamics.items.tools.ItemOrganicShovel;
import com.juicegrape.biodynamics.items.tools.ItemOrganicSword;

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
	
	public static Item organicIronSword;
	public static Item organicGoldSword;
	public static Item pinkDiamondSword;
	
	public static Item organicIronPickaxe;
	public static Item organicGoldPickaxe;
	public static Item pinkDiamondPickaxe;
	
	public static Item organicIronAxe;
	public static Item organicGoldAxe;
	public static Item pinkDiamondAxe;
	
	public static Item organicIronShovel;
	public static Item organicGoldShovel;
	public static Item pinkDiamondShovel;
	
	public static Item organicIronHoe;
	public static Item organicGoldHoe;
	public static Item pinkDiamondHoe;
	
	public static Item mortarAndPestle;
	
	public static Item happinessMeter;

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
		
		organicIronSword = new ItemOrganicSword(ToolMaterial.IRON, ItemInfo.ORGANIC_SWORDS[0]);
		organicGoldSword = new ItemOrganicSword(ToolMaterial.GOLD, ItemInfo.ORGANIC_SWORDS[1]);
		pinkDiamondSword = new ItemOrganicSword(ToolMaterial.EMERALD, ItemInfo.ORGANIC_SWORDS[2]);
		
		GameRegistry.registerItem(organicIronSword, ItemInfo.ORGANIC_SWORDS[0]);
		GameRegistry.registerItem(organicGoldSword, ItemInfo.ORGANIC_SWORDS[1]);
		GameRegistry.registerItem(pinkDiamondSword, ItemInfo.ORGANIC_SWORDS[2]);
		
		organicIronPickaxe = new ItemOrganicPickaxe(ToolMaterial.IRON, ItemInfo.ORGANIC_PICKAXES[0]);
		organicGoldPickaxe = new ItemOrganicPickaxe(ToolMaterial.GOLD, ItemInfo.ORGANIC_PICKAXES[1]);
		pinkDiamondPickaxe = new ItemOrganicPickaxe(ToolMaterial.EMERALD, ItemInfo.ORGANIC_PICKAXES[2]);
		
		GameRegistry.registerItem(organicIronPickaxe, ItemInfo.ORGANIC_PICKAXES[0]);
		GameRegistry.registerItem(organicGoldPickaxe, ItemInfo.ORGANIC_PICKAXES[1]);
		GameRegistry.registerItem(pinkDiamondPickaxe, ItemInfo.ORGANIC_PICKAXES[2]);
		
		organicIronAxe = new ItemOrganicAxe(ToolMaterial.IRON, ItemInfo.ORGANIC_AXES[0]);
		organicGoldAxe = new ItemOrganicAxe(ToolMaterial.GOLD, ItemInfo.ORGANIC_AXES[1]);
		pinkDiamondAxe = new ItemOrganicAxe(ToolMaterial.EMERALD, ItemInfo.ORGANIC_AXES[2]);
		
		GameRegistry.registerItem(organicIronAxe, ItemInfo.ORGANIC_AXES[0]);
		GameRegistry.registerItem(organicGoldAxe, ItemInfo.ORGANIC_AXES[1]);
		GameRegistry.registerItem(pinkDiamondAxe, ItemInfo.ORGANIC_AXES[2]);
		
		organicIronShovel = new ItemOrganicShovel(ToolMaterial.IRON, ItemInfo.ORGANIC_SHOVELS[0]);
		organicGoldShovel = new ItemOrganicShovel(ToolMaterial.GOLD, ItemInfo.ORGANIC_SHOVELS[1]);
		pinkDiamondShovel = new ItemOrganicShovel(ToolMaterial.EMERALD, ItemInfo.ORGANIC_SHOVELS[2]);
		
		GameRegistry.registerItem(organicIronShovel, ItemInfo.ORGANIC_SHOVELS[0]);
		GameRegistry.registerItem(organicGoldShovel, ItemInfo.ORGANIC_SHOVELS[1]);
		GameRegistry.registerItem(pinkDiamondShovel, ItemInfo.ORGANIC_SHOVELS[2]);
		
		organicIronHoe = new ItemOrganicHoe(ToolMaterial.IRON, ItemInfo.ORGANIC_HOES[0]);
		organicGoldHoe = new ItemOrganicHoe(ToolMaterial.GOLD, ItemInfo.ORGANIC_HOES[1]);
		pinkDiamondHoe = new ItemOrganicHoe(ToolMaterial.EMERALD, ItemInfo.ORGANIC_HOES[2]);
		
		GameRegistry.registerItem(organicIronHoe, ItemInfo.ORGANIC_HOES[0]);
		GameRegistry.registerItem(organicGoldHoe, ItemInfo.ORGANIC_HOES[1]);
		GameRegistry.registerItem(pinkDiamondHoe, ItemInfo.ORGANIC_HOES[2]);
		
		mortarAndPestle = new ItemMortarPestle(ItemInfo.ITEMMORTARPESTLE);
		
		GameRegistry.registerItem(mortarAndPestle, ItemInfo.ITEMMORTARPESTLE);
		
		happinessMeter = new ItemHappinessMeter(ItemInfo.ITEMHAPPINESSMETER);
		
		GameRegistry.registerItem(happinessMeter, ItemInfo.ITEMHAPPINESSMETER);
		
		

	}
	
	public static void initOreDict() {
		
		OreDictionary.registerOre("gemDiamond", new ItemStack(craftingItem));
		
		OreDictionary.registerOre("dustIron", new ItemStack(craftingItem, 1, ItemInfo.getCraftingItem("iron_dust")));
		OreDictionary.registerOre("dustGold", new ItemStack(craftingItem, 1, ItemInfo.getCraftingItem("gold_dust")));
		
		OreDictionary.registerOre("dustOrganicIron", new ItemStack(craftingItem, 1, ItemInfo.getCraftingItem("organic_iron_dust")));
		OreDictionary.registerOre("dustOrganicGold", new ItemStack(craftingItem, 1, ItemInfo.getCraftingItem("organic_gold_dust")));
		
		OreDictionary.registerOre("ingotOrganicIron", new ItemStack(craftingItem, 1, ItemInfo.getCraftingItem("organic_iron_ingot")));
		OreDictionary.registerOre("ingotOrganicGold", new ItemStack(craftingItem, 1, ItemInfo.getCraftingItem("organic_gold_ingot")));
		
		OreDictionary.registerOre("nuggetOrganicIron", new ItemStack(craftingItem, 1, ItemInfo.getCraftingItem("organic_iron_nugget")));
		OreDictionary.registerOre("nuggetOrganicGold", new ItemStack(craftingItem, 1, ItemInfo.getCraftingItem("organic_gold_nugget")));
		
		OreDictionary.registerOre("dustOrganicGlass", new ItemStack(craftingItem, 1, ItemInfo.getCraftingItem("organic_glass_dust")));
		OreDictionary.registerOre("dustGlass", new ItemStack(craftingItem, 1, ItemInfo.getCraftingItem("glass_dust")));
	}

}
