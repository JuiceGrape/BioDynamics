package com.juicegrape.biodynamics.network;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraftforge.event.entity.player.BonemealEvent;

import com.juicegrape.biodynamics.blocks.BlockEnerTreeSapling;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

/**
 * 
 * @author JuiceGrape
 *
 */
public class ModEventHandler {
	
	public static ModEventHandler INSTANCE = new ModEventHandler();
	
	public ModEventHandler() {
		
	}
	
	private static Random random = new Random();
	
	@SubscribeEvent
	public void oneBonemeal(BonemealEvent event) {
		Block target = event.block;
		if (target != null && target instanceof BlockEnerTreeSapling) {
			BlockEnerTreeSapling sapling = (BlockEnerTreeSapling)target;
			if (sapling.boneMeal(event.world, event.x, event.y, event.z, random)) {
				event.setResult(Result.ALLOW);
			} else {
				event.setResult(Result.DENY);
			}
		}
	}

}
