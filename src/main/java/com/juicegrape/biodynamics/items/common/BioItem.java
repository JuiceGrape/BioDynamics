package com.juicegrape.biodynamics.items.common;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.items.ItemInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author JuiceGrape
 *
 */
public class BioItem extends Item {
	
	public BioItem(String name) {
		super();
		this.setUnlocalizedName(name);
		this.setCreativeTab(biodynamics.bioTab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("item.", "") + "_item");
	}

}
