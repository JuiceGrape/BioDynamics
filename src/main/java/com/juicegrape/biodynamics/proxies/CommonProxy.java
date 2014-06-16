package com.juicegrape.biodynamics.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.blocks.BlockInfo;
import com.juicegrape.biodynamics.client.guis.GuiEnerTreeFurnace;
import com.juicegrape.biodynamics.client.guis.GuiInfo;
import com.juicegrape.biodynamics.tileentity.ContainerEnerTreeFurnace;
import com.juicegrape.biodynamics.tileentity.TileEntityBatteTreeCore;
import com.juicegrape.biodynamics.tileentity.TileEntityEnerTreeFurnace;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy implements IGuiHandler {

	public boolean isClient() {
		return false;
	}
	
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityEnerTreeFurnace.class, BlockInfo.ENERTREEFURNACE);
		GameRegistry.registerTileEntity(TileEntityBatteTreeCore.class, BlockInfo.BATTETREECORE);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case GuiInfo.GUI_ENERTREEFURNACE_ID:
			return new ContainerEnerTreeFurnace(player.inventory ,(TileEntityEnerTreeFurnace)world.getTileEntity(x, y, z));
		default:
			return null;
		}
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	

}
