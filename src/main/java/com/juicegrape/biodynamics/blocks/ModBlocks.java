package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class ModBlocks {

	public static Block enerTreeSapling;
	public static Fluid fluidRedstoneWater;
	public static Block redstoneWater;
	
	
	
	public static void init() {
		
		enerTreeSapling = new BlockEnerTreeSapling();
		GameRegistry.registerBlock(enerTreeSapling, BlockInfo.ENERTREESAPLING);
		
		fluidRedstoneWater = new Fluid(BlockInfo.REDSTONEWATER);
		FluidRegistry.registerFluid(fluidRedstoneWater);
		
		redstoneWater = new BlockRedstoneWater(fluidRedstoneWater, BlockInfo.REDSTONEWATER);
		GameRegistry.registerBlock(redstoneWater, BlockInfo.REDSTONEWATER);

	}

}
