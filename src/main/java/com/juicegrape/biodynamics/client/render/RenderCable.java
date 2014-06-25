package com.juicegrape.biodynamics.client.render;

import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.client.model.techne.TechneModel;
import net.minecraftforge.common.util.ForgeDirection;

import org.lwjgl.opengl.GL11;

import cofh.api.energy.IEnergyConnection;

public class RenderCable extends TileEntitySpecialRenderer {
	
private static final ResourceLocation texture = new ResourceLocation("biodynamics:textures/models/cable.png");
	
	public static final ResourceLocation modelLoc = new ResourceLocation("biodynamics:models/cable.tcn");
	
	public final TechneModel cable;
	
	public RenderCable() {
		cable = new TechneModel(modelLoc);
	}
	
	

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float tick) {
		
		
		this.bindTexture(texture);
		
		GL11.glPushMatrix();
		
		 GL11.glTranslatef((float) x + 0.5F, (float) y, (float) z + 0.5F);
		 
		 GL11.glPushMatrix();
		 
		 float scaler = 1F / 16F;
		 
		 GL11.glScalef(scaler, scaler, scaler);
		 GL11.glRotatef(180F, 0.0F, 0.0F, 1.0F);

		
		renderParts(te.getWorldObj(), te.xCoord, te.yCoord, te.zCoord);
				
		cable.renderPart("Core");
		
		GL11.glPopMatrix();
		
		GL11.glPopMatrix();

	}
	
	private void renderParts(World world, int x, int y, int z) {
		if (world == null) {
			float scaler = 2.0F;
			GL11.glScalef(scaler, scaler, scaler);
			GL11.glTranslatef(0.0F, scaler * 2, 0.0F);
			return;
		}
		for (ForgeDirection dir : ForgeDirection.VALID_DIRECTIONS) {
			TileEntity te = world.getTileEntity(x + dir.offsetX, y + dir.offsetY, z + dir.offsetZ);
			if (te != null && te instanceof IEnergyConnection) {
				IEnergyConnection tei = (IEnergyConnection)te;
				if (tei.canConnectEnergy(dir.getOpposite())) {
					cable.renderPart(getNameFromDirection(dir));
				}
			}
		}
	}
	
	private String getNameFromDirection(ForgeDirection dir) {
		switch (dir) {
		case UP:
			return "Up";
		case DOWN:
			return "Down";
		case SOUTH:
			return "Back";
		case WEST:
			return "Right";
		case EAST:
			return "Left";
		case NORTH:
			return "Front";
		default:
			return "ERROR";
		}
	}

}
