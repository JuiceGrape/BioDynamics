package com.juicegrape.biodynamics.villagers;

import java.util.List;
import java.util.Random;

import com.juicegrape.biodynamics.blocks.ModBlocks;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;
import net.minecraft.world.gen.structure.StructureVillagePieces.Start;

public class ComponentHut extends StructureVillagePieces.House1 {
	
	public int averageGroundLevel = -1;
	
	public ComponentHut() {
		
	}
	
	public ComponentHut(Start villagePiece, int something, Random random, StructureBoundingBox boundingBox, int somethingElse) {
		super();
		this.coordBaseMode = somethingElse;
		this.boundingBox = boundingBox;
	}
	
	public static ComponentHut buildComponent(Start villagePiece, List pieces, Random random, int p1, int p2, int p3, int p4, int p5) {
		int xSize = 6;
		int ySize = 6;
		int zSize = 6;
		StructureBoundingBox boundBox = StructureBoundingBox.getComponentToAddBoundingBox(p1, p2, p3, 0, 0, 0, xSize, ySize, zSize, p4);
		return canVillageGoDeeper(boundBox) && StructureComponent.findIntersecting(pieces, boundBox) == null ? new ComponentHut(villagePiece, p5, random, boundBox, p4) : null;
	}
	
	@Override
	public boolean addComponentParts(World world, Random random, StructureBoundingBox box) {
		
		if (this.averageGroundLevel < 0) {
			this.averageGroundLevel = this.getAverageGroundLevel(world, box);
			
			if (this.averageGroundLevel < 0) {
				return true;
			}
			
			this.boundingBox.offset(0, this.averageGroundLevel - this.boundingBox.maxY + 4, 0);
		}
		
		/**
         * arguments: (World worldObj, StructureBoundingBox structBB, int minX, int minY, int minZ, int maxX, int maxY, int
         * maxZ, int placeBlockId, int replaceBlockId, boolean alwaysreplace)
         */
		
		//Base
		this.fillWithBlocks(world, box, 2, 0, 1, 4, 0, 3, ModBlocks.enertreePlanks, ModBlocks.enertreePlanks, false);
		this.fillWithBlocks(world, box, 3, 0, 0, 3, 0, 0, ModBlocks.enertreePlanks, ModBlocks.enertreePlanks, false);
		
		//bottom left pillar
		this.fillWithBlocks(world, box, 1, 1, 0, 1, 4, 0, ModBlocks.enertreeLogs, ModBlocks.enertreeLogs, false);
		
		//upper left pillar
		this.fillWithBlocks(world, box, 1, 1, 4, 1, 4, 4, ModBlocks.enertreeLogs, ModBlocks.enertreeLogs, false);
		
		//bottom right pillar
		this.fillWithBlocks(world, box, 5, 1, 0, 5, 4, 0, ModBlocks.enertreeLogs, ModBlocks.enertreeLogs, false);
		
		//upper right pillar
		this.fillWithBlocks(world, box, 5, 1, 4, 5, 4, 4, ModBlocks.enertreeLogs, ModBlocks.enertreeLogs, false);
		

		//Walls
		this.fillWithBlocks(world, box, 2, 1, 0, 4, 4, 0, ModBlocks.enertreePlanks, ModBlocks.enertreePlanks, false);
		this.fillWithBlocks(world, box, 1, 1, 1, 1, 4, 3, ModBlocks.enertreePlanks, ModBlocks.enertreePlanks, false);
		this.fillWithBlocks(world, box, 2, 1, 4, 4, 4, 4, ModBlocks.enertreePlanks, ModBlocks.enertreePlanks, false);
		this.fillWithBlocks(world, box, 5, 1, 1, 5, 4, 3, ModBlocks.enertreePlanks, ModBlocks.enertreePlanks, false);
		
		//Windows
		this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 1, 2, 2, box);
		this.placeBlockAtCurrentPosition(world, Blocks.glass_pane, 0, 5, 2, 2, box);
		
		//Torches
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 2, 3, 2, box);
		this.placeBlockAtCurrentPosition(world, Blocks.torch, 0, 4, 3, 2, box);
		
		//Door
		this.placeDoorAtCurrentPosition(world, box, random, 3, 1, 0, this.getMetadataWithOffset(Blocks.wooden_door, 1));
		
		
		
		//Roof
		this.fillWithBlocks(world, box, 2, 4, 1, 4, 4, 3, ModBlocks.enertreePlanks, ModBlocks.enertreePlanks, false);

		
		//Villagers
		this.spawnVillagers(world, box, 3, 1, 3, 1);
		
		return true;
		
	}
	
	@Override
	public int getVillagerType(int spawnedVillagers) {
		return VillagerHandler.VILLAGER_ID;
	}

}
