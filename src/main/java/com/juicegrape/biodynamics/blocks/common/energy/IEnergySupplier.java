package com.juicegrape.biodynamics.blocks.common.energy;

import net.minecraft.world.World;

public interface IEnergySupplier {
	
	public int getOutput(World world, int x, int y, int z);

}
