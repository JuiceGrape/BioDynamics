package com.juicegrape.biodynamics.blocks;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.blocks.common.energy.IEnergyTransferer;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.init.Blocks;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockEnerTreeLogs extends BlockLog implements IEnergyTransferer {
	
	public BlockEnerTreeLogs() {
		super();
		this.setBlockName(BlockInfo.ENERTREELOGS);
		this.setCreativeTab(biodynamics.bioTab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public int getBlockColor() {
		return 0x4F4747;
	}
	 
	 @Override
	 @SideOnly(Side.CLIENT)
	 public int getRenderColor(int meta) {
		 return this.getBlockColor();
	 }
	 
	 @SideOnly(Side.CLIENT)
	 public int colorMultiplier(IBlockAccess world, int x, int y, int z) {
		 return this.getBlockColor();
	 }
	 
	 @Override
	 public IIcon getIcon(int var1, int var2) {
		 return Blocks.log.getIcon(var1, var2);
	 }

	@Override
	public int getTransferRate() {
		return 200;
	}

}
