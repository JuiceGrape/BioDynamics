package com.juicegrape.biodynamics.tileentity.common;

import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.FluidTank;

public class SpecificTank extends FluidTank {

	Fluid specificFluid;
	
	public SpecificTank(Fluid fluid, int capacity) {
		super(capacity);
		specificFluid = fluid;
	}
	
	@Override
    public int fill(FluidStack resource, boolean doFill) {
		return resource.getFluidID() == specificFluid.getID() ? super.fill(resource, doFill) : 0;
	}
	
	

}
