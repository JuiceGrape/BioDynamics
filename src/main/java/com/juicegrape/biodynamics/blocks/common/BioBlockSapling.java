package com.juicegrape.biodynamics.blocks.common;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BioBlockSapling extends BioBlock implements IPlantable {
	
	EnumPlantType type;
	
	protected BioBlockSapling(String name, EnumPlantType type) {
		super(Material.plants, name);
		this.setStepSound(Block.soundTypeGrass);
		this.type = type;
		this.setTickRandomly(true);
	}
	
	@Override
	public boolean canBlockStay(World world, int x, int y, int z) {
		if (world != null) {
			Block block = world.getBlock(x, y - 1, z);
			if (block != null && !world.isAirBlock(x, y - 1, z) &&
					block.canSustainPlant(world, x, y, z, ForgeDirection.UP, this)) {
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
	
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (canBlockStay(world, x, y, z)) {
			for (int i = 0; i < 3; i++) {
				if (!world.isAirBlock(x, y + i, z)) {
					return;
				}
				if (world.getBlockMetadata(x, y, z) >= 7) {
					growTree(world, x, y, z);
				} else {
					world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) + 1, 4);
				}
			}
		} else {
			this.checkBlockStay(world, x, y, z);
		}
	}
	
	private boolean checkBlockStay(World world, int x, int y, int z) {
		if (!this.canBlockStay(world, x, y, z)) {
			this.dropBlockAsItem(world, x, y, z, getPlantMetadata(world, x, y, z), 0);
			world.setBlockToAir(x, y, z);
			return false;
		} else {
			return true;
		}
	}
	
	public void growTree(World world, int x, int y, int z) {
		
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

	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return type;
	}

	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return this;
	}

	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		if (this.hasMultiple((World)world, x, y, z)) {
			return world.getBlockMetadata(x, y, z);
		} else {
			return 0;
		}
	}
	

}
