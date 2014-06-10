package com.juicegrape.biodynamics.blocks.common.energy;

import net.minecraft.world.World;

public interface IEnergyTransferer {
	
	public int getTransferRate(World world, int x, int y, int z);

}
