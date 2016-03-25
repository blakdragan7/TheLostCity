package com.blakdragan7.TheLostCity.blocks;

import com.blakdragan7.TheLostCity.TheLostCityMod;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TLCBlockLoader {

	public static TLCLibrarySideBlock libraryBlock;
	
	public static void loadBlocks()
	{
		libraryBlock = new TLCLibrarySideBlock("LibrarySide");
	}
	
	public static void registerBlocks()
	{
		GameRegistry.registerBlock(libraryBlock, "LibrarySide");
	}
	
	public static void registerBlockRenders()
	{
		registerBlockRender(libraryBlock);
	}
	
	public static void registerBlockRender(Block block)
	{
		Item blockItem = Item.getItemFromBlock(block);
		Minecraft.getMinecraft().getRenderItem().getItemModelMesher()
	    .register(blockItem, 0, new ModelResourceLocation(TheLostCityMod.MODID + ':' + block.getUnlocalizedName().substring(5), "inventory"));
	}
}
