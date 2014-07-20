package com.juicegrape.biodynamics.network;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.world.BlockEvent.HarvestDropsEvent;

import com.juicegrape.biodynamics.items.ModItems;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class CommonEventHandler {
	
	private Random random = new Random();
	
	public static CommonEventHandler INSTANCE = new CommonEventHandler();
	
	
	@SubscribeEvent
	public void onDiamondDrop(HarvestDropsEvent event) {
		
		if (event.isSilkTouching || !event.block.equals(Blocks.diamond_ore))
			return;
		
		for (int i = 0; i <= event.fortuneLevel; i++) {
			if (random.nextInt(100) == 1) {
				event.drops.add(new ItemStack(ModItems.craftingItem));
			}
		}
	}

}
