package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.blocks.common.BioTileEntityBlock;
import com.juicegrape.biodynamics.client.guis.GuiInfo;
import com.juicegrape.biodynamics.tileentity.TileEntityEnerTreeFurnace;
import com.juicegrape.biodynamics.tileentity.TileEntityMutatinator;


public class BlockMutatinator extends BioTileEntityBlock {

	protected BlockMutatinator(String name) {
		super(Material.iron, name);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityMutatinator();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntityMutatinator mutate = (TileEntityMutatinator)world.getTileEntity(x, y, z);
		if (mutate != null) {
			player.openGui(biodynamics.instance, GuiInfo.GUI_MUTATINATOR_ID, world, x, y, z);
		}

		return true;
	}

}
