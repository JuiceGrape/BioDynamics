package com.juicegrape.biodynamics.tileentity;

import com.juicegrape.biodynamics.blocks.BlockMutatinator;
import com.juicegrape.biodynamics.blocks.ModBlocks;

import net.minecraft.block.BlockMushroom;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.FluidTank;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

public class TileEntityMutatinator extends TileEntity implements IEnergyHandler, IInventory {
	
	EnergyStorage battery = new EnergyStorage(500000);
	
	protected FluidTank redWaterTank = new FluidTank(8000);
	protected FluidTank lavaTank = new FluidTank(8000);
	
	protected ItemStack[] slots;
	
	String redWaterTankTag = "redWaterTank";
	String lavaTankTag = "lavaTank";
	
	protected int burntime;
	protected int maxBurntime;
	protected int heat;
	public static final int maxHeat = 1000;
	
	String burntimeTag = "burntime";
	String maxBurntimeTag = "maxBurntime";
	String heatTag = "heat";
	
	private float returner;
	boolean up;
	
	public TileEntityMutatinator() {
		 slots = new ItemStack[12];
		 burntime = 0;
		 maxBurntime = 0;
		 heat = 0;
		 returner = 0.0F;
		 up = true;
	}

	
	@Override
	public void updateEntity() {
		if (up) {
			returner+=0.1F;
		} else {
			returner-=0.1F;
		}
		
		if (returner >= 100.0F) {
			up = false;
		} else if (returner <= 0.0F) {
			up = true;
		}
		
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {return true;}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return 0;
	}

	@Override
	public int extractEnergy(ForgeDirection from, int maxExtract,boolean simulate) {return 0;}

	@Override
	public int getEnergyStored(ForgeDirection from) {
		return battery.getEnergyStored();
	}

	@Override
	public int getMaxEnergyStored(ForgeDirection from) {
		return battery.getMaxEnergyStored();
	}
	
	public float getScaledEnergyStored() {
	//	return battery.getEnergyStored() / battery.getMaxEnergyStored() * 100.0F;
		return returner;
	}
	
	public float getScaledHeatStored() {
	//	return heat / maxHeat * 100.0F;
		return returner;
	}
	
	public float getBurntimeScaled() {
	//	return burntime / maxBurntime * 100.0F;
		return returner;
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
		battery.readFromNBT(nbt);
		
		redWaterTank = redWaterTank.readFromNBT(nbt.getCompoundTag(redWaterTankTag));
		lavaTank = lavaTank.readFromNBT(nbt.getCompoundTag(lavaTankTag));
		
		burntime = nbt.getInteger(burntimeTag);
		maxBurntime = nbt.getInteger(maxBurntimeTag);
		heat = nbt.getInteger(heatTag);
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
		battery.writeToNBT(nbt);
		
		NBTTagCompound redWaterTag = redWaterTank.writeToNBT(new NBTTagCompound());
		NBTTagCompound lavaTag = lavaTank.writeToNBT(new NBTTagCompound());
		
		nbt.setTag(redWaterTankTag, redWaterTag);
		nbt.setTag(lavaTankTag, lavaTag);
		
		nbt.setInteger(burntimeTag, burntime);
		nbt.setInteger(heatTag, heat);
		nbt.setInteger(maxBurntimeTag, maxBurntime);
		
	}

	@Override
	public int getSizeInventory() {
		return 12;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return slots[slot];
	}

	@Override
	public ItemStack decrStackSize(int p_70298_1_, int p_70298_2_) {
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {return null;}

	@Override
	public void setInventorySlotContents(int p_70299_1_, ItemStack p_70299_2_) {
		
	}

	@Override
	public String getInventoryName() {
		return ModBlocks.mutatinator.getLocalizedName();
	}

	@Override
	public boolean hasCustomInventoryName() {return false;}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer p_70300_1_) {
		return true;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		switch (slot) {
		case 1:
		case 3:
		case 4:
			return false;
		case 0:
		case 2:
			return true;
		case 11:
			return TileEntityFurnace.isItemFuel(stack);
		default:
			return true;
		
		}
	}
	
	

}
