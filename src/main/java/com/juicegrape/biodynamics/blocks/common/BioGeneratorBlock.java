package com.juicegrape.biodynamics.blocks.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.blocks.BlockEnergySoil;

public class BioGeneratorBlock extends BioTileEntityBlock {

	protected BioGeneratorBlock(Material mat, String name) {
		super(mat, name);
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		if (world != null) {
			Block block = world.getBlock(x, y - 1, z);
			if (block != null && !world.isAirBlock(x, y - 1, z) && block instanceof BlockEnergySoil) {
				return true;
			}
		}
		return false;
	}
	
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
		this.checkBlockStay(world, x, y, z);
	}
	
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		if (this.canBlockStay(world, x, y, z)) {
			return super.canPlaceBlockAt(world, x, y, z);
		}
		return false;
	}
	
	private boolean checkBlockStay(World world, int x, int y, int z) {
		if (!this.canBlockStay(world, x, y, z)) {
			this.dropBlockAsItem(world, x, y, z, this.getDamageValue(world, x, y, z), 0);
			world.setBlockToAir(x, y, z);
			world.removeTileEntity(x, y, z);
			return false;
		} else {
			return true;
		}
	}
	
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
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
	public int getRenderType() {
		return 1;
	}

}
