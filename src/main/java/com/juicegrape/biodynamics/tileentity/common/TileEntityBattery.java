package com.juicegrape.biodynamics.tileentity.common;

import net.minecraft.tileentity.TileEntity;

public class TileEntityBattery extends TileEntity {
	
	private int maxEnergy;
	private int currentEnergy;
	
	public TileEntityBattery(int maxEnergy) {
		this.maxEnergy = maxEnergy;
		currentEnergy = 0;
	}
	
	public int getCurrentEnergy() {
		return currentEnergy;
	}
	
	public int getMaxEnergy() {
		return maxEnergy;
	}
	
	private void setMaxEnergy(int newMax) {
		maxEnergy = newMax;
	}
	
	/**
	 * adds energy to the current amount.
	 * @param energy the amount of energy to add
	 * @return the amount of energy deficit that cannot be stored because the storage is full.
	 */
	public int addEnergy(int energy) {
		currentEnergy += energy;
		int returnEnergy = 0;
		if (currentEnergy > maxEnergy) {
			returnEnergy = maxEnergy - currentEnergy;
			currentEnergy = maxEnergy;
		}
		return returnEnergy;
	}
	
	/**
	 * removes energy from the current amount.
	 * @param energy the amount of energy to remove
	 * @return the amount of energy that can be removed, it returns @param energy if it can remove the full amount.
	 */
	public int removeEnergy(int energy) {
		int returnEnergy = energy;
		if (energy > currentEnergy) {
			returnEnergy = currentEnergy - energy;
			currentEnergy = 0;
		} else {
			currentEnergy -= energy;
		}
		return returnEnergy;
	}

}
