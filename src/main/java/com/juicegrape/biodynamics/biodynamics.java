package com.juicegrape.biodynamics;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;

import com.juicegrape.biodynamics.blocks.ModBlocks;
import com.juicegrape.biodynamics.config.ConfigHandler;
import com.juicegrape.biodynamics.items.ModItems;
import com.juicegrape.biodynamics.network.BucketHandler;
import com.juicegrape.biodynamics.network.ModEventHandler;
import com.juicegrape.biodynamics.proxies.CommonProxy;
import com.juicegrape.biodynamics.recipes.CommonRecipes;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

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
	}

	@EventHandler
	public void init(FMLInitializationEvent event) {
		
		CommonRecipes.initCommonRecipes();
		proxy.registerTileEntities();
		
		MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);
		MinecraftForge.EVENT_BUS.register(ModEventHandler.instance);

	}

	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
		

	}

}
