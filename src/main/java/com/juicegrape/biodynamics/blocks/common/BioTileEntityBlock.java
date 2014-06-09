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
	
	private TileEntity te;

	protected BioTileEntityBlock(Material mat, String name, TileEntity tileEntity) {
		super(mat);
		setBlockName(name);
		setCreativeTab(biodynamics.bioTab);
		te = tileEntity;
	}

	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return te;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("tile.", "") + "_block");
	}

}
