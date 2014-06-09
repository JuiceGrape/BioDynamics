package com.juicegrape.biodynamics.blocks;

import java.util.Random;

import com.juicegrape.biodynamics.biodynamics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;

public class BlockEnerTreeLeaves extends BlockLeaves {
	
	public BlockEnerTreeLeaves() {
		super();
		this.setBlockName(BlockInfo.ENERTREELEAVES);
		setCreativeTab(biodynamics.bioTab);
	}

	@Override
	public IIcon getIcon(int var1, int var2) {
		return Blocks.leaves.getIcon(var1, var2);
	}

	@Override
	public String[] func_150125_e() {
		return new String[]{"enerTree"};
	}

	
	@Override
	@SideOnly(Side.CLIENT)
    public int getBlockColor() {
		return 0xFF0000;
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
	 public boolean isOpaqueCube() {
		 return false;
	 }
	 
	 @Override
	 public Item getItemDropped(int p_149650_1_, Random p_149650_2_, int p_149650_3_){
		 return Item.getItemFromBlock(ModBlocks.enerTreeSapling);
	 }
	 
	 @Override
	 public int quantityDropped(Random random) {
		 return random.nextInt(40) == 0 ? 1 : 0;
	 }
	 
	 
	
	

}
