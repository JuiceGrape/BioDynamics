package com.juicegrape.biodynamics.config;

import java.io.File;

import com.juicegrape.biodynamics.villagers.VillagerHandler;

import net.minecraftforge.common.config.Configuration;

/**
 * 
 * @author JuiceGrape
 *
 */
public class ConfigHandler {

	public static void init(File file) {
		Configuration config = new Configuration(file);

		config.load();
		
		VillagerHandler.VILLAGER_ID = config.get("Misc", "ID for the custom villager", VillagerHandler.VILLAGER_ID_DEF).getInt();

		config.save();
	}

}
