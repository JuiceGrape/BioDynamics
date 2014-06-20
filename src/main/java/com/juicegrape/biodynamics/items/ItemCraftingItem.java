package com.juicegrape.biodynamics.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import com.juicegrape.biodynamics.items.common.BioItem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemCraftingItem extends BioItem {
	
	IIcon[] icons;

	public ItemCraftingItem(String name) {
		super(name);
		setHasSubtypes(true);
	}
	
	public String getUnlocalizedName(ItemStack stack){
        return "item." + ItemInfo.CRAFTINGITEMNAMES[stack.getItemDamage()];
    }
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		icons = new IIcon[ItemInfo.CRAFTINGITEMNAMES.length];
		for (int i = 0; i < icons.length; i++) {
			icons[i] = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.CRAFTINGITEMNAMES[i] + "_item");
		}
	}
	
	@SideOnly(Side.CLIENT)
    public IIcon getIconFromDamage(int meta) {
		if (meta >= icons.length) {
			return icons[0];
		}
        return icons[meta];
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List items) {
		for (int i = 0; i < ItemInfo.CRAFTINGITEMNAMES.length; i++) {
			items.add(new ItemStack(item, 1, i));
		}
    }

}
