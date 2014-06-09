package com.juicegrape.biodynamics.blocks.common;

import com.juicegrape.biodynamics.biodynamics;

import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class BioTileEntityBlock extends BlockContainer {
	
	private TileEntity te;

	protected BioTileEntityBlock(Material mat, String name, TileEntity tileEntity) {
		super(mat);
		setBlockName(name);
		setCreativeTab(biodynamics.bioTab);
		te = tileEntity;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return te;
	}

}
