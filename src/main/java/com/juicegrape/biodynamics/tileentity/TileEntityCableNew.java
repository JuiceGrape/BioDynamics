package com.juicegrape.biodynamics.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;

import com.juicegrape.biodynamics.tileentity.common.TileEntityBattery;

public class TileEntityCableNew extends TileEntityBattery {

	public TileEntityCableNew(int tier) {
		super(800, 100, 100);
	}
	
	@Override
	public void updateEntity() {
		this.battery.setMaxExtract(Math.max(Math.min(this.battery.getEnergyStored() / 8, 100), 10));
	}

	
	public void printEnergy() {
		super.printEnergy();
		System.out.println("maxtransfer = " + this.battery.getMaxExtract());
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
}
