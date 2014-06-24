package com.juicegrape.biodynamics.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.juicegrape.biodynamics.blocks.common.BioBlock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockCompressedMinerals extends BioBlock {
	
	IIcon[] icons;

	protected BlockCompressedMinerals(String name) {
		super(Material.iron, name);
		setStepSound(soundTypeMetal);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		icons = new IIcon[BlockInfo.MINERALBLOCKS.length];
		for (int i = 0; i < BlockInfo.MINERALBLOCKS.length; i++) {
			icons[i] = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + BlockInfo.MINERALBLOCKS[i]);	
		}
	}
	
	@Override
	public int damageDropped (int meta) {
		return meta;
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < BlockInfo.MINERALBLOCKS.length; i++) {
			list.add(new ItemStack(item, 1, i));
		}
	}
	
	@Override
    public IIcon getIcon(int side, int meta) {
		return icons[meta];
	}

}
