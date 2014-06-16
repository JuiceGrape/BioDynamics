package com.juicegrape.biodynamics.tileentity.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.cofh.api.energy.EnergyStorage;
import com.cofh.api.energy.IEnergyHandler;

public class TileEntityBattery extends TileEntity implements IEnergyHandler {
	
	protected EnergyStorage battery = new EnergyStorage(5000);
	
	public TileEntityBattery(int stored, int maxIn, int maxOut) {
		super();
	//	battery = new EnergyStorage(stored, maxIn, maxOut);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		battery.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		battery.writeToNBT(nbt);
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return battery.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return battery.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return battery.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return battery.getMaxEnergyStored();
	}
	
	
	
/*	public TileEntityBattery(int maxEnergy) {
		this.maxEnergy = maxEnergy;
		currentEnergy = 0;
	}
	
	public int getCurrentEnergy() {
		return currentEnergy;
	}
	
	public int getMaxEnergy() {
		return maxEnergy;
	}
	
	private void setMaxEnergy(int newMax) {
		maxEnergy = newMax;
	}
	
	/**
	 * adds energy to the current amount.
	 * @param energy the amount of energy to add
	 * @return the amount of energy deficit that cannot be stored because the storage is full.
	 *//*
	public int addEnergy(int energy) {
		currentEnergy += energy;
		int returnEnergy = 0;
		if (currentEnergy > maxEnergy) {
			returnEnergy = maxEnergy - currentEnergy;
			currentEnergy = maxEnergy;
		}
		return returnEnergy;
	}
	
	/**
	 * removes energy from the current amount.
	 * @param energy the amount of energy to remove
	 * @return the amount of energy that can be removed, it returns @param energy if it can remove the full amount.
	 *//*
	public int removeEnergy(int energy) {
		int returnEnergy = energy;
		if (energy > currentEnergy) {
			returnEnergy = currentEnergy - energy;
			currentEnergy = 0;
		} else {
			currentEnergy -= energy;
		}
		return returnEnergy;
	} */

}
