package com.juicegrape.biodynamics.tileentity;

import com.juicegrape.biodynamics.tileentity.common.IBioGenerator;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.IEnergyHandler;
import cofh.api.energy.EnergyStorage;

public class TileEntitySoil extends TileEntity implements IEnergyHandler {
	
	public EnergyStorage buffer;
	
	public TileEntitySoil(int tier) {
		buffer = new EnergyStorage(500 * tier, 100 * tier);
	}
	
	@Override
	public void updateEntity() {
		if (!worldObj.isRemote) {
			TileEntity ent = worldObj.getTileEntity(xCoord, yCoord + 1, zCoord);
			if (ent != null && ent instanceof IBioGenerator) {
				IBioGenerator gen = (IBioGenerator)ent;
				int handleEnergy = getCorrectTransferValue(gen.getPowerGen(), this.receiveEnergy(ForgeDirection.UP, buffer.getMaxReceive(), true));
				if (handleEnergy != 0) {
					this.receiveEnergy(ForgeDirection.UP, handleEnergy, false);
				}
			}
			
			for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				if (dir != ForgeDirection.UP) {
					TileEntity ent1 = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
					if (ent != null && ent1 instanceof cofh.api.energy.IEnergyHandler) {
						cofh.api.energy.IEnergyHandler handler = (cofh.api.energy.IEnergyHandler)ent1;
						int handleEnergy = getCorrectTransferValue(handler.receiveEnergy(dir.getOpposite(), buffer.getMaxExtract(), true), this.extractEnergy(dir, buffer.getMaxExtract(), true));
						if (handleEnergy != 0){
							handler.receiveEnergy(dir.getOpposite(), handleEnergy, false);
							this.extractEnergy(dir, handleEnergy, false);
						}
					}
				}
			}
			
		}
	}
	
	public void printEnergy() {
		System.out.println("energyStored " + this.getEnergyStored(ForgeDirection.UNKNOWN));
		System.out.println("maxEnergyStored " + this.getMaxEnergyStored(ForgeDirection.UNKNOWN));
	}

	@Override
	public boolean canConnectEnergy(ForgeDirection from) {
		return from != ForgeDirection.UP;
	}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive,
			boolean simulate) {
		return from != ForgeDirection.UP ? 0 : buffer.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract, boolean simulate) {
		return from == ForgeDirection.UP ? 0 : buffer.extractEnergy(maxExtract, simulate);
	}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return buffer.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return buffer.getMaxEnergyStored();
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		buffer.readFromNBT(nbt);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {

		super.writeToNBT(nbt);
		buffer.writeToNBT(nbt);
	}
	
	protected int getCorrectTransferValue(int transfer, int extract) {
		return Math.min(transfer, extract);
	}

}
