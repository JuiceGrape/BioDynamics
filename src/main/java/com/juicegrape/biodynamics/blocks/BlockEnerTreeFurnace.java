package com.juicegrape.biodynamics.blocks;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.blocks.common.BioTileEntityBlock;
import com.juicegrape.biodynamics.client.guis.GuiInfo;
import com.juicegrape.biodynamics.tileentity.TileEntityEnerTreeFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockEnerTreeFurnace extends BioTileEntityBlock {

	protected BlockEnerTreeFurnace(String name) {
		super(Material.wood, name);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntityEnerTreeFurnace furnace = (TileEntityEnerTreeFurnace)world.getTileEntity(x, y, z);
		if (furnace != null) {
			player.openGui(biodynamics.instance, GuiInfo.GUI_ENERTREEFURNACE_ID, world, x, y, z);
		}
		return true;
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityEnerTreeFurnace();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		TileEntityEnerTreeFurnace furnace = (TileEntityEnerTreeFurnace)world.getTileEntity(x, y, z);
		if (furnace == null) {
			return;
		}
		if (!furnace.isBurning()) {
			return;
		}
		spawnParticles("reddust", world, x, y, z, random);
		
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		TileEntityEnerTreeFurnace furnace = (TileEntityEnerTreeFurnace)world.getTileEntity(x, y, z);
		if (furnace == null) {
			return 0;
		} else {
			if (furnace.isBurning()) {
				return 15;
			} else {
				return 0;
			}
		}
	}
	
	protected void spawnParticles(String particles, World world, int x, int y, int z, Random random) {
		float particleX = x + random.nextFloat();
		float particleY = y + random.nextFloat() + 1;
		if (particleY > y + 1.5) {
			particleY -= 0.5;
		}
		float particleZ = z + random.nextFloat();
		float particleMotionX = 0F;
		float particleMotionY = 0F;
		float particleMotionZ = 0F;
		
		world.spawnParticle(particles, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
}
	
	

}
