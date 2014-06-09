package com.juicegrape.biodynamics.network;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraftforge.event.entity.player.BonemealEvent;

import com.juicegrape.biodynamics.blocks.BlockEnerTreeSapling;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class ModEventHandler {
	
	public static ModEventHandler instance = new ModEventHandler();
	
	public ModEventHandler() {
		
	}
	
	Random random = new Random();
	
	@SubscribeEvent
	public void oneBonemeal(BonemealEvent event) {
		Block target = event.block;
		System.out.println("BANANA");
		if (target != null && target instanceof BlockEnerTreeSapling) {
			target.updateTick(event.world, event.x, event.y, event.z, random);
			System.out.println(event.world.getBlockMetadata(event.x, event.y, event.z));
			event.setResult(Result.ALLOW);
		}
	}

}
