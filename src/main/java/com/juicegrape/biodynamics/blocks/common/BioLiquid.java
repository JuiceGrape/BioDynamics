package com.juicegrape.biodynamics.blocks.common;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;

import com.juicegrape.biodynamics.items.ModItems;
import com.juicegrape.biodynamics.items.common.BioBucket;
import com.juicegrape.biodynamics.network.BucketHandler;

import cpw.mods.fml.common.registry.GameRegistry;


/**
 * 
 * @author JuiceGrape
 *
 */
public class BioLiquid extends BlockFluidClassic {

	/**
	 * Set's a liquid to a block, registers a bucket for it and gives that bucket the correct fluid container.
	 * 
	 * @param fluid the fluid to register to this liquid block.
	 * @param material material of the liquid.
	 * @param name the unlocalized name of the liquid. The unlocalized name of the bucket will be this + _bucket.
	 */
	public BioLiquid(Fluid fluid, Material material, String name) {
		super(fluid, material);
		setBlockName(name);
		
		BioBucket bucket = new BioBucket(this, name + "_bucket");
		bucket.setContainerItem(Items.bucket);
		GameRegistry.registerItem(bucket, name + "_bucket");
		FluidContainerRegistry.registerFluidContainer(fluid, new ItemStack(bucket), new ItemStack(Items.bucket));
		BucketHandler.INSTANCE.buckets.put(this, bucket);
		ModItems.buckets.add(bucket);
		
	}
	
	@Override
	public boolean canDisplace(IBlockAccess world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid())
			return false;
		return super.canDisplace(world, x, y, z);
	}
	
	@Override
	public boolean displaceIfPossible(World world, int x, int y, int z) {
		if (world.getBlock(x, y, z).getMaterial().isLiquid())
			return false;
		return super.displaceIfPossible(world, x, y, z);
	}

}
