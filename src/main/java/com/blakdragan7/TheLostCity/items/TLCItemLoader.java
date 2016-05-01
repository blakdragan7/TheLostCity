package com.blakdragan7.TheLostCity.items;

import com.blakdragan7.TheLostCity.TheLostCityMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TLCItemLoader 
{
	public static TLCStarterBook tlcStarterBook;
	
	public static void LoadItems() 
	{
		tlcStarterBook = new TLCStarterBook("starter_book");
	}
	
	public static void RegisterItems() 
	{
		GameRegistry.registerItem(tlcStarterBook, tlcStarterBook.getUnlocalizedName().substring(5));
	}
	
	public static void registerAllRenders()
	{
		registerItemRender(tlcStarterBook);
	}
	
	public static void registerItemRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(TheLostCityMod.MODID + ':' + item.getUnlocalizedName().substring(5),"inventory"));
	}
}
