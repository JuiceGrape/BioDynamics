package com.juicegrape.biodynamics.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.client.guis.GuiEnerTreeFurnace;
import com.juicegrape.biodynamics.client.guis.GuiInfo;
import com.juicegrape.biodynamics.tileentity.TileEntityEnerTreeFurnace;

public class ClientProxy extends CommonProxy {

	@Override
	public boolean isClient() {
		return true;
	}
	
	@Override
	public void registerTileEntities() {
		super.registerTileEntities();;
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case GuiInfo.GUI_ENERTREEFURNACE_ID:
			return new GuiEnerTreeFurnace(player.inventory ,(TileEntityEnerTreeFurnace)world.getTileEntity(x, y, z));
		default:
			return null;
		}
	}
	

}
