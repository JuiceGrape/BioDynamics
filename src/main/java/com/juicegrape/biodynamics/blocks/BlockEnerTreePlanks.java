package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.blocks.common.BioBlock;
import com.juicegrape.biodynamics.blocks.common.energy.IEnergyTransferer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEnerTreePlanks extends BioBlock {

	protected BlockEnerTreePlanks(String name) {
		super(Material.wood, name);
	}

}
