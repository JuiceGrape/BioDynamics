package com.juicegrape.biodynamics.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;

import com.juicegrape.biodynamics.blocks.BlockInfo;
import com.juicegrape.biodynamics.client.guis.GuiInfo;
import com.juicegrape.biodynamics.tileentity.ContainerEnerTreeFurnace;
import com.juicegrape.biodynamics.tileentity.TileEntityBatteTreeCore;
import com.juicegrape.biodynamics.tileentity.TileEntityCable;
import com.juicegrape.biodynamics.tileentity.TileEntityEnerTreeFurnace;
import com.juicegrape.biodynamics.tileentity.TileEntitySoil;
import com.juicegrape.biodynamics.tileentity.generators.TileEntityBurningFlower;
import com.juicegrape.biodynamics.tileentity.generators.TileEntitySolarFlower;

import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.registry.GameRegistry;

/**
 * 
 * @author JuiceGrape
 *
 */
public class CommonProxy implements IGuiHandler {

	public boolean isClient() {
		return false;
	}
	
	public void registerTileEntities() {
		GameRegistry.registerTileEntity(TileEntityEnerTreeFurnace.class, BlockInfo.ENERTREEFURNACE);
		GameRegistry.registerTileEntity(TileEntityBatteTreeCore.class, BlockInfo.BATTETREECORE);
		GameRegistry.registerTileEntity(TileEntitySoil.class, BlockInfo.ENERGETICSOIL);
		GameRegistry.registerTileEntity(TileEntitySolarFlower.class, BlockInfo.SOLARFLOWER);
		GameRegistry.registerTileEntity(TileEntityCable.class, BlockInfo.CABLE);
		GameRegistry.registerTileEntity(TileEntityBurningFlower.class, BlockInfo.BURNINGFLOWER);
	}

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case GuiInfo.GUI_ENERTREEFURNACE_ID:
			return new ContainerEnerTreeFurnace(player.inventory ,(TileEntityEnerTreeFurnace)world.getTileEntity(x, y, z));
		default:
			return null;
		}
	}
	
	public void initRenderers() {
		
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}
	
	public int addArmor(String armor) {
		return 0;
	}

	

}
