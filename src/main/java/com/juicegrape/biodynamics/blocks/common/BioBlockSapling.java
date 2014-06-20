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

/**
 * @author JuiceGrape
 * Class for easily making saplings, override this if you want to make a sapling.
 *
 */
public class BioBlockSapling extends BioBlock implements IPlantable {
	
	EnumPlantType type;
	
	/**
	 * The maximum height of the resulting tree this sapling creates.
	 */
	public int maxHeight;
	
	/**
	 * The minimum height of the resulting tree this sapling creates.
	 */
	public int minHeight;
	
	/**
	 * 
	 * Sets the step sound to grass, set's the plant type and sets it to tick randomly.
	 * 
	 * @param name The unlocalized name for this sapling.
	 * @param type The plant type for this sapling. Determines where it can be placed down.
	 */
	protected BioBlockSapling(String name, EnumPlantType type) {
		super(Material.plants, name);
		this.setStepSound(Block.soundTypeGrass);
		this.type = type;
		this.setTickRandomly(true);
		maxHeight = 5;
		minHeight = 3;
	}
	
	//Checks if a block can stay at a certain point in the world.
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
	
	//Is called whenever a block changes next to it, to see if it can still stay.
	@Override
	public void onNeighborBlockChange(World world, int x, int y, int z, Block neighbor) {
		this.checkBlockStay(world, x, y, z);
	}
	
	//Checks if you can actually place the block at the given coordinates.
	@Override
	public boolean canPlaceBlockAt(World world, int x, int y, int z) {
		if (this.canBlockStay(world, x, y, z)) {
			return super.canPlaceBlockAt(world, x, y, z);
		}
		return false;
	}
	
	
	//Called every time this sapling updates, if it can grow, it increases the metadata by 1, if the metadata is more 7 or more, it will call the growTree function.
	@Override
	public void updateTick(World world, int x, int y, int z, Random random) {
		if (canBlockStay(world, x, y, z)) {
			for (int i = 1; i < minHeight; i++) {
				if (!world.isAirBlock(x, y + i, z)) {
					return;
				}
			}
			if (world.getBlockMetadata(x, y, z) >= 7) {
				growTree(world, x, y, z);
			} else {
				world.setBlockMetadataWithNotify(x, y, z, world.getBlockMetadata(x, y, z) + 1, 4);
			}
		} else {
			this.checkBlockStay(world, x, y, z);
		}
	}
	
	/**
	 * 
	 * Checks if the block can stay at the current position, if it can't, it drops it as an item.
	 * 
	 * @param world the world of the sapling.
	 * @param x xPosition of the sapling.
	 * @param y yPosition of the sapling.
	 * @param z zPosition of the sapling.
	 * @return boolean if it can stay or not.
	 */
	private boolean checkBlockStay(World world, int x, int y, int z) {
		if (!this.canBlockStay(world, x, y, z)) {
			this.dropBlockAsItem(world, x, y, z, getPlantMetadata(world, x, y, z), 0);
			world.setBlockToAir(x, y, z);
			return false;
		} else {
			return true;
		}
	}
	
	
	/**
	 * 
	 * This code is called whenever the sapling is ready to grow as a tree. Override this to make your sapling actually grow.
	 * 
	 * @param world World the sapling is in.
	 * @param x xPosition of the sapling.
	 * @param y yPosition of the sapling.
	 * @param z zPosition of the sapling.
	 */
	public void growTree(World world, int x, int y, int z) {
		
	}
	
	/**
	 * The code to bonemeal this plant, defaults to using the updateTick.
	 * 
	 * @param world the world the sapling is in.
	 * @param x xPosition of the sapling.
	 * @param y yPosition of the sapling.
	 * @param z zPosition of the sapling.
	 * @param random an rng to use for stuff.
	 * @return if the bonemeal was succesfull or not.
	 */
	public boolean boneMeal(World world, int x, int y, int z, Random random) {
		this.updateTick(world, x, y, z, random);
		return true;
	}
	
	
	//Makes sure you can walk through the sapling.
	@Override
	public AxisAlignedBB getCollisionBoundingBoxFromPool(World world, int x, int y, int z) {
        return null;
    }
	
	//Makes sure blocks around the sapling have their sides rendered like they should.
	@Override
	public boolean isOpaqueCube() {
		return false;
	}

	//Makes sure it isn't rendered as a 1x1 cube.
	@Override
	public boolean renderAsNormalBlock() {
		return false;
	}
	
	//Sets the render type to that of flowers and saplings.
	@Override
	public int getRenderType() {
		return 1;
	}

	//Gets the plant type for planting purposes mostly.
	@Override
	public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
		return type;
	}

	//Stupid vanilla code.
	@Override
	public Block getPlant(IBlockAccess world, int x, int y, int z) {
		return this;
	}

	//Returns the meta this plant should drop when broken, if it has multiple (As indicated in BioBlock), it should drop the meta of the plant that's there.
	@Override
	public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
		if (this.hasMultiple((World)world, x, y, z)) {
			return world.getBlockMetadata(x, y, z);
		} else {
			return 0;
		}
	}
	

}
