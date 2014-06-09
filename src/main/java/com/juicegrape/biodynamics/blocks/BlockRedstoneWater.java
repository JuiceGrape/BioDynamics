package com.juicegrape.biodynamics.blocks;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import net.minecraftforge.fluids.Fluid;

import com.juicegrape.biodynamics.blocks.common.BioLiquid;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockRedstoneWater extends BioLiquid {
	
	private IIcon stillIcon;
	private IIcon flowingIcon;

	public BlockRedstoneWater(Fluid fluid, String name) {
		super(fluid, Material.water, name);
		fluid.setBlock(this);
	}
	
	@Override
    public IIcon getIcon(int side, int meta) {
            return (side == 0 || side == 1)? stillIcon : flowingIcon;
    }
	
	 @SideOnly(Side.CLIENT)
     @Override
     public void registerBlockIcons(IIconRegister register) {
             stillIcon = register.registerIcon("water_still");
             flowingIcon = register.registerIcon("water_flow");
	 }
	 
	 @Override
	 @SideOnly(Side.CLIENT)
	 public int colorMultiplier(IBlockAccess world, int x, int y, int z){
		 return 0xE82020;
	 }

}
