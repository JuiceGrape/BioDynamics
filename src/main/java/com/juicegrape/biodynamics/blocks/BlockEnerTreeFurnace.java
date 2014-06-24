package com.juicegrape.biodynamics.blocks;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.blocks.common.BioTileEntityBlock;
import com.juicegrape.biodynamics.client.guis.GuiInfo;
import com.juicegrape.biodynamics.tileentity.TileEntityEnerTreeFurnace;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author JuiceGrape
 *
 */
public class BlockEnerTreeFurnace extends BioTileEntityBlock {

	IIcon topIcon;
	IIcon topLitIcon;
	IIcon sideIcon;
	IIcon bottomIcon;
	
	Random random = new Random();

	protected BlockEnerTreeFurnace(String name) {
		super(Material.wood, name);
		this.setStepSound(soundTypeWood);
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (player.isSneaking()) {
			System.out.println(world.isRemote);
			System.out.println(world.getBlockMetadata(x, y, z));
			return true;
		}
		TileEntityEnerTreeFurnace furnace = (TileEntityEnerTreeFurnace)world.getTileEntity(x, y, z);
		if (furnace != null) {
			player.openGui(biodynamics.instance, GuiInfo.GUI_ENERTREEFURNACE_ID, world, x, y, z);
		}

		return true;
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister register) {
		topIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("tile.", "") + "_top_block");
		topLitIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("tile.", "") + "_lit_top_block");
		sideIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("tile.", "") + "_side_block");
		bottomIcon = register.registerIcon(BlockInfo.TEXTURE_LOCATION + ":" + this.getUnlocalizedName().replace("tile.", "") + "_bottom_block");
	}
	
	@Override
	public TileEntity createNewTileEntity(World var1, int var2) {
		return new TileEntityEnerTreeFurnace();
	}
	
	@Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(World world, int x, int y, int z, Random random) {
		if (world.getBlockMetadata(x, y, z) == 1) {
			spawnParticles("reddust", world, x, y, z, random);
		}
		
		
	}
	
	@Override
	public int getLightValue(IBlockAccess world, int x, int y, int z) {
		if (world.getBlockMetadata(x, y, z) == 1) {
			return 10;
		} else {
			return 0;
		}
	} 
	
	@Override
	public IIcon getIcon(int side, int meta) {
		switch (side) {
		case 0:
			return bottomIcon;
		case 1:
			if (meta == 0) {
				return topIcon;
			} else if (meta == 1) {
				return topLitIcon;
			}
		default:
			return sideIcon;
		}
	}
	
	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int fortune) {
		TileEntityEnerTreeFurnace furnace = (TileEntityEnerTreeFurnace)world.getTileEntity(x, y, z);

		if (furnace != null) {
			for (int i = 0; i < furnace.getSizeInventory(); i++) {
				if (furnace.getStackInSlot(i) != null) {
					float xItem = 0.8F * random.nextFloat() + 0.1F;
					float yItem = 0.8F * random.nextFloat() + 0.1F;
					float zItem = 0.8F * random.nextFloat() + 0.1F;
					
					EntityItem item = new EntityItem(world, x + xItem, y + yItem, z + zItem, furnace.getStackInSlot(i));
					item.motionX = random.nextGaussian() * 0.05;
					item.motionY = random.nextGaussian() * 0.05 + 0.2;
					item.motionZ = random.nextGaussian() * 0.05;
					
					
					world.spawnEntityInWorld(item);
				}
			}
		}
		super.breakBlock(world, x, y, z, block, fortune);
		
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
