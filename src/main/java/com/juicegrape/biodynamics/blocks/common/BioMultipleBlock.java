package com.juicegrape.biodynamics.blocks.common;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.juicegrape.biodynamics.blocks.BlockInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
/**
 * Unused
 * @author JuiceGrape
 *
 */
public class BioMultipleBlock extends Block {
	
	IIcon[] icons;
	int amount;

	protected BioMultipleBlock(Material mat, String name, int amount) {
		super(mat);
		this.amount = amount;
	}
	
		@SuppressWarnings({ "rawtypes", "unchecked" })
	    @Override
	    @SideOnly(Side.CLIENT)
	    public void getSubBlocks(Item item, CreativeTabs tab, List list) {
	        for (int i = 0; i < amount; i++)
	            list.add(new ItemStack(item, 1, i));
	    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		icons = new IIcon[amount];
		for (int i = 0; i < amount; i++) {
			icons[i] = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("tile.", "") + "_block" + i);
		}
	}
	
	@Override
	public int damageDropped(int damage) {
		return damage;
	}
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIcon(int side, int meta) {
		return icons[meta];
	}

}
