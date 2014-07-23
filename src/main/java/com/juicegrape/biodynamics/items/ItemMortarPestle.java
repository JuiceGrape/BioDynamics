package com.juicegrape.biodynamics.items;

import net.minecraft.item.ItemStack;

import com.juicegrape.biodynamics.items.common.BioItem;

public class ItemMortarPestle extends BioItem {

	public ItemMortarPestle(String name) {
		super(name);
		setMaxStackSize(1);
		setMaxDamage(512);
	}
	
	@Override
	public boolean doesContainerItemLeaveCraftingGrid(ItemStack stack) {
		return false;
	}

	@Override
	public ItemStack getContainerItem(ItemStack stack) {
		stack.setItemDamage(stack.getItemDamage() + 1);
		
		if (stack.getItemDamage() == this.getMaxDamage()) {
			return new ItemStack(ModItems.craftingItem, 1, 9);
		} else {
			return stack;
		}
		
	}

	@Override
	public boolean hasContainerItem(ItemStack stack) {
		return true;
	}

}
