package com.juicegrape.biodynamics.tileentity.containers.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class SlotOutput extends Slot {

	public SlotOutput(IInventory inventory, int slot, int x, int y) {
		super(inventory, slot, x, y);
	}
	
	public boolean isItemValid(ItemStack stack) {
		return false;
	}
	
	

}
