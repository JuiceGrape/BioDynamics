package com.juicegrape.biodynamics.misc;

import net.minecraftforge.common.util.ForgeDirection;

public class ForgeDirectionSidedHelper {
	
	protected static ForgeDirection[] facingDirs = new ForgeDirection[] {ForgeDirection.NORTH, ForgeDirection.EAST, ForgeDirection.SOUTH, ForgeDirection.WEST};
	
	public static ForgeDirection getFacingFromInt(int dir) {
		return facingDirs[dir];
	}
	
	public static ForgeDirection getLeftFromFacing(ForgeDirection facing) {
		switch (facing) {
		case EAST:
			return ForgeDirection.SOUTH;
		case NORTH:
			return ForgeDirection.EAST;
		case WEST:
			return ForgeDirection.NORTH;
		case SOUTH:
			return ForgeDirection.WEST;
		default:
			return null;
		}
	}
	
	public static ForgeDirection getRightFromFacing(ForgeDirection facing) {
		switch (facing) {
		case EAST:
			return ForgeDirection.NORTH;
		case NORTH:
			return ForgeDirection.WEST;
		case WEST:
			return ForgeDirection.SOUTH;
		case SOUTH:
			return ForgeDirection.EAST;
		default:
			return null;
		}
	}

}
