package com.juicegrape.biodynamics.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

import com.cofh.api.energy.EnergyStorage;
import com.cofh.api.energy.IEnergyHandler;

public class TileEntityBatteTreeCore extends TileEntity implements IEnergyHandler {
	
	EnergyStorage battery = new EnergyStorage(5000);

	public TileEntityBatteTreeCore() {
		super();
		printEnergy();
		this.receiveEnergy(ForgeDirection.UNKNOWN, 200, false);
		printEnergy();
	}
	
	public void printEnergy() {
		System.out.println(this.getEnergyStored(ForgeDirection.UNKNOWN));
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
	
	

}
