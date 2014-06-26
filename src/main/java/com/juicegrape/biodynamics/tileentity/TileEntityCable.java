package com.juicegrape.biodynamics.tileentity;

import java.util.ArrayList;
import java.util.List;

import net.minecraftforge.common.util.ForgeDirection;

import com.juicegrape.biodynamics.tileentity.common.TileEntityBattery;

public class TileEntityCable extends TileEntityBattery  {
	
	private List<ForgeDirection> receive;
	private int timer;

	public TileEntityCable() {
		super(200, 100, 200);
		receive = new ArrayList<ForgeDirection>();
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			receive.add(dir);
		}
		timer = 0;
	}
	
	
	@Override
	public int receiveEnergy(ForgeDirection from, int maxReceive, boolean simulate) {
		if (receive.contains(from))
			receive.remove(from);
		return super.receiveEnergy(from, maxReceive, simulate);
	}
	
	@Override
	protected ForgeDirection[] getOutputDirections() {
		return receive.toArray(new ForgeDirection[]{null});
	}
	
	@Override
	public void updateEntity() {
		timer++;
		if (timer >= 20) {
			for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
				receive.add(dir);
			}
			timer = 0;
		}
		super.updateEntity();
	}
	
//TODO: make it not send power to where it received it from
}
