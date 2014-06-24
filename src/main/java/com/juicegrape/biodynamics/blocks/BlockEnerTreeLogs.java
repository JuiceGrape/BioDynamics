package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import com.juicegrape.biodynamics.biodynamics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author JuiceGrape
 *
 */
public class BlockEnerTreeLogs extends BlockLog {
	
	private IIcon topIcon;
	private IIcon sideIcon;
	
	public BlockEnerTreeLogs() {
		super();
		this.setBlockName(BlockInfo.ENERTREELOGS);
		this.setCreativeTab(biodynamics.bioTab);
		this.setStepSound(soundTypeWood);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		topIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("tile.", "") + "_top_block");
		sideIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("tile.", "") + "_block");

	}
	
	
	@SideOnly(Side.CLIENT)
	@Override
	protected IIcon getSideIcon(int meta) {
		return sideIcon;
	}
	
	@SideOnly(Side.CLIENT)
	@Override
	protected IIcon getTopIcon(int meta) {
		return topIcon;
	}

}
