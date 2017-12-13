package com.blakdragan7.TheLostCity.blocks;

import com.blakdragan7.TheLostCity.TheLostCityMod;

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
	
	public static void loadBlocks(RegistryEvent.Register<Block> event)
	{
		libraryShelf = new TLCLibraryShelf();
		
		event.getRegistry().register(libraryShelf);
	}
	
	public static void loadBlockItems(RegistryEvent.Register<Item> event)
	{	
		event.getRegistry().register(new ItemBlock(libraryShelf).setRegistryName(libraryShelf.getRegistryName()));
	}
	
	public static void registerBlockRenders()
	{
		registerBlockRender(libraryShelf);
	}
	
	public static void registerBlockRender(Block block)
	{
		Item blockItem = Item.getItemFromBlock(block);
		ModelLoader.setCustomModelResourceLocation(blockItem, 0, new ModelResourceLocation(block.getRegistryName(),"inventory"));
	}
}
