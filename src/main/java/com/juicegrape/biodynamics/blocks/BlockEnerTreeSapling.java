package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;

import com.juicegrape.biodynamics.blocks.common.BioBlock;
import com.juicegrape.biodynamics.blocks.common.BioBlockSapling;

public class BlockEnerTreeSapling extends BioBlockSapling {

	protected BlockEnerTreeSapling() {
		super(BlockInfo.ENERTREESAPLING, EnumPlantType.Plains);
		float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
	}
}
