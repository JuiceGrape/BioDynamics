package com.juicegrape.biodynamics.tileentity;

import com.juicegrape.biodynamics.tileentity.common.TileEntityBattery;

public class TileEntityCable extends TileEntityBattery  {

	public TileEntityCable(int tier) {
		super(800*tier*tier, 100*tier*tier, 100*tier*tier);
	}

}
