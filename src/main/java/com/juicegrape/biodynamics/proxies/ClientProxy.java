package com.juicegrape.biodynamics.proxies;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import net.minecraftforge.client.MinecraftForgeClient;

import com.juicegrape.biodynamics.blocks.ModBlocks;
import com.juicegrape.biodynamics.client.guis.GuiEnerTreeFurnace;
import com.juicegrape.biodynamics.client.guis.GuiInfo;
import com.juicegrape.biodynamics.client.render.ItemTERenderer;
import com.juicegrape.biodynamics.client.render.RenderBurningFlower;
import com.juicegrape.biodynamics.client.render.RenderCable;
import com.juicegrape.biodynamics.tileentity.TileEntityCable;
import com.juicegrape.biodynamics.tileentity.TileEntityEnerTreeFurnace;
import com.juicegrape.biodynamics.tileentity.generators.TileEntityBurningFlower;

import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;

/**
 * 
 * @author JuiceGrape
 *
 */
public class ClientProxy extends CommonProxy {

	@Override
	public boolean isClient() {
		return true;
	}
	
	@Override
	public void registerTileEntities() {
		super.registerTileEntities();;
	}
	
	@Override
	public void initRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCable.class, new RenderCable());
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityBurningFlower.class, new RenderBurningFlower());
		MinecraftForgeClient.registerItemRenderer(Item.getItemFromBlock(ModBlocks.cableblock), new ItemTERenderer(new TileEntityCable(0)));
	}
	
	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		switch(ID) {
		case GuiInfo.GUI_ENERTREEFURNACE_ID:
			return new GuiEnerTreeFurnace(player.inventory ,(TileEntityEnerTreeFurnace)world.getTileEntity(x, y, z));
		default:
			return null;
		}
	}
	
	@Override
	public int addArmor(String armor) {
		return RenderingRegistry.addNewArmourRendererPrefix(armor);
	}
	

}
