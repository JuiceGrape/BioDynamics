package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;

import com.juicegrape.biodynamics.blocks.common.BioTileEntityBlock;
import com.juicegrape.biodynamics.blocks.common.energy.IEnergyReceiver;
import com.juicegrape.biodynamics.blocks.common.energy.IEnergySupplier;
import com.juicegrape.biodynamics.blocks.common.energy.IEnergyTransferer;
import com.juicegrape.biodynamics.tileentity.TileEntityBatteTreeCore;
import com.juicegrape.biodynamics.tileentity.TileEntityEnerTreeFurnace;

public class BlockBatteTreeCore extends BioTileEntityBlock {

	protected BlockBatteTreeCore(String name) {
		super(Material.wood, name);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityBatteTreeCore();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntityBatteTreeCore core = (TileEntityBatteTreeCore)world.getTileEntity(x, y, z);
		if (core != null) {
			System.out.println(core.canConnectEnergy(ForgeDirection.DOWN));
		}
		return true;
	}

}
