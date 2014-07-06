package com.juicegrape.biodynamics.blocks.generators;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.blocks.common.BioGeneratorBlock;
import com.juicegrape.biodynamics.tileentity.generators.TileEntityBurningFlower;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockBurningFlower extends BioGeneratorBlock {

	public BlockBurningFlower(String name) {
		super(Material.plants, name);
		this.setStepSound(soundTypeGrass);
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityBurningFlower();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (world.isRemote) {
			return true;
		}
		TileEntityBurningFlower burning = (TileEntityBurningFlower)world.getTileEntity(x, y, z);
		if (burning == null) {
			return false;
		}
		ItemStack stack = player.getCurrentEquippedItem();
		if (stack == null) {
			if (burning.burnable[0] != null)
			System.out.println(burning.burnable[0].toString());
			return false;
		}
		
		
		if(burning.getStackInSlot(0) == null) {
			if (burning.isItemValidForSlot(0, stack)) {
				burning.setInventorySlotContents(0, stack);
				player.setCurrentItemOrArmor(0, null);
				return true;
			}
		} else {
			if (burning.isBurning()) {
				System.out.println("Has item and is burning");
			} else {
				System.out.println("has item but is not burning");
			}
		}
		
		return false;
		
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		TileEntityBurningFlower burning = (TileEntityBurningFlower)world.getTileEntity(x, y, z);
		if (burning == null) {
			return;
		}
		if (burning.isBurning()) {
			spawnParticles("flame", world, x, y, z, random);
		}
	}
	
	protected void spawnParticles(String particles, World world, int x, int y, int z, Random random) {
		float particleX = x + random.nextFloat();
		if (particleX > x + 0.75)
			particleX -= 0.25;
		if (particleX < x + 0.25)
			particleX += 0.25;
		float particleY = y + 1.2f;
		float particleZ = z + random.nextFloat();
		if (particleZ > z + 0.75)
			particleZ -= 0.25;
		if (particleZ < z + 0.25)
			particleZ += 0.25;
		float particleMotionX = 0F;
		float particleMotionY = 0F;
		float particleMotionZ = 0F;
		
		world.spawnParticle(particles, particleX, particleY, particleZ, particleMotionX, particleMotionY, particleMotionZ);
}

}
