package com.juicegrape.biodynamics.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.techne.TechneModel;

import org.lwjgl.opengl.GL11;

public class RenderCable extends TileEntitySpecialRenderer {
	
private static final ResourceLocation texture = new ResourceLocation("biodynamics:textures/models/CableCore.png");
	
	public static final ResourceLocation baseLoc = new ResourceLocation("biodynamics:models/CableCore.tcn");
	public static final ResourceLocation extensionLoc = new ResourceLocation("biodynamics:models/CableSide.tcn");
	
	public static TechneModel cableBase;
	public static TechneModel cableExtension;
	
	public RenderCable() {
		cableBase  = new TechneModel(baseLoc);
		cableExtension = new TechneModel(extensionLoc);
	}
	
	

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float tick) {
		
		
		this.bindTexture(texture);
		
		GL11.glPushMatrix();
		
		 GL11.glTranslatef((float) x, (float) y, (float) z);
		 
		 GL11.glPushMatrix();
		
		cableBase.render((Entity) null, 0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F);
		
		GL11.glPopMatrix();
		
		GL11.glPopMatrix();

	}

}
