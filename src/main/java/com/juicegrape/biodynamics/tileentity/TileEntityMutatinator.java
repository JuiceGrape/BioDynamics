package com.juicegrape.biodynamics.tileentity;

import java.util.Iterator;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;
import net.minecraftforge.fluids.FluidTankInfo;
import net.minecraftforge.fluids.IFluidHandler;
import net.minecraftforge.fluids.IFluidTank;
import cofh.api.energy.EnergyStorage;
import cofh.api.energy.IEnergyHandler;

import com.juicegrape.biodynamics.blocks.ModBlocks;
import com.juicegrape.biodynamics.misc.ForgeDirectionSidedHelper;
import com.juicegrape.biodynamics.recipes.mutatinator.MutatorRecipe;
import com.juicegrape.biodynamics.tileentity.common.SpecificTank;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TileEntityMutatinator extends TileEntity implements IEnergyHandler, IInventory, IFluidHandler {
	
	EnergyStorage battery = new EnergyStorage(100000);
	
	public FluidTank redWaterTank = new SpecificTank(ModBlocks.fluidRedstoneWater, 8000); 
	public FluidTank lavaTank = new SpecificTank(FluidRegistry.LAVA, 8000);
	
	protected ItemStack[] slots;
	
	String redWaterTankTag = "redWaterTank";
	String lavaTankTag = "lavaTank";
	
	public int burntime;
	public int maxBurntime;
	public int heat;
	public int workingTime;
	
	private static final int maxWorkingTime = 100;
	
	public static final int maxHeat = 1000;
	
	String burntimeTag = "burntime";
	String maxBurntimeTag = "maxBurntime";
	String heatTag = "heat";
	String workingTag = "workingTime";
	
	int reqWater = 500;
	
	protected MutatorRecipe currentRecp;
	
	
	public TileEntityMutatinator() {
		 slots = new ItemStack[12];
		 burntime = 0;
		 maxBurntime = 0;
		 heat = 0;
		 redWaterTank.setFluid(new FluidStack(ModBlocks.fluidRedstoneWater, 0));
		 lavaTank.setFluid(new FluidStack(FluidRegistry.LAVA, 0));
		 workingTime = 0;
		 
		 currentRecp = null;
		 
		 //for (int i = 0; i < slots.length; i++) { slots[i] = new ItemStack(Items.egg, i + 1); }
	}

	
	@Override
	public void updateEntity() {
		super.updateEntity();
		//worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
		
		if (worldObj.isRemote)
			return;
		
		//lava tank
		handleLiquid(FluidRegistry.LAVA, 0, 1, lavaTank);
		
		//redwater tank
		handleLiquid(ModBlocks.fluidRedstoneWater, 2, 3, redWaterTank);
		
		//Decrease the heat
		if (worldObj.rand.nextInt(20) == 1)
			heat-=(worldObj.rand.nextInt(3) + 1);
		
		if (heat < maxHeat && lavaTank.drain(1, false).amount == 1) {
			lavaTank.drain(1, true);
			heat+=(worldObj.rand.nextInt(3) + 1);
		}
		
		//burntime
		if (heat < maxHeat || battery.getEnergyStored() < battery.getMaxEnergyStored()) {
			if (burntime <= 0 && slots[11] != null && TileEntityFurnace.isItemFuel(slots[11])) {
	    		burntime = TileEntityFurnace.getItemBurnTime(this.slots[11]); 
	    		maxBurntime = burntime;
	    		if (slots[11].getItem().hasContainerItem(slots[11])) {
	    			slots[11] = slots[11].getItem().getContainerItem(slots[11]);
	    		} else {
	    			this.decrStackSize(11, 1);
	    		}
	    	}
		}
		
		//using the burntime
		if (burntime > 0) {
			if (heat < maxHeat) {
				heat++;
			}
			if (battery.receiveEnergy(10, true) != 0) {
				battery.receiveEnergy(10, false);
			}
		}
		
		
		if (handleCrafting()) {
			if (currentRecp != null)
				workingTime+= currentRecp.getSpeed();
		} else if (workingTime > 0) {
			workingTime = 0;
			currentRecp = null;
		}
		
		if (workingTime >= maxWorkingTime && currentRecp != null) {
			craft(currentRecp);
		}
		
		
		
		
		
		
		
		//make sure levels don't go out of bounds
		if (heat >= maxHeat) {
			heat = maxHeat;
		}
		System.out.println(worldObj.getBiomeGenForCoords(xCoord, zCoord).temperature);
		if (heat < 30.0f * worldObj.getBiomeGenForCoords(xCoord, zCoord).temperature) {
			heat++;
		}
		
		redWaterTank.fill(new FluidStack(ModBlocks.fluidRedstoneWater, 500), true);
		
		
		//tick down burntime, always make this last
		burntime--;
		
	}
	
	private boolean handleCrafting() {
		if (slots[4] != null) {
			Iterator<MutatorRecipe> itr = MutatorRecipe.recipes.iterator();
			while (itr.hasNext()) {
				MutatorRecipe recp = itr.next();
				if (recp != null) {
					if (recp.getHeat() <= this.heat && recp.getPower() <= this.battery.getEnergyStored() && reqWater <= this.redWaterTank.getFluidAmount()) {
						if ((slots[10] == null) || (slots[10].stackSize <= recp.getOutput().getMaxStackSize() - recp.getOutput().stackSize && slots[10].isItemEqual(recp.getOutput())) ) {
							if (recp.hasMainItem(slots[4])) {
								//check if it needs extra items
								if (recp.requiresExtraInput()) {
									if (canCraft(recp)) {
										//Check if it can output
										if (slots[10] == null || (slots[10].stackSize < 64 && slots[10].isItemEqual(recp.getOutput())) ) {
											//handle the crafting
											currentRecp = recp;
											return true;
										}
									}
								} else {
									boolean hasItem = false;
									for (int i = 5; i < 10; i++) {
										if (slots[i] != null) {
											hasItem = true;
										}
									}
									//Doesn't require extra input
									//handle the crafting
									if (!hasItem){
										currentRecp = recp;
										return true;
									}
									
								}
							}
						}
					}
					/*if (slots[10] == null || (slots[10].stackSize < 64 && slots[10].isItemEqual(recp.getOutput())) ) {
						if (recp.hasMainItem(slots[4])) {
							slots[4].stackSize-=1;
							if (slots[4].stackSize == 0)
								slots[4] = null;
							if (slots[10] == null) {
								slots[10] = recp.getOutput();
							} else {
								slots[10].stackSize += 1;
							}
						}
					}*/
				}
			}
		}
		return false;
	}
	
	private void craft(MutatorRecipe recp) {
		slots[4].stackSize-=1;
		if (slots[4].stackSize == 0)
			slots[4] = null;
		if (slots[10] == null) {
			slots[10] = recp.getOutput().copy();
		} else {
			slots[10].stackSize += recp.getOutput().stackSize;
		}
		for (int i = 5; i < 10; i++) {
			if (slots[i] != null) {
				slots[i].stackSize -= 1;
				if (slots[i].stackSize <= 0)
					slots[i] = null;
			}
		}
		this.battery.extractEnergy(recp.getPower(), false);
		this.redWaterTank.drain(reqWater, true);
		workingTime = 0;
	}
	
	private boolean canCraft(MutatorRecipe recp) {
		int slotsused = 0;
		for (int i = 5; i < 10; i++) {
			if (slots[i] != null) {
				slotsused++;
				if (!recp.hasItem(slots[i])) {
					return false;
				}
			}
		}
		if (slotsused != recp.getOtherRecipeLength()) {
			return false;
		}
		return true;
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
		return (float)workingTime / (float)maxWorkingTime * 100.0F;
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
		workingTime = nbt.getInteger(workingTag);
		
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
		nbt.setInteger(workingTag, workingTime);
		
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


	@Override
	public int fill(ForgeDirection from, FluidStack resource, boolean doFill) {
		if (from == getLeft()) {
			return lavaTank.fill(resource, doFill);
		} else if (from == getRight()) {
			return redWaterTank.fill(resource, doFill);
		}
		return 0;
	}


	@Override
	public FluidStack drain(ForgeDirection from, FluidStack resource, boolean doDrain) {
		return null;
		
	}


	@Override
	public FluidStack drain(ForgeDirection from, int maxDrain, boolean doDrain) {
		return null;
	}


	@Override
	public boolean canFill(ForgeDirection from, Fluid fluid) {
		if (from == getLeft() && fluid == FluidRegistry.LAVA) {
			return true;
		} else if (from == getRight() && fluid == ModBlocks.fluidRedstoneWater) {
			return true;
		}
		return false;
	}


	@Override
	public boolean canDrain(ForgeDirection from, Fluid fluid) {
		return false;
	}


	@Override
	public FluidTankInfo[] getTankInfo(ForgeDirection from) {
		return new FluidTankInfo[] {lavaTank.getInfo(), redWaterTank.getInfo()};
	}
	
	public ForgeDirection getFacing() {
		return ForgeDirectionSidedHelper.getFacingFromInt(worldObj.getBlockMetadata(xCoord, yCoord, zCoord));
	}
	
	public ForgeDirection getLeft() {
		return ForgeDirectionSidedHelper.getLeftFromFacing(getFacing());
	}
	
	public ForgeDirection getRight() {
		return ForgeDirectionSidedHelper.getRightFromFacing(getFacing());
	}
	
	private boolean hasRecipe() {
		//for (int i = 0)
		return false;
	}
	
	
	/*@Override
	    public Packet getDescriptionPacket() {
	    	NBTTagCompound nbtTag = new NBTTagCompound();
	    	writeToNBT(nbtTag);
	    	return new S35PacketUpdateTileEntity(xCoord, yCoord, zCoord, 1, nbtTag);
	    	
	    }
	    
	    @Override
	    public void onDataPacket(NetworkManager net, S35PacketUpdateTileEntity pkt) {
	    	readFromNBT(pkt.func_148857_g());
	    }
	*/
	

}
