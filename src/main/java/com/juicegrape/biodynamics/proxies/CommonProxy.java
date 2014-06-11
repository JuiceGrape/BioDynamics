package com.juicegrape.biodynamics.proxies;

import com.juicegrape.biodynamics.blocks.BlockInfo;
import com.juicegrape.biodynamics.tileentity.TileEntityEnerTreeFurnace;

import cpw.mods.fml.common.registry.GameRegistry;

public class CommonProxy {

	public boolean isClient() {
		return false;
	}
	
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityEnerTreeFurnace.class, BlockInfo.ENERTREEFURNACE);
	}

}
