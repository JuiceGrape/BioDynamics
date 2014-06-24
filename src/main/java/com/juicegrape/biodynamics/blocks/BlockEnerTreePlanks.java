package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.material.Material;

import com.juicegrape.biodynamics.blocks.common.BioBlock;

/**
 * 
 * @author JuiceGrape
 *
 */
public class BlockEnerTreePlanks extends BioBlock {

	protected BlockEnerTreePlanks(String name) {
		super(Material.wood, name);
		this.setStepSound(soundTypeWood);
	}

}
