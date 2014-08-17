package com.juicegrape.biodynamics.tileentity;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.IFluidTank;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

import com.juicegrape.biodynamics.blocks.ModBlocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityMutatinator extends TileEntity implements IEnergyHandler, IInventory {
	
	EnergyStorage battery = new EnergyStorage(500000);
	
	public FluidTank redWaterTank = new FluidTank(8000);
	public FluidTank lavaTank = new FluidTank(8000);
	
	protected ItemStack[] slots;
	
	String redWaterTankTag = "redWaterTank";
	String lavaTankTag = "lavaTank";
	
	public int burntime;
	public int maxBurntime;
	public int heat;
	public int workingTime;
	
	public static final int maxHeat = 1000;
	
	String burntimeTag = "burntime";
	String maxBurntimeTag = "maxBurntime";
	String heatTag = "heat";
	
	
	public TileEntityMutatinator() {
		 slots = new ItemStack[12];
		 burntime = 0;
		 maxBurntime = 0;
		 heat = 0;
		 redWaterTank.setFluid(new FluidStack(ModBlocks.fluidRedstoneWater, 0));
		 lavaTank.setFluid(new FluidStack(FluidRegistry.LAVA, 0));
	}

	
	@Override
	public void updateEntity() {
		super.updateEntity();
		
		//lava tank
		handleLiquid(FluidRegistry.LAVA, 0, 1, lavaTank);
		/*if (slots[0] != null && FluidContainerRegistry.containsFluid(slots[0], new FluidStack(FluidRegistry.LAVA, 1000))) {
			FluidStack fStack = FluidContainerRegistry.getFluidForFilledItem(slots[0]);
			if (lavaTank.fill(fStack, false) == fStack.amount) {
				boolean canfill = false;
				if (slots[0].getItem().hasContainerItem(slots[0])) {
					ItemStack containerItem = slots[0].getItem().getContainerItem(slots[0]);
					if (slots[1] == null) {
						slots[1] = containerItem;
						slots[0] = null;
						canfill = true;
					} else if (slots[1].getItem().equals(containerItem.getItem()) && slots[1].getItemDamage() == containerItem.getItemDamage()) {
						slots[1].stackSize++;
						slots[0] = null;
						canfill = true;
					}
				} else {
					slots[0].stackSize--;
					if (slots[0].stackSize == 0)
						slots[0] = null;
					canfill = true;
				}
				if (canfill)
					lavaTank.fill(fStack, true);
			}
	
		} */
		//redwater tank
		handleLiquid(ModBlocks.fluidRedstoneWater, 2, 3, redWaterTank);
		/*if (slots[2] != null && FluidContainerRegistry.containsFluid(slots[2], new FluidStack(ModBlocks.fluidRedstoneWater, 1000))) {
			FluidStack fStack = FluidContainerRegistry.getFluidForFilledItem(slots[2]);
			if (redWaterTank.fill(fStack, false) == fStack.amount) {
				boolean canfill = false;
				if (slots[2].getItem().hasContainerItem(slots[2])) {
					ItemStack containerItem = slots[2].getItem().getContainerItem(slots[2]);
					if (slots[3] == null) {
						slots[3] = containerItem;
						slots[2] = null;
						canfill = true;
					} else if (slots[3].getItem().equals(containerItem.getItem()) && slots[3].getItemDamage() == containerItem.getItemDamage()) {
						slots[3].stackSize++;
						slots[2] = null;
						canfill = true;
					}
				} else {
					slots[2].stackSize--;
					if (slots[2].stackSize == 0)
						slots[2] = null;
					canfill = true;
				}
				if (canfill)
					redWaterTank.fill(fStack, true);
			}
		}*/
		
	}
	
	@Override
	public boolean canConnectEnergy(ForgeDirection from) {return true;}

	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		return battery.receiveEnergy(maxReceive, simulate);
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
		return (float)battery.getEnergyStored() / (float)battery.getMaxEnergyStored() * 100.0F;
	}
	
	public float getScaledHeatStored() {
		return (float)heat / (float)maxHeat * 100.0F;
	}
	
	public float getBurntimeScaled() {
		if (maxBurntime == 0)
			return 0F;
		
		return (float)burntime / (float)maxBurntime * 100.0F;
	}
	
	public float getRWScaled() {
		return (float)redWaterTank.getFluidAmount() / (float)redWaterTank.getCapacity() * 100.0F;
	}
	
	public float getLavaScaled() {
		return (float)lavaTank.getFluidAmount() / (float)lavaTank.getCapacity() * 100.0F;
	}
	
	public float getWorkingTimeScaled() {
		return (float)workingTime;
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
		
		NBTTagList list = nbt.getTagList("Items", 10);
		slots = new ItemStack[getSizeInventory()];
		
		for (int i = 0; i < list.tagCount(); i++) {
			NBTTagCompound compound = (NBTTagCompound)list.getCompoundTagAt(i);
			int slot = compound.getByte("Slot") & 255;
			
			if (slot >= 0 && slot < slots.length) {
				slots[slot] = ItemStack.loadItemStackFromNBT(compound);
			}
		}
		
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
		
		NBTTagList list = new NBTTagList();
		
		for (int i = 0; i < slots.length; i++) {
			if (slots[i] != null) {
				NBTTagCompound comp = new NBTTagCompound();
				comp.setByte("Slot", (byte)i);
				slots[i].writeToNBT(comp);
				list.appendTag(comp);
			}
		}
		
		nbt.setTag("Items", list);
		
		
		
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
	public ItemStack decrStackSize(int par1, int par2)
    {
        if (this.slots[par1] != null)
        {
            ItemStack itemstack;

            if (this.slots[par1].stackSize <= par2)
            {
                itemstack = this.slots[par1];
                this.slots[par1] = null;
                return itemstack;
            }
            else
            {
                itemstack = this.slots[par1].splitStack(par2);

                if (this.slots[par1].stackSize == 0)
                {
                    this.slots[par1] = null;
                }

                return itemstack;
            }
        }
        else
        {
            return null;
        }
    }

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {return null;}

	@Override
	public void setInventorySlotContents(int par1, ItemStack par2ItemStack){
        this.slots[par1] = par2ItemStack;

        if (par2ItemStack != null && par2ItemStack.stackSize > this.getInventoryStackLimit())
        {
            par2ItemStack.stackSize = this.getInventoryStackLimit();
        }

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
			return FluidContainerRegistry.containsFluid(stack, new FluidStack(FluidRegistry.LAVA, 1000));
		case 2:
			return FluidContainerRegistry.containsFluid(stack, new FluidStack(ModBlocks.fluidRedstoneWater, 1000));
		case 11:
			return TileEntityFurnace.isItemFuel(stack);
		default:
			return true;
		
		}
	}
	
	@SideOnly(Side.CLIENT)
	public void setEnergyStored(int energy) {
		this.battery.setEnergyStored(energy);
	}
	
	@SideOnly(Side.CLIENT)
	public void setRWstored(int amount) {
		this.redWaterTank.setFluid(new FluidStack(ModBlocks.fluidRedstoneWater, amount));
	}
	
	@SideOnly(Side.CLIENT)
	public void setlavaStored(int amount) {
		this.lavaTank.setFluid(new FluidStack(FluidRegistry.LAVA, amount));
	}
	
	protected void handleLiquid(Fluid fluid, int inputSlot, int outputSlot, IFluidTank tank) {
		if (slots[inputSlot] != null && FluidContainerRegistry.containsFluid(slots[inputSlot], new FluidStack(fluid, 1000))) {
			FluidStack fStack = FluidContainerRegistry.getFluidForFilledItem(slots[inputSlot]);
			if (tank.fill(fStack, false) == fStack.amount) {
				boolean canfill = false;
				if (slots[inputSlot].getItem().hasContainerItem(slots[inputSlot])) {
					ItemStack containerItem = slots[inputSlot].getItem().getContainerItem(slots[inputSlot]);
					if (slots[outputSlot] == null) {
						slots[outputSlot] = containerItem;
						slots[inputSlot] = null;
						canfill = true;
					} else if (slots[outputSlot].getItem().equals(containerItem.getItem()) && slots[outputSlot].getItemDamage() == containerItem.getItemDamage()) {
						slots[outputSlot].stackSize++;
						slots[inputSlot] = null;
						canfill = true;
					}
				} else {
					slots[inputSlot].stackSize--;
					if (slots[inputSlot].stackSize == 0)
						slots[inputSlot] = null;
					canfill = true;
				}
				if (canfill)
					tank.fill(fStack, true);
			}
	
		}
	}
	
	

}
