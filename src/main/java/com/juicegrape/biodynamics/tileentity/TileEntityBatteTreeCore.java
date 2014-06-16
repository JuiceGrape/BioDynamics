package com.juicegrape.biodynamics.tileentity;

import com.juicegrape.biodynamics.tileentity.common.TileEntityBattery;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.util.ForgeDirection;

public class TileEntityBatteTreeCore extends TileEntityBattery {

	public TileEntityBatteTreeCore() {
		super(50000, 200, 200);
		printEnergy();
		this.receiveEnergy(ForgeDirection.UNKNOWN, 200, false);
		printEnergy();
	}
	
	public void printEnergy() {
		System.out.println(this.getEnergyStored(ForgeDirection.UNKNOWN));
	}
	
	

}
