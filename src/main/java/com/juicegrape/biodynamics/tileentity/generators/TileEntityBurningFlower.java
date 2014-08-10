package com.juicegrape.biodynamics.tileentity.generators;

import java.util.Random;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.Packet;
import net.minecraft.network.play.server.S35PacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;

import com.juicegrape.biodynamics.misc.ItemEntityHelper;
import com.juicegrape.biodynamics.tileentity.common.IBioGenerator;

public class TileEntityBurningFlower extends TileEntity implements IBioGenerator, IInventory {
	
	public ItemStack burnable;
	int burntime = 0;
	
	protected int happiness;
	protected int fullness;
	
	private static final String fullTag = "happiness";
	private static final String happyTag = "fullness";
	
	private static final int updateRate = 10;
	
	private static final String itemTag = "Burnable";
	private static final String burnTag = "Burntime";
	
	public TileEntityBurningFlower() {
		super();
		burnable = null;
		happiness = 50;
		fullness = 100;
	}

	@Override
	public int getPowerGen() {
		return burntime > 0 ? happiness < 25 ? 20 : happiness > 75 ? 60 : 40 : 0;
	}

	@Override
	public int getSizeInventory() {
		return 1;
	}

	@Override
	public ItemStack getStackInSlot(int slot) {
		return burnable;
	}

	@Override
	public ItemStack decrStackSize(int slot, int amount) {
		onInventoryChanged();
		if (burnable != null) {
			ItemStack stack;
			if (burnable.stackSize <= amount) {
				stack = burnable;
				burnable = null;
				onInventoryChanged();
				return stack;
			} else {
				stack = burnable.splitStack(amount);
				
				if (burnable.stackSize == 0) {
					burnable = null;
				}
				
				return stack;
			}
		} else {
			return null;
		}

		
		
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int p_70304_1_) {
		return burnable;
	}

	@Override
	public void setInventorySlotContents(int slot, ItemStack stack) {
		burnable = stack;
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
		this.markDirty();
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
    	
    	if (burnable != null && !TileEntityFurnace.isItemFuel(burnable)) {
    		EntityPlayer player = worldObj.getClosestPlayer(xCoord, yCoord, zCoord, 5);
    		if (player != null) {
    			worldObj.spawnEntityInWorld(ItemEntityHelper.createItemTowardsPlayer(burnable, worldObj, xCoord, yCoord, zCoord, player));
    		} else {
    			worldObj.spawnEntityInWorld(ItemEntityHelper.createItem(burnable, worldObj, xCoord, yCoord, zCoord));
    		}
    		burnable = null;
    		this.update();
    		return;
    		
    	}
    	if (burntime <= 0 && burnable != null && TileEntityFurnace.isItemFuel(burnable)) {
    		burntime = TileEntityFurnace.getItemBurnTime(this.burnable) + 1; 
    		if (this.burnable.getItem().hasContainerItem(burnable)) {
    			burnable = burnable.getItem().getContainerItem(burnable);
    		} else {
    			this.decrStackSize(0, 1);
    		}
    		this.update();
    	}
    	
    	if (isBurning()) {
    		burntime--;
    		if (worldObj.rand.nextInt(updateRate) == 1)
    			fullness++;
    	} else if (worldObj.rand.nextInt(updateRate) == 1) {
    		fullness--;
    	}
    	
    	//Adjusting happiness because of fullness
    	if (worldObj.rand.nextInt(updateRate) == 1) {
    		if (fullness < 25) {
    			happiness-=2;
    		} else if (fullness < 50) {
    			happiness--;
    		} else if (fullness <= 100) {
    			happiness++;
    		} else if (fullness >= 145) {
    			happiness+=2;
    		}

    	}
    	
    	//some end adjustments to make sure the values don't go above/below the wanted levels
    	if (happiness > 100)
    		happiness = 100;
    	if (happiness < 1)
    		happiness = 1;
    	if (fullness > 150)
    		fullness = 150;
    	if (fullness < 0)
    		fullness = 0;

    	
    	
    	super.updateEntity();
    }
    
    public boolean isBurning() {
    	return burntime > 0;
    }
    
    @Override
    public void readFromNBT(NBTTagCompound nbt) {
    	super.readFromNBT(nbt);
    	
    	NBTTagCompound item = nbt.getCompoundTag(itemTag);
    	
    	if (item != null) 
    		burnable = ItemStack.loadItemStackFromNBT(item);
    	
    	happiness = nbt.getInteger(happyTag);
    	fullness = nbt.getInteger(fullTag);
    	burntime = nbt.getInteger(burnTag);
    }
    
    @Override
    public void writeToNBT(NBTTagCompound nbt) {
    	super.writeToNBT(nbt);
    	
    	if (burnable != null) {
	    	NBTTagCompound item = new NBTTagCompound();
	    	burnable.writeToNBT(item);
	    	nbt.setTag(itemTag, item);
    	}
    	
    	nbt.setInteger(happyTag, happiness);
    	nbt.setInteger(fullTag, fullness);
    	nbt.setInteger(burnTag, burntime);
    }

	@Override
	public int getHappiness() {
		return happiness;
	}
    
   

}
