package com.juicegrape.biodynamics.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.blocks.common.BioTileEntityBlock;
import com.juicegrape.biodynamics.tileentity.TileEntityMutatinator;
import com.juicegrape.biodynamics.tileentity.TileEntityTreeFarm;

public class BlockTreeFarm extends BioTileEntityBlock {

	Random random;
	protected BlockTreeFarm(String name) {
		super(Material.iron, name);
		random = new Random();
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityTreeFarm();
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int fortune) {
		TileEntityTreeFarm treefarm = (TileEntityTreeFarm)world.getTileEntity(x, y, z);

		if (treefarm != null) {
			for (int i = 0; i < treefarm.getSizeInventory(); i++) {
				if (treefarm.getStackInSlot(i) != null) {
					float xItem = 0.8F * random.nextFloat() + 0.1F;
					float yItem = 0.8F * random.nextFloat() + 0.1F;
					float zItem = 0.8F * random.nextFloat() + 0.1F;
					
					EntityItem item = new EntityItem(world, x + xItem, y + yItem, z + zItem, treefarm.getStackInSlot(i));
					item.motionX = random.nextGaussian() * 0.05;
					item.motionY = random.nextGaussian() * 0.05 + 0.2;
					item.motionZ = random.nextGaussian() * 0.05;
					
					
					world.spawnEntityInWorld(item);
				}
			}
		}
		super.breakBlock(world, x, y, z, block, fortune);
		
	}

}
