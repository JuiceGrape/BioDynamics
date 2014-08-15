package com.juicegrape.biodynamics.tileentity.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;

import com.juicegrape.biodynamics.tileentity.TileEntityMutatinator;

public class ContainerMutatinator extends Container{
	
	TileEntityMutatinator mutatinator;
	InventoryPlayer player;
	
	public ContainerMutatinator(InventoryPlayer player, TileEntityMutatinator mutate) {
		this.player = player;
		mutatinator = mutate;
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}

}
