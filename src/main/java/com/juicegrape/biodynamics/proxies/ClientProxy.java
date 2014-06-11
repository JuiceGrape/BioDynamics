package com.juicegrape.biodynamics.proxies;

public class ClientProxy extends CommonProxy {

	@Override
	public boolean isClient() {
		return true;
	}
	
	@Override
	public void registerTileEntities() {
		super.registerTileEntities();;
	}

}
