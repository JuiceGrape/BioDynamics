package com.juicegrape.biodynamics.blocks.generators;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.blocks.common.BioGeneratorBlock;
import com.juicegrape.biodynamics.tileentity.TileEntitySoil;
import com.juicegrape.biodynamics.tileentity.generators.TileEntitySolarFlower;

public class BlockSolarFlower extends BioGeneratorBlock {

	public BlockSolarFlower(String name) {
		super(Material.plants, name);
		this.setStepSound(soundTypeGrass);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySolarFlower();
	}

}
