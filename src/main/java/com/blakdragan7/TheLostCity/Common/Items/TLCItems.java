package com.blakdragan7.TheLostCity.Common.Items;

import com.blakdragan7.TheLostCity.TheLostCityMod;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

public class TLCItems
{
	@GameRegistry.ObjectHolder("tlc:starter_book")
	public static TLCStarterBook tlcStarterBook;
	@GameRegistry.ObjectHolder("tlc:_GateKey_necklace")
	public static TLCGateKey tlcGateKey;
	
	public static void LoadItems(RegistryEvent.Register<Item> event) 
	{
		tlcStarterBook = new TLCStarterBook("starter_book");
		tlcGateKey = new TLCGateKey();
		
		event.getRegistry().register(tlcStarterBook);
		event.getRegistry().register(tlcGateKey);
	}
	
	public static void registerAllRenders()
	{
		registerItemRender(tlcStarterBook);
		registerItemRender(tlcGateKey);
	}
	
	public static void registerItemRender(Item item)
	{
		ModelLoader.setCustomModelResourceLocation(item, 0, new ModelResourceLocation(item.getRegistryName(),"inventory"));
	}
}
