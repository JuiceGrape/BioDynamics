package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.blocks.common.BioTileEntityBlock;
import com.juicegrape.biodynamics.tileentity.TileEntityCable;

public class BlockEnergyCable extends BioTileEntityBlock {

	protected BlockEnergyCable(String name) {
		super(Material.iron, name);
		setBlockName(BlockInfo.CABLE);
		float minBound = 0.0625F * 3F;
		float maxBound = 1F - minBound;
		this.setBlockBounds(minBound, minBound, minBound, maxBound, maxBound, maxBound);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityCable(meta + 1);
	}
	
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	@Override
	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int x, int y, int z, int side) {
	   return false;
	}

}
