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
import com.juicegrape.biodynamics.misc.OreDictionaryHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author JuiceGrape
 *
 */
public class BlockRedstoneWater extends BioLiquid {
	
	private IIcon stillIcon;
	private IIcon flowingIcon;
	
	private ItemStack sap = new ItemStack(Blocks.sapling);

	public BlockRedstoneWater(Fluid fluid, String name) {
		super(fluid, Material.water, name);
		this.setTickRate(20);
		fluid.setBlock(this);
	}
	
	@Override
    public IIcon getIcon(int side, int meta) {
            return (side == 0 || side == 1)? stillIcon : flowingIcon;
    }
	
	 @SideOnly(Side.CLIENT)
     @Override
     public void registerBlockIcons(IIconRegister register) {
             stillIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("tile.", "") + "_still_block");
             flowingIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("tile.", "") + "_flow_block");
             ModBlocks.fluidRedstoneWater.setIcons(stillIcon, flowingIcon);
	 }
	 

	 
	 @Override
	 public void updateTick(World world, int x, int y, int z, Random random) {
		 super.updateTick(world, x, y, z, random);
		 if (world.isRemote)
			 return;
		 System.out.println("TICK");
		 int checkx = x + random.nextInt(10) - 4;
		 int checky = y + 1;
		 int checkz = z + random.nextInt(10) - 4;
		 if (world.getBlockMetadata(x, y, z) == 0) {
			 
			 Block sapling = world.getBlock(checkx, checky, checkz);
			 if (sapling != null && !(sapling instanceof BlockEnerTreeSapling) && OreDictionaryHelper.isStackEqual(new ItemStack(sapling), new ItemStack(Blocks.sapling))) {
				 
				 world.setBlock(checkx, checky, checkz, ModBlocks.enerTreeSapling);
				 world.setBlock(x, y, z, Blocks.water);
				 world.markBlockForUpdate(x, y, z);
				 
				 return;
				 
				 
			 }
			
		 }
		 if (!world.isBlockTickScheduledThisTick(x, y, z, this)) {
			 world.scheduleBlockUpdate(x, y, z, this, this.tickRate);
		 }
		 
		 
		 
	 }
	 
	 public boolean canProvidePower() {
	        return true;
	 }

	 public int isProvidingStrongPower(IBlockAccess world, int x, int y, int z, int side) {
		 int returnvalue =15 - world.getBlockMetadata(x, y, z) * 2;
		 return returnvalue > 0 ? returnvalue : 0;
	 }
	 
	/* public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		 
		 return false;
	/*	 if (world.getBlock(x, y, z).equals(Blocks.water) || world.getBlock(x, y, z).equals(Blocks.flowing_water)) {
			 return false;
		 } else {
			 return super.canDisplace(world, x, y, z);
		 } 
	 } */
	 
	 

}
