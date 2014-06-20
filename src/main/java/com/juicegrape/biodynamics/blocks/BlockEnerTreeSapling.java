package com.juicegrape.biodynamics.blocks;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;

import com.juicegrape.biodynamics.blocks.common.BioBlockSapling;

/**
 * 
 * @author JuiceGrape
 *
 */
public class BlockEnerTreeSapling extends BioBlockSapling {
	
	Random random;

	protected BlockEnerTreeSapling() {
		super(BlockInfo.ENERTREESAPLING, EnumPlantType.Plains);
		float f = 0.4F;
        this.setBlockBounds(0.5F - f, 0.0F, 0.5F - f, 0.5F + f, f * 2.0F, 0.5F + f);
        random = new Random();
	}
	
	@Override
	public void growTree(World world, int x, int y, int z) {
		if (world.isRemote) {
			return;
		}
		int height = 0;
		for (int i = 1; i <= this.maxHeight + 1; i++) {
			if (world.isAirBlock(x, y + i, z)) {
				if (height + 1 == i) {
					height = i;
				}
			}
		}
		if (height + 1 < minHeight) {
			return;
		}
		
		int growHeight;
		
		if (height - minHeight > 0) {
			growHeight = minHeight + random.nextInt(height - minHeight);
		} else {
			growHeight = minHeight;
		}
		
		
		for (int i = 0; i < growHeight; i ++) {
			world.setBlock(x, y + i, z, ModBlocks.enertreeLogs);
		}
		
		for (int i = -1; i <= 0; i ++) {
			for (int j = -1; j <= 1; j++) {
				if (world.isAirBlock(x + j, y + growHeight - 1 + i, z))
					world.setBlock(x + j, y + growHeight - 1 + i, z, ModBlocks.enertreeLeaves);
					
				if (world.isAirBlock(x, y + growHeight - 1 + i, z + j))
					world.setBlock(x, y + growHeight - 1 + i, z + j, ModBlocks.enertreeLeaves);
			}
		}
		if (world.isAirBlock(x, y + growHeight, z))
			world.setBlock(x, y + growHeight, z, ModBlocks.enertreeLeaves);
		
		
		
	}
	
}
