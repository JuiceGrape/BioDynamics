package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;

import com.juicegrape.biodynamics.blocks.generators.BlockBurningFlower;
import com.juicegrape.biodynamics.blocks.generators.BlockSolarFlower;
import com.juicegrape.biodynamics.items.itemBlocks.ItemBlockEnergySoil;
import com.juicegrape.biodynamics.items.itemBlocks.ItemBlockMinerals;

import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 
 * @author JuiceGrape
 *
 */
public class ModBlocks {

	public static Block enerTreeSapling;
	public static Fluid fluidRedstoneWater;
	public static Block redstoneWater;
	public static Block enertreeLeaves;
	public static Block enertreeLogs;
	public static Block enertreePlanks;
	public static Block enertreeFurnace;
	public static Block battetreeCore;
	public static Block energysoil;
	public static Block solarflower;
	public static Block burningflower;
	public static Block mineralBlocks;
	public static Block cableblock;
	
	
	
	public static void init() {
		
		enerTreeSapling = new BlockEnerTreeSapling();
		GameRegistry.registerBlock(enerTreeSapling, BlockInfo.ENERTREESAPLING);
		
		fluidRedstoneWater = new Fluid(BlockInfo.REDSTONEWATER);
		fluidRedstoneWater.setDensity(1300);
		fluidRedstoneWater.setLuminosity(15);
		fluidRedstoneWater.setViscosity(1300);
		FluidRegistry.registerFluid(fluidRedstoneWater);
		
		redstoneWater = new BlockRedstoneWater(fluidRedstoneWater, BlockInfo.REDSTONEWATER);
		GameRegistry.registerBlock(redstoneWater, BlockInfo.REDSTONEWATER);
		
		enertreeLeaves = new BlockEnerTreeLeaves();
		GameRegistry.registerBlock(enertreeLeaves, BlockInfo.ENERTREELEAVES);
		
		enertreeLogs = new BlockEnerTreeLogs();
		GameRegistry.registerBlock(enertreeLogs, BlockInfo.ENERTREELOGS);
		
		enertreePlanks = new BlockEnerTreePlanks(BlockInfo.ENERTREEPLANKS);
		GameRegistry.registerBlock(enertreePlanks, BlockInfo.ENERTREEPLANKS);
		
		enertreeFurnace = new BlockEnerTreeFurnace(BlockInfo.ENERTREEFURNACE);
		GameRegistry.registerBlock(enertreeFurnace, BlockInfo.ENERTREEFURNACE);
		
		battetreeCore = new BlockBatteTreeCore(BlockInfo.BATTETREECORE);
		GameRegistry.registerBlock(battetreeCore, BlockInfo.BATTETREECORE);
		
		energysoil = new BlockEnergySoil(BlockInfo.ENERGETICSOIL);
		GameRegistry.registerBlock(energysoil, ItemBlockEnergySoil.class, BlockInfo.ENERGETICSOIL);
		
		solarflower = new BlockSolarFlower(BlockInfo.SOLARFLOWER);
		GameRegistry.registerBlock(solarflower, BlockInfo.SOLARFLOWER);
		
		burningflower = new BlockBurningFlower(BlockInfo.BURNINGFLOWER);
		GameRegistry.registerBlock(burningflower, BlockInfo.BURNINGFLOWER);
		
		mineralBlocks = new BlockCompressedMinerals(BlockInfo.MINERALBLOCK);
		GameRegistry.registerBlock(mineralBlocks, ItemBlockMinerals.class, BlockInfo.MINERALBLOCK);
		
		cableblock = new BlockEnergyCable(BlockInfo.CABLE);
		GameRegistry.registerBlock(cableblock, BlockInfo.CABLE);
		
		addOreDict();
		

	}
	
	public static void addOreDict() {
		OreDictionary.registerOre("treeSapling", enerTreeSapling);
		OreDictionary.registerOre("treeLeaves", enertreeLeaves);
		OreDictionary.registerOre("logWood", enertreeLogs);
		OreDictionary.registerOre("plankWood", enertreePlanks);
	}

}
