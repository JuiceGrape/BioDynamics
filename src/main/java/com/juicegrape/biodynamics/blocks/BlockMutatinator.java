package com.juicegrape.biodynamics.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.blocks.common.BioTileEntityBlock;
import com.juicegrape.biodynamics.client.guis.GuiInfo;
import com.juicegrape.biodynamics.tileentity.TileEntityEnerTreeFurnace;
import com.juicegrape.biodynamics.tileentity.TileEntityMutatinator;


public class BlockMutatinator extends BioTileEntityBlock {
	
	Random random;

	protected BlockMutatinator(String name) {
		super(Material.iron, name);
		random = new Random();
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int meta) {
		return new TileEntityMutatinator();
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		TileEntityMutatinator mutate = (TileEntityMutatinator)world.getTileEntity(x, y, z);
		if (mutate != null) {
			player.openGui(biodynamics.instance, GuiInfo.GUI_MUTATINATOR_ID, world, x, y, z);
		}

		return true;
	}
	
	@Override
    public void onBlockPlacedBy(World world, int x, int y, int z, EntityLivingBase entityliving, ItemStack itemStack) {
		int dir = MathHelper.floor_double((double)((entityliving.rotationYaw * 4F) / 360F) + 0.5D) & 3;
        world.setBlockMetadataWithNotify(x, y, z, dir, 3);
    }
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int fortune) {
		TileEntityMutatinator mutatinator = (TileEntityMutatinator)world.getTileEntity(x, y, z);

		if (mutatinator != null) {
			for (int i = 0; i < mutatinator.getSizeInventory(); i++) {
				if (mutatinator.getStackInSlot(i) != null) {
					float xItem = 0.8F * random.nextFloat() + 0.1F;
					float yItem = 0.8F * random.nextFloat() + 0.1F;
					float zItem = 0.8F * random.nextFloat() + 0.1F;
					
					EntityItem item = new EntityItem(world, x + xItem, y + yItem, z + zItem, mutatinator.getStackInSlot(i));
					item.motionX = random.nextGaussian() * 0.05;
					item.motionY = random.nextGaussian() * 0.05 + 0.2;
					item.motionZ = random.nextGaussian() * 0.05;
					
					
					world.spawnEntityInWorld(item);
				}
			}
		}
		super.breakBlock(world, x, y, z, block, fortune);
		
	}
  

}
