package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.material.Material;
import net.minecraftforge.fluids.Fluid;

import com.juicegrape.biodynamics.blocks.common.BioLiquid;

public class BlockRedstoneWater extends BioLiquid {

	public BlockRedstoneWater(Fluid fluid, String name) {
		super(fluid, Material.water, name);
		fluid.setBlock(this);
	}

}
