package com.juicegrape.biodynamics.tileentity.common;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

/**
 * 
 * @author JuiceGrape
 *
 */
public class TileEntityBattery extends TileEntity implements IEnergyHandler {
	
	protected EnergyStorage battery;
	
	protected int loss;
	
	
	
	public TileEntityBattery(int stored, int maxIn, int maxOut) {
		super();
		battery = new EnergyStorage(stored, maxIn, maxOut);
		loss = 0;
	}
	
	public void setLoss(int loss) {
		this.loss = loss;
	}
	
	public int getLoss() {
		return loss;
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
	public void updateEntity() {
		if (!worldObj.isRemote) {
			for (ForgeDirection dir : getOutputDirections()) {
				TileEntity ent = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
				if (ent != null && ent instanceof cofh.api.energy.IEnergyHandler) {
					cofh.api.energy.IEnergyHandler handler = (cofh.api.energy.IEnergyHandler)ent;
					int handleEnergy = getCorrectTransferValue(handler.receiveEnergy(dir.getOpposite(), battery.getMaxExtract(), true), this.extractEnergy(dir, battery.getMaxExtract(), true));
					if (handleEnergy != 0){
						handler.receiveEnergy(dir.getOpposite(), handleEnergy, false);
						this.extractEnergy(dir, handleEnergy , false);
					}
				}
			}
		}
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return true;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		for (ForgeDirection dir : getInputDirections()) {
			if (dir == from) {
				return battery.receiveEnergy(maxReceive, simulate);
			}
		}
		return 0;
		
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
	
	protected int getCorrectTransferValue(int transfer, int extract) {
		return Math.min(transfer, extract);
	}
	
	protected ForgeDirection[] getInputDirections() {
		return ForgeDirection.VALID_DIRECTIONS;
	}
	
	protected ForgeDirection[] getOutputDirections() {
		return ForgeDirection.VALID_DIRECTIONS;
	}
	
	public void printEnergy() {
		System.out.println("energyStored " + this.getEnergyStored(ForgeDirection.UNKNOWN));
		System.out.println("maxEnergyStored " + this.getMaxEnergyStored(ForgeDirection.UNKNOWN));
	}
	
	public void setMaxTransfer(int transfer) {
		battery.setMaxExtract(transfer);
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
