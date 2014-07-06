package com.juicegrape.biodynamics.client.render;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.entity.RenderItem;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

import com.juicegrape.biodynamics.tileentity.generators.TileEntityBurningFlower;

public class RenderBurningFlower extends TileEntitySpecialRenderer {
	
	private final RenderItem customRenderItem;

	
	public RenderBurningFlower() {
		customRenderItem = new RenderItem() {
        	
        	@Override
        	public boolean shouldSpreadItems() {
        		return false;
        	}
		};
		
		customRenderItem.setRenderManager(RenderManager.instance);
	}

	@Override
	public void renderTileEntityAt(TileEntity te, double x, double y, double z, float tick) {
		
		
		if (te.getWorldObj() == null) {
			return;
		}
		
		TileEntityBurningFlower flower = (TileEntityBurningFlower)te.getWorldObj().getTileEntity(te.xCoord, te.yCoord, te.zCoord);
		
		if (flower == null) {
			return;
		}
		
		if (flower.getStackInSlot(0) == null) {
			return;
		}
		
		ItemStack torender = flower.getStackInSlot(0).copy();
		
		float rotationAngle = (float) (720.0 * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL);
    	float bob = MathHelper.sin(45.0F * (System.currentTimeMillis() & 0x3FFFL) / 0x3FFFL) / 10;
    	
    	GL11.glPushMatrix();
    	GL11.glDisable(2896);
    	
    	EntityItem ghostEntityItem = new EntityItem(flower.getWorldObj());
    	ghostEntityItem.hoverStart = 0.0F;
    	ghostEntityItem.setEntityItemStack(torender);
    	
    	GL11.glTranslatef((float) x, (float) y, (float) z);
    	
    	GL11.glTranslatef(0.5F, 0.5F + bob , 0.5F);
    	GL11.glColor3f(1F, 1F, 1F);
    	
    	if (Minecraft.isFancyGraphicsEnabled()) {
			GL11.glRotatef(rotationAngle, 0.0F, 1.0F, 0.0F);
		}
    	
    	
    	
    	if (torender.getItem() instanceof ItemBlock) {
			ItemBlock testItem = (ItemBlock)torender.getItem();
			Block testBlock = testItem.field_150939_a;
			if (RenderBlocks.renderItemIn3d(testBlock.getRenderType())) {
				GL11.glScalef(1.2F, 1.2F, 1.2F);
			}
		}
    	
    	customRenderItem.doRender(ghostEntityItem, 0, 0, 0, 0, 0);
    	
    	GL11.glEnable(2896);
	    GL11.glPopMatrix();
    	
    	
    	
    	
		
		
	}

}
