package com.juicegrape.biodynamics.items.tools;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.items.ItemInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.Item.ToolMaterial;

public class ItemOrganicShovel extends ItemSpade {
	
	public ItemOrganicShovel(ToolMaterial mat, String name) {
		super(mat);
		this.setUnlocalizedName(name);
		this.setCreativeTab(biodynamics.bioTab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + "tools/" + this.getUnlocalizedName().replace("item.", "") + "_item");
	}

}
