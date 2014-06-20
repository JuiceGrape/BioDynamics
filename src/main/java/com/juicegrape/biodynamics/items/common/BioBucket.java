package com.juicegrape.biodynamics.items.common;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.items.ItemInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBucket;

/**
 * 
 * @author JuiceGrape
 *
 */
public class BioBucket extends ItemBucket {

	public BioBucket(Block fluid, String name) {
		super(fluid);
		this.setUnlocalizedName(name);
		this.setCreativeTab(biodynamics.bioTab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("item.", "") + "_item");
	}

}
