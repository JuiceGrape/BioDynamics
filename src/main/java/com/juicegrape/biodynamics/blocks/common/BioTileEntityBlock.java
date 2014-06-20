package com.juicegrape.biodynamics.blocks.common;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.blocks.BlockInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

/**
 * 
 * @author JuiceGrape
 *
 */
public class BioTileEntityBlock extends BlockContainer {

	/**
	 * 
	 * Simple class to extend for Tile Entities that does alot of stuff for me.
	 * 
	 * @param mat material of the block.
	 * @param name unlocalized name of the block.
	 */
	protected BioTileEntityBlock(Material mat, String name) {
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

	//What tile entity to create, make this return a new tileentity of the sort you want.
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

}
