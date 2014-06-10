package com.juicegrape.biodynamics.blocks.common.energy;

import net.minecraft.world.World;

public interface IEnergyReceiver {
	
	public int getMaxEnergy(World world, int x, int y, int z);
	
	public int getCurrentEnergy(World world, int x, int y, int z);
	
	public int getInputRate(World world, int x, int y, int z);

}
