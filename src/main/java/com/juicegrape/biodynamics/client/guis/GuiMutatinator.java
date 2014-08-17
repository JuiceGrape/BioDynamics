package com.juicegrape.biodynamics.client.guis;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.RenderBlockFluid;

import org.lwjgl.opengl.GL11;

import com.juicegrape.biodynamics.blocks.ModBlocks;
import com.juicegrape.biodynamics.tileentity.TileEntityMutatinator;
import com.juicegrape.biodynamics.tileentity.containers.ContainerMutatinator;

import cpw.mods.fml.common.registry.LanguageRegistry;

public class GuiMutatinator extends GuiContainer {
	
	protected final int
	heatX = 176,
	heatWidth = 18,
	heatHeight = 31,
	tankX = 194,
	tankWidth = 10,
	tankHeight = 25,
	powerX = 204,
	powerWidth = 12,
	powerHeight = 51,
	arrowX = 216,
	arrowWidth = 13,
	arrowHeight = 19,
	fireX = 229,
	fireWidth = 14,
	fireHeight = 14;
	
	protected final int
	lavaTankX = 11,
	lavaTankY = 71,
	rwTankX = 32,
	rwTankY = 71,
	emptyHeatX = 7,
	emptyHeatY = 18,
	emptyArrowX = 81,
	emptyArrowY = 86,
	emptyPowerX = 134,
	emptyPowerY = 73,
	emptyFireX = 152,
	emptyFireY = 91;
	
	
	
	
	
	
	private static final ResourceLocation mutatinatorGuiTexture = new ResourceLocation(GuiInfo.RESOURCELOCATIONGUIS + GuiInfo.GUI_TEXTURE_MUTATINATOR);
	
	
	TileEntityMutatinator mutatinator;

	public GuiMutatinator(InventoryPlayer player, TileEntityMutatinator mutate) {
		super(new ContainerMutatinator(player, mutate));
		mutatinator = mutate;
		this.xSize = 176;
		this.ySize = 210;
		this.width = 256;
		this.height = 256;

	}
	
	@Override
	protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
		String drawString = mutatinator.getInventoryName();
		this.fontRendererObj.drawString(drawString, this.xSize / 2 - this.fontRendererObj.getStringWidth(drawString) / 2, 6, 4210752);
		
		
		
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float var1, int mouseX, int mouseY) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(mutatinatorGuiTexture);
		int x = (this.width - this.xSize) / 2;
		int y = (this.height - this.ySize) / 2;
		this.drawTexturedModalRect(x, y, 0, 0, this.xSize, this.ySize);
		
		int ha = (int) (heatHeight * (mutatinator.getScaledHeatStored() / 100.0F));
		int pa = (int) (powerHeight * (mutatinator.getScaledEnergyStored() / 100.0F));
		int fa = (int) (fireHeight * (mutatinator.getBurntimeScaled() / 100.0F));
		int la = (int) (tankHeight * (mutatinator.getLavaScaled() / 100.0F));
		int ra = (int) (tankHeight * (mutatinator.getRWScaled() / 100.0F));
		int aa = (int) (arrowHeight * (mutatinator.getWorkingTimeScaled() / 100.0F));
		
		this.drawTexturedModalRect(x + emptyHeatX, y + emptyHeatY + heatHeight - ha, heatX, heatHeight - ha, heatWidth, ha);
		this.drawTexturedModalRect(x + emptyPowerX, y + emptyPowerY + powerHeight - pa, powerX, powerHeight - pa, powerWidth,pa);
		this.drawTexturedModalRect(x + emptyArrowX, y + emptyArrowY, arrowX, 0, arrowWidth, aa);
		this.drawTexturedModalRect(x + emptyFireX, y + emptyFireY + fireHeight - fa, fireX, fireHeight - fa, fireWidth, fa);
		
		//TODO: add alpha levels to the fluids being rendered?
		
		this.mc.getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
		this.drawTexturedModelRectFromIcon(x + lavaTankX, y + lavaTankY + tankHeight - la, Blocks.lava.getIcon(0, 0), tankWidth, la);
		this.drawTexturedModelRectFromIcon(x + rwTankX, y + rwTankY + tankHeight - ra, ModBlocks.redstoneWater.getIcon(0, 0), tankWidth, ra);
		
		this.mc.getTextureManager().bindTexture(mutatinatorGuiTexture);
		this.drawTexturedModalRect(x + lavaTankX, y + lavaTankY, tankX, 0, tankWidth, tankHeight);
		this.drawTexturedModalRect(x + rwTankX, y + rwTankY, tankX, 0, tankWidth, tankHeight);
		
		
	}

}
