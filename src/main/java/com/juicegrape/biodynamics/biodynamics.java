package com.juicegrape.biodynamics;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.common.MinecraftForge;

import com.juicegrape.biodynamics.blocks.ModBlocks;
import com.juicegrape.biodynamics.config.ConfigHandler;
import com.juicegrape.biodynamics.items.ModItems;
import com.juicegrape.biodynamics.network.BucketHandler;
import com.juicegrape.biodynamics.network.ModEventHandler;
import com.juicegrape.biodynamics.proxies.CommonProxy;
import com.juicegrape.biodynamics.recipes.CommonRecipes;
import com.juicegrape.biodynamics.recipes.OrganicIngotRecipe;
import com.juicegrape.biodynamics.villagers.VillagerHandler;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.VillagerRegistry;

/**
 * 
 * @author JuiceGrape
 *
 */
@Mod(modid = ModInfo.ID, name = ModInfo.NAME, version = ModInfo.VERSION)
public class biodynamics {

	@Instance(ModInfo.ID)
	public static biodynamics instance;

	@SidedProxy(clientSide = ModInfo.CLIENTPROXY, serverSide = ModInfo.COMMONPROXY)
	public static CommonProxy proxy;

	public static CreativeTabs bioTab = new CreativeTabs(ModInfo.ID) {

		@Override
		public Item getTabIconItem() {
			return Item.getItemFromBlock(ModBlocks.enerTreeSapling);
		}

	};

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.init(event.getSuggestedConfigurationFile());

		ModBlocks.init();
		ModItems.init();
		
		OrganicIngotRecipe.initMap();
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		CommonRecipes.initCommonRecipes();
		
		proxy.registerTileEntities();
		
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ModEventHandler.INSTANCE);
		
		NetworkRegistry.INSTANCE.registerGuiHandler(this, proxy);
		
		VillagerHandler.init();
		
		ModItems.initOreDict();
		
		proxy.initRenderers();

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		OrganicIngotRecipe.init();

	}

}
