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

public class BioTileEntityBlock extends BlockContainer {

	protected BioTileEntityBlock(Material mat, String name) {
		super(mat);
		setBlockName(name);
		setCreativeTab(biodynamics.bioTab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("tile.", "") + "_block");
	}

	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return null;
	}

}
