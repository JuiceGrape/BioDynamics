package com.juicegrape.biodynamics.blocks.common;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.blocks.BlockInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author JuiceGrape
 *
 * A simple block code to set the basic things for me
 *
 *
 */
public class BioBlock extends Block {
	
	/**
	 * 
	 * Set's the unlocalized name and the creative tab.
	 * 
	 * @param mat the material to make the block
	 * @param name the unlocalized name that the block will get. Also determines the texture name.
	 */
	protected BioBlock(Material mat, String name) {
		super(mat);
		setBlockName(name);
		setCreativeTab(biodynamics.bioTab);
	}
	
	


	 //Automatically registers the blockIcon based on the unlocalized name.
	 //Override this if you want multiple Icons

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("tile.", "") + "_block");
	}

	/**
	 * 
	 * @param world world the block is in
	 * @param x xCoordinate of the block
	 * @param y yCoordinate of the block
	 * @param z zCoordinate of the block
	 * @return if this block has multiple blocks in itself based on metadata.
	 */
	public boolean hasMultiple(World world, int x, int y, int z) {
		return false;
	}
	
	
}
