package com.juicegrape.biodynamics.tileentity.containers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.util.ForgeDirection;

import com.juicegrape.biodynamics.tileentity.TileEntityMutatinator;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ContainerMutatinator extends Container{
	
	TileEntityMutatinator mutatinator;
	InventoryPlayer player;
	
	public int 
	lastBurnTime,
	lastMaxBurnTime,
	lastHeat,
	lastEnergy,
	lastRedWater,
	lastLava;
	
	
	public ContainerMutatinator(InventoryPlayer player, TileEntityMutatinator mutate) {
		this.player = player;
		mutatinator = mutate;
		
		this.addSlotToContainer(new Slot(mutate, 0, 8, 53));
		this.addSlotToContainer(new Slot(mutate, 1, 8, 98));
		this.addSlotToContainer(new Slot(mutate, 2, 29, 53));
		this.addSlotToContainer(new Slot(mutate, 3, 29, 98));
		this.addSlotToContainer(new Slot(mutate, 4, 80, 68));
		this.addSlotToContainer(new Slot(mutate, 5, 51, 51));
		this.addSlotToContainer(new Slot(mutate, 6, 57, 26));
		this.addSlotToContainer(new Slot(mutate, 7, 80, 15));
		this.addSlotToContainer(new Slot(mutate, 8, 103, 26));
		this.addSlotToContainer(new Slot(mutate, 9, 109, 51));
		this.addSlotToContainer(new Slot(mutate, 10, 80, 107));
		this.addSlotToContainer(new Slot(mutate, 11, 152, 107));
		
		for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j)
            {
                this.addSlotToContainer(new Slot(player, j + i * 9 + 9, 8 + j * 18, 128 + i * 18));
            }
        }

        for (int i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(player, i, 8 + i * 18, 186));
        }
		
	}

	@Override
	public boolean canInteractWith(EntityPlayer player) {
		return true;
	}
	
	@Override
	public void addCraftingToCrafters(ICrafting crafter){
		super.addCraftingToCrafters(crafter);
		crafter.sendProgressBarUpdate(this, 0, this.mutatinator.burntime);
		crafter.sendProgressBarUpdate(this, 1, this.mutatinator.maxBurntime);
		crafter.sendProgressBarUpdate(this, 2, this.mutatinator.heat);
		crafter.sendProgressBarUpdate(this, 3, this.mutatinator.getEnergyStored(ForgeDirection.UNKNOWN));
		crafter.sendProgressBarUpdate(this, 4, this.mutatinator.redWaterTank.getFluidAmount());
		crafter.sendProgressBarUpdate(this, 5, this.mutatinator.lavaTank.getFluidAmount());
	}
	
	@Override
	public void detectAndSendChanges(){
		super.detectAndSendChanges();
		for (int i = 0; i < this.crafters.size(); ++i) {
			ICrafting crafter = (ICrafting)this.crafters.get(i);
			if (lastBurnTime != mutatinator.burntime)
				crafter.sendProgressBarUpdate(this, 0, mutatinator.burntime);
			
			if (lastMaxBurnTime != mutatinator.maxBurntime)
				crafter.sendProgressBarUpdate(this, 1, mutatinator.maxBurntime);
			
			if (lastHeat != mutatinator.heat)
				crafter.sendProgressBarUpdate(this, 2, mutatinator.getEnergyStored(ForgeDirection.UNKNOWN));
			
			if (lastEnergy != mutatinator.getEnergyStored(ForgeDirection.UNKNOWN))
				crafter.sendProgressBarUpdate(this, 3, mutatinator.getEnergyStored(ForgeDirection.UNKNOWN));
			
			if (lastRedWater != mutatinator.redWaterTank.getFluidAmount())
				crafter.sendProgressBarUpdate(this, 4, mutatinator.redWaterTank.getFluidAmount());
			
			if (lastLava != mutatinator.lavaTank.getFluidAmount())
				crafter.sendProgressBarUpdate(this, 5, mutatinator.lavaTank.getFluidAmount());
			
			
			
			
		}
		
		lastBurnTime = mutatinator.burntime;
		lastMaxBurnTime = mutatinator.maxBurntime;
		lastHeat = mutatinator.heat;
		lastEnergy = mutatinator.getEnergyStored(ForgeDirection.UNKNOWN);
		lastRedWater = mutatinator.redWaterTank.getFluidAmount();
		lastLava = mutatinator.lavaTank.getFluidAmount();
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int id, int bar) {
		switch (id) {
		case 0:
			this.mutatinator.burntime = bar;
			break;
		case 1:
			this.mutatinator.maxBurntime = bar;
			break;
		case 2:
			this.mutatinator.heat = bar;
			break;
		case 3:
			this.mutatinator.setEnergyStored(bar);
			break;
		case 4:
			this.mutatinator.setRWstored(bar);
			break;
		case 5:
			this.mutatinator.setlavaStored(bar);
			break;
		default:
			System.out.println("Unkown ID");
			break;
			
		}
				
	}
	
	@Override
	public boolean func_94530_a(ItemStack stack, Slot slot) {
        if (slot.inventory instanceof TileEntityMutatinator) {
        	return mutatinator.isItemValidForSlot(slot.slotNumber, stack);
        }
        return true;
    }

}
