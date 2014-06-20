package com.juicegrape.biodynamics.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.blocks.ModBlocks;
import com.juicegrape.biodynamics.items.common.BioItem;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author JuiceGrape
 *
 */
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
	
	@Override
	public boolean onEntityItemUpdate(EntityItem entityItem) {
		if (entityItem.worldObj.isRemote)
			return false;
		ItemStack item = entityItem.getEntityItem();
		if (item.getItemDamage() == 0) {
			double x = entityItem.posX - (entityItem.posX % 1);
			if (entityItem.posX < 0)
				x--;
			double y = entityItem.posY - (entityItem.posY % 1);
			if (entityItem.posY  <= 0)
				y--;
			double z = entityItem.posZ - (entityItem.posZ % 1);
			if (entityItem.posZ <= 0)
				z--;
			
			if (entityItem.worldObj.getBlock((int)x, (int)y, (int)z).equals(Blocks.water)
					&& entityItem.worldObj.getBlockMetadata((int)x, (int)y, (int)z) == 0
					) {
				System.out.println("yes");
				if (item.stackSize == 1) {
					entityItem.setDead();
				} else {
					item.stackSize--;
				}
				
				entityItem.worldObj.setBlock((int)x, (int)y, (int)z, ModBlocks.redstoneWater);
				entityItem.worldObj.markBlockForUpdate((int)x, (int)y, (int)z);
				
				
			}
		}
        return false;
    }
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player) {
		return itemStack;
	}
}
