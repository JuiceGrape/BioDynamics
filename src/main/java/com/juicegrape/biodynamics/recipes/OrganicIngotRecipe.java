package com.juicegrape.biodynamics.recipes;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.juicegrape.biodynamics.items.ModItems;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import net.minecraftforge.oredict.ShapelessOreRecipe;

/**
 * 
 * @author JuiceGrape
 *
 */
public class OrganicIngotRecipe {
	
	public static final String oreDictOrganic1 = "BioDynamicsOrganics1";
	public static final String oreDictOrganic2 = "BioDynamicsOrganics2";
	public static final String oreDictOrganic3 = "BioDynamicsOrganics3";
	
	public static Map<ItemStack, Integer> materialMap = new HashMap<ItemStack, Integer>();
	public static Map<String, Integer> oreDictMaterialMap = new HashMap<String, Integer>();
	
	public static void initMap() {
		materialMap.put(new ItemStack(Items.slime_ball), 1);
		materialMap.put(new ItemStack(Items.potato), 1);
		materialMap.put(new ItemStack(Items.carrot), 1);
		materialMap.put(new ItemStack(Items.apple), 1);
		materialMap.put(new ItemStack(Items.reeds), 1);
		materialMap.put(new ItemStack(Items.egg), 1);
		materialMap.put(new ItemStack(Items.porkchop), 2);
		materialMap.put(new ItemStack(Items.beef), 2);
		materialMap.put(new ItemStack(Items.chicken), 2);
		materialMap.put(new ItemStack(Items.wheat), 1);
		materialMap.put(new ItemStack(Items.string), 1);
		materialMap.put(new ItemStack(Items.dye, 1, OreDictionary.WILDCARD_VALUE), 1);
		materialMap.put(new ItemStack(Items.sugar), 1);
		materialMap.put(new ItemStack(Items.magma_cream), 3);
		materialMap.put(new ItemStack(Items.fish, 1, OreDictionary.WILDCARD_VALUE), 1);
		materialMap.put(new ItemStack(Blocks.red_flower, 1, OreDictionary.WILDCARD_VALUE), 1);
		materialMap.put(new ItemStack(Blocks.yellow_flower), 1);
		materialMap.put(new ItemStack(Blocks.web, 1, OreDictionary.WILDCARD_VALUE), 1);
		materialMap.put(new ItemStack(Blocks.double_plant, 1, OreDictionary.WILDCARD_VALUE), 1);
		materialMap.put(new ItemStack(Blocks.brown_mushroom), 1);
		materialMap.put(new ItemStack(Blocks.red_mushroom), 1);
		materialMap.put(new ItemStack(Blocks.vine), 1);
		materialMap.put(new ItemStack(Blocks.waterlily), 1);
		materialMap.put(new ItemStack(Blocks.cactus), 1);
		
		oreDictMaterialMap.put("treeSapling", 1);
	}
	
	public static void init() {
		String name = "dustIron";
		String name2 = "dustGold";
		Iterator itr = materialMap.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<ItemStack, Integer> entry = (Entry<ItemStack, Integer>)itr.next();
			switch (entry.getValue()) {
			case 1:
				OreDictionary.registerOre(oreDictOrganic1, entry.getKey());
				break;
			case 2:
				OreDictionary.registerOre(oreDictOrganic2, entry.getKey());
				break;
			case 3:
				OreDictionary.registerOre(oreDictOrganic3, entry.getKey());
				break;
			}
		}
		
		itr = oreDictMaterialMap.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<String, Integer> entry = (Entry<String, Integer>)itr.next();
			Iterator<ItemStack> itr2 = OreDictionary.getOres(entry.getKey()).iterator();
			while (itr2.hasNext()) {
				switch (entry.getValue()) {
				case 1:
					OreDictionary.registerOre(oreDictOrganic1, itr2.next());
					break;
				case 2:
					OreDictionary.registerOre(oreDictOrganic2, itr2.next());
					break;
				case 3:
					OreDictionary.registerOre(oreDictOrganic3, itr2.next());
					break;
					
				}
			}
			
		}
		
		//Iron dust to organic iron dust
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.craftingItem, 1, 3), name, oreDictOrganic1));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.craftingItem, 2, 3), name, oreDictOrganic2));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.craftingItem, 3, 3), name, oreDictOrganic3));
		
		//Gold dust to organic gold dust, it creates 1 with a tier 2 organic, and 2 with a tier 3 organic
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.craftingItem, 1, 7), name2, oreDictOrganic2));
		GameRegistry.addRecipe(new ShapelessOreRecipe(new ItemStack(ModItems.craftingItem, 2, 7), name2, oreDictOrganic3));
		
		
	}

}
