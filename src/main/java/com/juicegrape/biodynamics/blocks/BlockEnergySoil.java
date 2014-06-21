package com.juicegrape.biodynamics.blocks;

import java.util.List;

import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.blocks.common.BioTileEntityBlock;
import com.juicegrape.biodynamics.tileentity.TileEntityBatteTreeCore;
import com.juicegrape.biodynamics.tileentity.TileEntitySoil;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEnergySoil extends BioTileEntityBlock {

	protected BlockEnergySoil(String name) {
		super(Material.grass, name);
	}
	
	
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntitySoil(meta + 1);
	}
	
	@Override
	public int damageDropped (int meta) {
		return meta;
	}
	
	
	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(Item item, CreativeTabs tab, List list) {
		for (int i = 0; i < 3; i++) {
			list.add(new ItemStack(item, 1, i));
		}
		
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntitySoil core = (TileEntitySoil)world.getTileEntity(x, y, z);
		if (!world.isRemote && core != null) {
			core.printEnergy();
		}
		return true;
	}
	
	
}
