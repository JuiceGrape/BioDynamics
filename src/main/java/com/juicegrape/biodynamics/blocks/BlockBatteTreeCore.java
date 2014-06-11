package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.blocks.common.BioTileEntityBlock;
import com.juicegrape.biodynamics.blocks.common.energy.IEnergyReceiver;
import com.juicegrape.biodynamics.blocks.common.energy.IEnergySupplier;
import com.juicegrape.biodynamics.blocks.common.energy.IEnergyTransferer;
import com.juicegrape.biodynamics.tileentity.TileEntityBatteTreeCore;

public class BlockBatteTreeCore extends BioTileEntityBlock implements IEnergySupplier, IEnergyReceiver {

	protected BlockBatteTreeCore(String name) {
		super(Material.wood, name);
	}

	@Override
	public int getMaxEnergy(World world, int x, int y, int z) {
		return 0;
	}

	@Override
	public int getCurrentEnergy(World world, int x, int y, int z) {
		return 0;
	}

	@Override
	public int getInputRate(World world, int x, int y, int z) {
		return 100;
	}

	@Override
	public int getOutputRate(World world, int x, int y, int z) {
		return 100;
	}

}
