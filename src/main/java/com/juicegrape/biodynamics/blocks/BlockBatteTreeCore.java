package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.blocks.common.BioTileEntityBlock;
import com.juicegrape.biodynamics.tileentity.TileEntityBatteTreeCore;

public class BlockBatteTreeCore extends BioTileEntityBlock {

	protected BlockBatteTreeCore(String name) {
		super(Material.wood, name);
		this.setStepSound(soundTypeWood);
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityBatteTreeCore();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntityBatteTreeCore core = (TileEntityBatteTreeCore)world.getTileEntity(x, y, z);
		if (!world.isRemote && core != null) {
			core.printEnergy();
		}
		return true;
	}

}
