package com.blakdragan7.TheLostCity.items;

import net.minecraftforge.fml.common.registry.GameRegistry;

public class TLCItemLoader 
{
	public static TLCStarterBook tlcStarterBook;
	
	public static void LoadItems() 
	{
		tlcStarterBook = new TLCStarterBook("The Lost City - Volume 1");
	}
	
	public static void RegisterItems() 
	{
		GameRegistry.registerItem(tlcStarterBook, "The Lost City - Volume 1");
	}
}
