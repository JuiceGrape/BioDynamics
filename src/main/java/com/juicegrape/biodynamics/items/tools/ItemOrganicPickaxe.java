package com.juicegrape.biodynamics.items.tools;

import java.util.Random;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.items.ItemInfo;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemOrganicPickaxe extends ItemPickaxe {
	
	public ItemOrganicPickaxe(ToolMaterial mat, String name) {
		super(mat);
		this.setUnlocalizedName(name);
		this.setCreativeTab(biodynamics.bioTab);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + "tools/" + this.getUnlocalizedName().replace("item.", "") + "_item");
	}
	
	public void onUpdate(ItemStack stack, World world, Entity entity, int idek, boolean summing) {
		if (stack.getItemDamage() > 0) {
			if (world.rand.nextInt(500) == 1) {
				stack.setItemDamage(stack.getItemDamage() - 1);
			}
		} 	
	}

}
