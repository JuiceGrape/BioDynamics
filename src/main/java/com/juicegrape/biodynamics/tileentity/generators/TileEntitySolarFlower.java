package com.juicegrape.biodynamics.tileentity.generators;

import net.minecraft.tileentity.TileEntity;

import com.juicegrape.biodynamics.tileentity.common.IBioGenerator;

public class TileEntitySolarFlower extends TileEntity implements IBioGenerator {

	@Override
	public int getPowerGen() {
		if (this.worldObj.canBlockSeeTheSky(xCoord, yCoord, zCoord) && worldObj.isDaytime() && !worldObj.isRaining()) {
			return 10;
		} else {
			return 0;
		}
	}

}
