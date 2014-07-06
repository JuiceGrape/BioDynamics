package com.juicegrape.biodynamics.tileentity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;

public class TileEntityCable extends TileEntity implements IEnergyHandler {
	
	int times = 0;
	
	int tier;
	
	private List<ForgeDirection> exclusionSimulate;
	
	private static final String TIERNBT = "energy_cable_tier";
	
	public TileEntityCable(int tier) {
		super();
		this.tier = tier;
		exclusionSimulate = new ArrayList<ForgeDirection>();
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}
	
	@Override
	public void updateEntity() {
		exclusionSimulate.clear();
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		
		int sendValue = getCorrectTransferValue(maxReceive, this.getMaxEnergyStored(from));
	//	System.out.println("sendValue " + sendValue);
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			if (dir != from && shouldContinue(simulate, dir)) {
				int retValue = 0;
				if (worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ) instanceof IEnergyHandler) {
					IEnergyHandler handler = ((IEnergyHandler)worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ));
					retValue = handler.receiveEnergy(dir.getOpposite(), sendValue, simulate);
					//System.out.println("retValue " + retValue);
					exclusionSimulate.add(dir);
					
					
					
					if (retValue != 0) {
						return retValue;
					}
				}
			}
		}
		return 0;
	}
	
	protected int getCorrectTransferValue(int transfer, int extract) {
		return Math.min(transfer, extract);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return 0;
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return 200;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		tier = nbt.getInteger(TIERNBT);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		nbt.setInteger(TIERNBT, tier);
	}
	
	protected boolean shouldContinue(boolean simulate, ForgeDirection dir) {
		if (!simulate) {
			return true;
		} else {
			return !exclusionSimulate.contains(dir);
		}
	}
	
	
}
