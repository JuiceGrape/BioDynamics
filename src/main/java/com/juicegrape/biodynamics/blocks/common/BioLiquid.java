package com.juicegrape.biodynamics.blocks.common;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.network.BucketHandler;

import cpw.mods.fml.common.registry.GameRegistry;

public class BioLiquid extends BlockFluidClassic {

	public BioLiquid(Fluid fluid, Material material, String name) {
		super(fluid, material);
		setBlockName(name);
		
		ItemBucket yourBucket = new ItemBucket(this);
		yourBucket.setUnlocalizedName(name + "_bucket").setContainerItem(Items.bucket);
		GameRegistry.registerItem(yourBucket, name + "_bucket");
		yourBucket.setCreativeTab(biodynamics.bioTab);
		FluidContainerRegistry.registerFluidContainer(fluid, new ItemStack(yourBucket), new ItemStack(Items.bucket));
		BucketHandler.INSTANCE.buckets.put(this, yourBucket);
	}

}
