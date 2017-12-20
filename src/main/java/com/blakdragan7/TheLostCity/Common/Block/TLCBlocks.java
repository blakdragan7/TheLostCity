package com.blakdragan7.TheLostCity.Common.Block;

import com.blakdragan7.TheLostCity.TheLostCityMod;
import com.blakdragan7.TheLostCity.Common.Block.Tile.TileEntityCrystal;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TLCBlocks {

	@GameRegistry.ObjectHolder("tlc:libraryshelf")
	public static TLCLibraryShelf libraryShelf;
	
	@GameRegistry.ObjectHolder("tlc:crystalblock")
	public static CrystalBlock crystalBlock;
	
	public static void loadBlocks(RegistryEvent.Register<Block> event)
	{
		libraryShelf = new TLCLibraryShelf();
		crystalBlock = new CrystalBlock();
		
		event.getRegistry().register(libraryShelf);
		event.getRegistry().register(crystalBlock);
		
		GameRegistry.registerTileEntity(TileEntityCrystal.class, TheLostCityMod.MODID + ":crystaltileentity");
	}
	
	public static void loadBlockItems(RegistryEvent.Register<Item> event)
	{	
		event.getRegistry().register(new ItemBlock(libraryShelf).setRegistryName(libraryShelf.getRegistryName()));
		event.getRegistry().register(new ItemBlock(crystalBlock).setRegistryName(crystalBlock.getRegistryName()));
	}
	
	public static void registerBlockRenders()
	{
		registerBlockRender(libraryShelf);
		crystalBlock.InitModel();
	}
	
	public static void registerBlockRender(Block block)
	{
		Item blockItem = Item.getItemFromBlock(block);
		ModelLoader.setCustomModelResourceLocation(blockItem, 0, new ModelResourceLocation(block.getRegistryName(),"inventory"));
	}
}
