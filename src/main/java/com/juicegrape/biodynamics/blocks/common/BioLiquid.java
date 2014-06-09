package com.juicegrape.biodynamics.blocks.common;

import com.juicegrape.biodynamics.biodynamics;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.BlockFluidFinite;
import net.minecraftforge.fluids.Fluid;

public class BioLiquid extends BlockFluidClassic {

	public BioLiquid(Fluid fluid, Material material, String name) {
		super(fluid, material);
		setBlockName(name);
		setCreativeTab(biodynamics.bioTab);
	}

}
