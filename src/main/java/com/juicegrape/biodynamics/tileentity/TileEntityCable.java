package com.juicegrape.biodynamics.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

public class TileEntityCable extends TileEntity implements IEnergyHandler {
	
	protected EnergyStorage battery;

	public TileEntityCable(int tier) {
		super();
		battery = new EnergyStorage(800 * tier, 400 * tier, 400 * tier);
	}
	
	@Override
	public void updateEntity() {
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity te = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
			if (te != null && te instanceof TileEntityCable) {
				TileEntityCable cable = (TileEntityCable)te;
				int energyOne = (this.getEnergyStored(dir.getOpposite()) + cable.getEnergyStored(dir)) / 2;
				int energyTwo = energyOne;
				//TODO: make sure it doesn't go over it's limits
				this.setEnergyStored(energyOne);
				cable.setEnergyStored(energyTwo);
			}
		}
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity te = worldObj.getTileEntity(xCoord + dir.offsetX, yCoord + dir.offsetY, zCoord + dir.offsetZ);
			if (te != null && te instanceof IEnergyHandler && !(te instanceof TileEntityCable)) {
				IEnergyHandler handler = (IEnergyHandler)te;
				int handleEnergy = getCorrectTransferValue(handler.receiveEnergy(dir.getOpposite(), battery.getMaxExtract(), true), this.extractEnergy(dir, battery.getMaxExtract(), true));
				if (handleEnergy != 0){
					handler.receiveEnergy(dir.getOpposite(), handleEnergy, false);
					this.extractEnergy(dir, handleEnergy , false);
				}
			}
		}
		super.updateEntity();
	}
	
	@Override
    public Packet getDescriptionPacket() {
    	NBTTagCompound nbtTag = new NBTTagCompound();
    	writeToNBT(nbtTag);
    	return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTag);
    	
    }
    
    @Override
    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
    	readFromNBT(pkt.func_148857_g());
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
	public int receiveEnergy(ForgeDirection from, int maxReceive,
			boolean simulate) {
		return battery.receiveEnergy(maxReceive, simulate);
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,
			boolean simulate) {
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
	
	public void setEnergyStored(int energy) {
		battery.setEnergyStored(energy);
	}
	
	protected int getCorrectTransferValue(int transfer, int extract) {
		return Math.min(transfer, extract);
	}
}
