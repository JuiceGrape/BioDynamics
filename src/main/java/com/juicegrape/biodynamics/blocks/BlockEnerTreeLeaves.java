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

/**
 * 
 * @author JuiceGrape
 *
 */
public class BlockEnerTreeLeaves extends BlockLeaves {
	
	private IIcon leaves;
	private IIcon leaves_Opaque;
	
	public BlockEnerTreeLeaves() {
		super();
		this.setBlockName(BlockInfo.ENERTREELEAVES);
		setCreativeTab(biodynamics.bioTab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		leaves = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("tile.", "") + "_block");
		leaves_Opaque = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("tile.", "") + "_opaque_block");
	}

	@Override
	public IIcon getIcon(int var1, int var2) {
		if (this.isOpaqueCube()) {
			return leaves_Opaque;
		} else {
			return leaves;
		}
	}

	@Override
	public String[] func_150125_e() {
		return new String[]{"enerTree"};
	}

	 
	 @Override
	 public boolean isOpaqueCube() {
		 return Blocks.leaves.isOpaqueCube();
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
