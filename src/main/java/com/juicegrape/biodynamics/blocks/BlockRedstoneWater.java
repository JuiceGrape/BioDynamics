package com.juicegrape.biodynamics.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.oredict.OreDictionary;

import com.juicegrape.biodynamics.blocks.common.BioLiquid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRedstoneWater extends BioLiquid {
	
	private IIcon stillIcon;
	private IIcon flowingIcon;
	
	private ItemStack sap = new ItemStack(Blocks.sapling);

	public BlockRedstoneWater(Fluid fluid, String name) {
		super(fluid, Material.water, name);
		fluid.setBlock(this);
	}
	
	@Override
    public IIcon getIcon(int side, int meta) {
            return (side == 0 || side == 1)? stillIcon : flowingIcon;
    }
	
	 @SideOnly(Side.CLIENT)
     @Override
     public void registerBlockIcons(IIconRegister register) {
             stillIcon = register.registerIcon("water_still");
             flowingIcon = register.registerIcon("water_flow");
	 }
	 
	 @Override
	 @SideOnly(Side.CLIENT)
	 public int colorMultiplier(IBlockAccess world, int x, int y, int z){
		 return 0xFF0000;
	 }
	 
	 @Override
	 public void updateTick(World world, int x, int y, int z, Random random) {
		 int checkx = x;
		 int checky = y + 1;
		 int checkz = z;
		 if (world.getBlockMetadata(x, y, z) == 0) {
			 for (int i = -5; i <= 5; i++) {
				 for (int j = -5; j <= 5; j++) {
					 Block sapling = world.getBlock(checkx + i, checky, checkz + j);
					 if (sapling != null) {
						 if (random.nextInt(50) == 1 && !(sapling instanceof BlockEnerTreeSapling) &&
								OreDictionary.getOreName(OreDictionary.getOreID(new ItemStack(sapling))).equals(
								OreDictionary.getOreName(OreDictionary.getOreID(sap)) )) {
							 
							 continue;
						 }
					 }
				 }
			 }
		 }
	 }

}
