package com.juicegrape.biodynamics.tileentity.generators;

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

import com.juicegrape.biodynamics.tileentity.common.IBioGenerator;

public class TileEntityBurningFlower extends TileEntity implements IBioGenerator, IInventory {
	
	public ItemStack[] burnable = new ItemStack[getSizeInventory()];;
	int burntime = 0;
	
	private static final String arrayTag = "Items";
	private static final String slotTag = "Slot";
	
	public TileEntityBurningFlower() {
		super();
	}

	@Override
	public int getPowerGen() {
		return burntime > 0 ? 50 : 0;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return burnable[slot];
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		
		if (burnable[slot] != null) {
			ItemStack stack;
			if (burnable[slot].stackSize <= amount) {
				stack = burnable[slot];
				burnable[slot] = null;
				onInventoryChanged();
				return stack;
			} else {
				stack = burnable[slot].splitStack(amount);
				
				if (burnable[slot].stackSize == 0) {
					burnable[slot] = null;
				}
				
				onInventoryChanged();
				return stack;
			}
		} else {
			return null;
		}
		
		
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		burnable[slot] = stack;
		if (stack != null && stack.stackSize > this.getInventoryStackLimit()) {
			stack.stackSize = getInventoryStackLimit();
		}
		onInventoryChanged();
	}

	@Override
	public String getInventoryName() {
		return "burningflower.internal.name";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return player.getDistanceSq(xCoord + 0.5, yCoord + 0.5, zCoord + 0.5) < 64;
	}

	@Override
	public void openInventory() {}

	@Override
	public void closeInventory() {}

	@Override
	public boolean isItemValidForSlot(int slot, ItemStack stack) {
		return TileEntityFurnace.isItemFuel(stack);
	}
	
	public void onInventoryChanged() {
		update();
	}
	
	public void update() {
		worldObj.markBlockForUpdate(xCoord, yCoord, zCoord);
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
    
    public void updateEntity() {
    	if (worldObj.isRemote) {
    		return;
    	}
    	if (burntime <= 0 && burnable[0] != null && TileEntityFurnace.isItemFuel(burnable[0])) {
    		burntime = TileEntityFurnace.getItemBurnTime(decrStackSize(0, 1));
    	}
    	
    	if (isBurning()) {
    		burntime--;
    	}
    	super.updateEntity();
    }
    
    public boolean isBurning() {
    	return burntime > 0;
    }
    
    public void readFromNBT(NBTTagCompound nbt) {
    	super.readFromNBT(nbt);
    	NBTTagList list = nbt.getTagList(arrayTag, 10);
    	burnable = new ItemStack[this.getSizeInventory()];
    	
    	for (int i = 0; i < list.tagCount(); i++) {
    		NBTTagCompound nbt2 = (NBTTagCompound)list.getCompoundTagAt(i);
    		
    		int j = nbt2.getByte(slotTag) & 255;
    		
    		if (j >= 0 && j < burnable.length) {
    			burnable[j] = ItemStack.loadItemStackFromNBT(nbt2);
    		}
    	}
    	burntime = nbt.getInteger("burntime");
    }
    
    public void writeToNbt(NBTTagCompound nbt) {
    	super.writeToNBT(nbt);
    	NBTTagList list = new NBTTagList();
    	
    	for (int i = 0; i < burnable.length; i++) {
    		NBTTagCompound nbt2 = new NBTTagCompound();
    		nbt2.setByte(slotTag, (byte)i);
    		burnable[i].writeToNBT(nbt2);
    		list.appendTag(nbt2);
    	}
    	
    	nbt.setTag(arrayTag, list);
    	nbt.setInteger("burntime", burntime);
    }

}
