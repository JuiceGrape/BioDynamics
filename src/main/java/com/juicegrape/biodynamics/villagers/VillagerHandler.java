package com.juicegrape.biodynamics.villagers;

import java.util.Random;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.blocks.ModBlocks;
import com.juicegrape.biodynamics.entities.EntityInfo;
import com.juicegrape.biodynamics.items.ModItems;
import com.juicegrape.biodynamics.recipes.CommonRecipes;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import cpw.mods.fml.common.registry.VillagerRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageCreationHandler;
import cpw.mods.fml.common.registry.VillagerRegistry.IVillageTradeHandler;

/**
 * 
 * @author JuiceGrape
 *
 */
public class VillagerHandler implements IVillageTradeHandler {
	
	public static final int VILLAGER_ID_DEF = 10;
	public static int VILLAGER_ID;
	
	public int ID;
	public ResourceLocation location;
	
	public VillagerHandler(int ID, String location) {
		this.ID = ID;
		this.location = new ResourceLocation(location);
	}

	@Override
	public void manipulateTradesForVillager(EntityVillager villager, MerchantRecipeList recipeList, Random random) {
		
		if (villager.getProfession() == ID) {
			recipeList.add(new MerchantRecipe(new ItemStack(Items.emerald, 9 + random.nextInt(4)), new ItemStack(ModBlocks.solarflower, 1)));
			recipeList.add(new MerchantRecipe(new ItemStack(Items.diamond, 1 + random.nextInt(4)), new ItemStack(ModItems.craftingItem, 1, 0)));
		}
		
	}
	
	
	public static void init() {
		specialInit(new VillagerHandler(VILLAGER_ID, EntityInfo.TEXTURE_LOCATION + "finn_villager.png"), new HutCreationHandler(), "BioDynamics:HutStructure");
	}
	
	public static void specialInit(VillagerHandler handler, IVillageCreationHandler creationHandler, String strucname) {
		VillagerRegistry.instance().registerVillagerId(handler.ID);
		VillagerRegistry.instance().registerVillagerSkin(handler.ID, handler.location);
		VillagerRegistry.instance().registerVillageTradeHandler(handler.ID, handler);
		VillagerRegistry.instance().registerVillageCreationHandler(creationHandler);
		
		try {
			MapGenStructureIO.func_143031_a(creationHandler.getComponentClass(), strucname);
		} catch (Throwable e) {
			System.out.println("fuck-----------------------------------------------------------");
		}
		
		
		
		
	}

}
