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

public class ItemSpecialBonemeals extends BioItem {
	
	

	public ItemSpecialBonemeals(String name) {
		super(name);
		setHasSubtypes(true);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {}
	
	@Override
	@SideOnly(Side.CLIENT)
	public IIcon getIconFromDamage(int dmg) {
		return Items.dye.getIconFromDamage(15);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public int getColorFromItemStack(ItemStack itemStack, int pass) {
        return ItemInfo.BONEMEALCOLOURS[itemStack.getItemDamage()];
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List items) {
		for (int i = 0; i < ItemInfo.BONEMEALCOLOURS.length; i++) {
			items.add(new ItemStack(item, 1, i));
		}
    }
	
	public String getUnlocalizedName(ItemStack itemStack) {
        return this.getUnlocalizedName() + itemStack.getItemDamage();
    }
	

}
