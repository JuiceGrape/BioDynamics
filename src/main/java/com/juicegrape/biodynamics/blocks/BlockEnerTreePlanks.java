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

public class BlockEnerTreePlanks extends BioBlock implements IEnergyTransferer {

	protected BlockEnerTreePlanks(String name) {
		super(Material.wood, name);
	}
	
	

	@Override
	public int getTransferRate(World world, int x, int y, int z) {
		return 100;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public int getBlockColor() {
		return ModBlocks.enertreeLogs.getBlockColor();
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
		 return Blocks.planks.getIcon(var1, var2 + 6);
	 }

}
