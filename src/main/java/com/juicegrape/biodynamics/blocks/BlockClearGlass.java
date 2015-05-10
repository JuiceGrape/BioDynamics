package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.blocks.common.BioBlock;
import com.juicegrape.biodynamics.misc.ConnectedTextureHelper;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockClearGlass extends BioBlock {
	
	public static final int ICON_NUMBER = 47;
	protected IIcon[] icons = new IIcon[ICON_NUMBER];

	protected BlockClearGlass(String name) {
		super(Material.glass, name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
		return blockAccess.getBlockMetadata(x, y, z) == 15 ? icons[0] : ConnectedTextureHelper.getConnectedBlockTexture(blockAccess, x, y, z, side, icons, this);
	}
	
	public boolean isOpaqueCube() {
        return false;
    }
	
	 @Override
	 public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side) {
		 if (blockAccess.getBlock(x, y, z) == this || blockAccess.getBlock(x, y, z) == ModBlocks.naturalglass) {
			 return false;
		 }
		 return super.shouldSideBeRendered(blockAccess, x, y, z, side);
	 }
	
	@Override
	public void registerBlockIcons(IIconRegister register) {
		blockIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + "natural_glass/" +  this.getUnlocalizedName().replace("tile.", "") + "_" + 0);
		for (int i = 0; i < ICON_NUMBER; i++ ) {
			icons[i] = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + "natural_glass/" +  this.getUnlocalizedName().replace("tile.", "") + "_" + i);
		}
	}
	
	@Override
    public boolean canPlaceTorchOnTop(World world, int x, int y, int z) {
        return true;
    }

}
