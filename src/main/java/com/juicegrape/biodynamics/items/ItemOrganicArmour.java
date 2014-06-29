package com.juicegrape.biodynamics.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.biodynamics;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemOrganicArmour extends ItemArmor {

	public ItemOrganicArmour(String name, ArmorMaterial mat, int renderIndex, int slot) {
		super(mat, renderIndex, slot);
		this.setCreativeTab(biodynamics.bioTab);
		this.setUnlocalizedName(name);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister register) {
		itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + "armour/" + this.getUnlocalizedName().replace("item.", "") + "_item");
	}
	
	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) {
		if (this.getUnlocalizedName().contains("organic_iron")) {
			switch (slot) {
			case 0:
			case 1:
			case 3:
				return ItemInfo.TEXTURE_LOCATION + ":textures/models/armour/" + "organic_iron_layer_1.png";
			case 2:
				return ItemInfo.TEXTURE_LOCATION + ":textures/models/armour/" + "organic_iron_layer_2.png";
			}
		} else if (this.getUnlocalizedName().contains("organic_gold")) {
			switch (slot) {
			case 0:
			case 1:
			case 3:
				return ItemInfo.TEXTURE_LOCATION + ":textures/models/armour/" + "organic_gold_layer_1.png";
			case 2:
				return ItemInfo.TEXTURE_LOCATION + ":textures/models/armour/" + "organic_gold_layer_2.png";
			}
		} else if (this.getUnlocalizedName().contains("pink_diamond")) {
			switch (slot) {
			case 0:
			case 1:
			case 3:
				return ItemInfo.TEXTURE_LOCATION + ":textures/models/armour/" + "pink_diamond_layer_1.png";
			case 2:
				return ItemInfo.TEXTURE_LOCATION + ":textures/models/armour/" + "pink_diamond_layer_2.png";
			}
		}
		return "";
	}
	
	@Override
	public void onArmorTick(World world, EntityPlayer player, ItemStack stack) {
		
	}
	

}
