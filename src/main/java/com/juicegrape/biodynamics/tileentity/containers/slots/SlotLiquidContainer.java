package com.juicegrape.biodynamics.tileentity.containers.slots;

import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

public class SlotLiquidContainer extends Slot {
	
	public SlotLiquidContainer(IInventory inventory, int slot, int x, int y) {
		super(inventory, slot, x, y);
	}
	
	public boolean isItemValid(ItemStack stack) {
		return FluidContainerRegistry.isFilledContainer(stack);
	}

}
