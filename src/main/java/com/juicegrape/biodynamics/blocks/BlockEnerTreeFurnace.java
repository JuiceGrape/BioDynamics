package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.blocks.common.BioTileEntityBlock;
import com.juicegrape.biodynamics.tileentity.TileEntityEnerTreeFurnace;

public class BlockEnerTreeFurnace extends BioTileEntityBlock {

	protected BlockEnerTreeFurnace(String name) {
		super(Material.wood, name);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		} else {
			TileEntityEnerTreeFurnace furnace = (TileEntityEnerTreeFurnace)world.getTileEntity(x, y, z);
			if (furnace != null) {
				player.func_146101_a(furnace);
			}
			return true;
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityEnerTreeFurnace();
	}
	
	

}
