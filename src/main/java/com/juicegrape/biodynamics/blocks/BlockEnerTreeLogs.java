package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.blocks.common.energy.IEnergyTransferer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEnerTreeLogs extends BlockLog {
	
	public BlockEnerTreeLogs() {
		super();
		this.setBlockName(BlockInfo.ENERTREELOGS);
		this.setCreativeTab(biodynamics.bioTab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public int getBlockColor() {
		return 0x3B3232;
	}
	 
	 @Override
	 @SideOnly(Side.CLIENT)
	 public int getRenderColor(int meta) {
		 return this.getBlockColor();
	 }
	 
	 @SideOnly(Side.CLIENT)
	 public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		 return this.getRenderColor(world.getBlockMetadata(x, y, z));
	 } 
	 
	 @Override
	 public IIcon getIcon(int var1, int var2) {
		 return Blocks.log2.getIcon(var1, var2 + 2);
	 }

}
