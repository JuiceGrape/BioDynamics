package com.juicegrape.biodynamics.tileentity;

import com.juicegrape.biodynamics.tileentity.common.TileEntityBattery;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

/**
 * 
 * @author JuiceGrape
 *
 */
public class TileEntityBatteTreeCore extends TileEntityBattery {
	
	private ForgeDirection[] outputDirs = {ForgeDirection.DOWN};
	private ForgeDirection[] inputDirs = {ForgeDirection.NORTH, ForgeDirection.SOUTH, ForgeDirection.EAST, ForgeDirection.WEST};
	

	public TileEntityBatteTreeCore() {
		super(50000, 200, 200);
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
		return from != ForgeDirection.UP;
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
    protected ForgeDirection[] getInputDirections() {
		return inputDirs;
	}
	
    @Override
	protected ForgeDirection[] getOutputDirections() {
		return outputDirs;
	}
	
	

}
