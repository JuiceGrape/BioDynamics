package com.juicegrape.biodynamics.client.guis;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.LanguageManager;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.util.ResourceLocation;

import com.juicegrape.biodynamics.tileentity.ContainerEnerTreeFurnace;
import com.juicegrape.biodynamics.tileentity.TileEntityEnerTreeFurnace;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.registry.LanguageRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

/**
 * 
 * @author JuiceGrape
 *
 */
public class GuiEnerTreeFurnace extends GuiContainer {
	
	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation(GuiInfo.RESOURCELOCATIONGUIS + GuiInfo.GUI_TEXTURE_ENERTREEFURNACE);
	TileEntityEnerTreeFurnace furnace;

	public GuiEnerTreeFurnace(InventoryPlayer player, TileEntityEnerTreeFurnace furnace) {
		super(new ContainerEnerTreeFurnace(player, furnace));
		this.furnace = furnace;
	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String drawString;
		if (!furnace.isInvNameLocalized()) {
			//if (FMLClientHandler.instance().getCurrentLanguage() != null) {
			//	drawString = LanguageRegistry.instance().getStringLocalization(furnace.getInventoryName(), FMLClientHandler.instance().getCurrentLanguage());
			//} else {
				drawString = LanguageRegistry.instance().getStringLocalization(furnace.getInventoryName(), "en_US");
		//	}
		} else {
			drawString = furnace.getInventoryName();
		}
		this.fontRendererObj.drawString(drawString, this.xSize / 2 - this.fontRendererObj.getStringWidth(drawString) / 2, 6, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int var2, int var3) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		 this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
	        int k = (this.width - this.xSize) / 2;
	        int l = (this.height - this.ySize) / 2;
	        this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
	        int i1;

	        if (this.furnace.isBurning())
	        {
	            i1 = this.furnace.getBurnTimeRemainingScaled(12);
	            this.drawTexturedModalRect(k + 56, l + 36 + 12 - i1, 176, 12 - i1, 14, i1 + 2);
	        }

	        i1 = this.furnace.getCookProgressScaled(24);
	        this.drawTexturedModalRect(k + 79, l + 34, 176, 14, i1 + 1, 16);
	}

}
