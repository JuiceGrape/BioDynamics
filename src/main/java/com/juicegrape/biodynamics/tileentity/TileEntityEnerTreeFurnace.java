package com.juicegrape.biodynamics.tileentity;

import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

import com.juicegrape.biodynamics.blocks.ModBlocks;
import com.juicegrape.biodynamics.items.ModItems;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class TileEntityEnerTreeFurnace extends TileEntityFurnace {
	
	private static int[] topSlots = {0, 1};
	private static int[] bottomSlots = {1,2};
	
	
	public TileEntityEnerTreeFurnace() {
		this(LanguageRegistry.instance().getStringLocalization(ModBlocks.enertreeFurnace.getUnlocalizedName() + ".name"));
	}
	
	public TileEntityEnerTreeFurnace(String name) {
		super();
		this.func_145951_a(name);
	}
	
	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		super.readFromNBT(nbt);
	}
	
	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		super.writeToNBT(nbt);
	}
	
	public static int getItemBurnTime(ItemStack stack) {
		if (stack == null) {
			return 0;
		} else {
			Item item = stack.getItem();
			if (item == Items.redstone)
				return 1600;
			if (item == ModItems.specialBonemeal && stack.getItemDamage() == 0)
				return 1200;
			if (item == ModItems.bucketsArray[0])
				return 1600;
		}
		return 0;
	}
	
	public static boolean isItemFuel(ItemStack stack) {
		return getItemBurnTime(stack) > 0;
	}
	
	@Override
	public void smeltItem() {
		/*if (this.canSmelt()){
			if (OreDictionaryHelper.isStackEqual(getStackInSlot(0), new ItemStack(Blocks.sapling))) {
				this.getStackInSlot(0).stackSize--;
				if (getStackInSlot(0).stackSize <= 0) {
					setInventorySlotContents(0, null);
				}
				ItemStack stack = this.getStackInSlot(2);
				if (stack == null) {
					stack = new ItemStack(ModBlocks.enerTreeSapling);
				} else {
					stack.stackSize++;
				}
				setInventorySlotContents(2, stack);
			}
		} */
		super.smeltItem();
		
		
	}
	
	public void updateEntity() {
		boolean flag = this.furnaceBurnTime > 0;
		boolean flag1 = false;
		
		for(int i = 0; i < 2; i++) {
			if (this.furnaceBurnTime > 0) {
				this.furnaceBurnTime--;
			}
		}
		
		if (!this.worldObj.isRemote) {
			if (this.furnaceBurnTime == 0 && this.canSmelt()) {
				this.currentItemBurnTime = this.furnaceBurnTime = getItemBurnTime(this.getStackInSlot(1));
				
				if (this.furnaceBurnTime > 0) {
					flag1 = true;
					
					if (this.getStackInSlot(1) != null) {
						this.getStackInSlot(1).stackSize--;
						
						if (this.getStackInSlot(1).stackSize == 0) {
							this.setInventorySlotContents(1, this.getStackInSlot(1).getItem().getContainerItem(this.getStackInSlot(1)));
						}
					}
				}
			}
			
			if (this.isBurning() && this.canSmelt()) {
				for (int i = 0; i < 2; i++) {
					this.furnaceCookTime++;
					if (this.furnaceCookTime == 200) {
						this.furnaceCookTime = 0;
						this.smeltItem();
						flag1 = true;
					}
				}
			} else {
				this.furnaceCookTime = 0;
			}
			
			if (flag != this.furnaceBurnTime > 0) {
				flag1 = true;
			}
			
			
			if (flag1) {
				this.markDirty();
			}
			
			this.worldObj.markBlockForUpdate(this.xCoord, this.yCoord, this.zCoord);
		}
		
	}
	

	private boolean canSmelt() {
		if (this.getStackInSlot(0) == null)
        {
            return false;
        }
        else
        {
        	ItemStack itemstack;
            itemstack = FurnaceRecipes.smelting().getSmeltingResult(this.getStackInSlot(0));
            if (itemstack == null) return false;
            if (this.getStackInSlot(2) == null) return true;
            if (!this.getStackInSlot(2).isItemEqual(itemstack)) return false;
            int result = getStackInSlot(2).stackSize + itemstack.stackSize;
            return result <= getInventoryStackLimit() && result <= this.getStackInSlot(2).getMaxStackSize();
        }
	}
	
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		if(slot == 2) {
			return false;
		} else if (slot == 1){
			return true;
		} else {
			return true;
		}
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
    public int[] getAccessibleSlotsFromSide(int side) {
      switch (side) {
      case 0:
    	  return bottomSlots;
      case 1:
    	  return topSlots;
      default:
    	return null;
      }
    }

}
