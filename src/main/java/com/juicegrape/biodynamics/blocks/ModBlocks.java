package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.oredict.OreDictionary;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static Block enerTreeSapling;
	public static Fluid fluidRedstoneWater;
	public static Block redstoneWater;
	public static Block enertreeLeaves;
	public static Block enertreeLogs;
	public static Block enertreePlanks;
	public static Block enertreeFurnace;
	public static Block battetreeCore;
	
	
	
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
		
		addOreDict();
		

	}
	
	public static void addOreDict() {
		OreDictionary.registerOre(OreDictionary.getOreName(OreDictionary.getOreID(new ItemStack(Blocks.sapling))), enerTreeSapling);
		OreDictionary.registerOre(OreDictionary.getOreName(OreDictionary.getOreID(new ItemStack(Blocks.leaves))), enertreeLeaves);
		OreDictionary.registerOre(OreDictionary.getOreName(OreDictionary.getOreID(new ItemStack(Blocks.log))), enertreeLogs);
		OreDictionary.registerOre(OreDictionary.getOreName(OreDictionary.getOreID(new ItemStack(Blocks.planks))), enertreePlanks);
	}

}
