package com.juicegrape.biodynamics.blocks.common;

import com.juicegrape.biodynamics.biodynamics;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

public class BioBlock extends Block {

	protected BioBlock(Material mat, String name) {
		super(mat);
		setBlockName(name);
		setCreativeTab(biodynamics.bioTab);
	}

}
