package com.juicegrape.biodynamics.blocks.common;

import net.minecraft.block.material.Material;
import net.minecraft.init.Items;
import net.minecraft.item.ItemBucket;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.BlockFluidClassic;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;

import com.juicegrape.biodynamics.biodynamics;
import com.juicegrape.biodynamics.items.ItemSpecialBonemeals;
import com.juicegrape.biodynamics.items.ModItems;
import com.juicegrape.biodynamics.items.common.BioBucket;
import com.juicegrape.biodynamics.network.BucketHandler;

import cpw.mods.fml.common.registry.GameRegistry;

public class BioLiquid extends BlockFluidClassic {

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

}
