package com.blakdragan7.TheLostCity.blocks;

import com.blakdragan7.TheLostCity.TheLostCityMod;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TLCBlockLoader {

	public static TLCLibraryShelf libraryShelf;
	
	public static void loadBlocks()
	{
		libraryShelf = new TLCLibraryShelf("LibraryShelf");
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(libraryShelf, "LibraryShelf");
	}
	
	public static void registerBlockRenders()
	{
		registerBlockRender(libraryShelf);
	}
	
	public static void registerBlockRender(Block block)
	{
		Item blockItem = Item.getItemFromBlock(block);
		ModelLoader.setCustomModelResourceLocation(blockItem, 0, new ModelResourceLocation(TheLostCityMod.MODID + ':' + blockItem.getUnlocalizedName().substring(5),"inventory"));
	}
}
