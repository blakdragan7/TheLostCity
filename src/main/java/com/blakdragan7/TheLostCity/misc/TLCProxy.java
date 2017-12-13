package com.blakdragan7.TheLostCity.misc;

import com.blakdragan7.TheLostCity.blocks.TLCBlocks;
import com.blakdragan7.TheLostCity.items.TLCItems;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

@Mod.EventBusSubscriber
public class TLCProxy  
{
	public static final String ProxyName = "com.blakdragan7.TheLostCity.misc.TLCProxy";
	
	public void preInit(FMLPreInitializationEvent e) 
	{
		
	}
	public void init(FMLInitializationEvent e) 
	{
		
	}
	public void postInit(FMLPostInitializationEvent e) 
	{
		
	}
	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) 
	{
		TLCBlocks.loadBlocks(event);
	}
	@SubscribeEvent
	public static void registerItems(RegistryEvent.Register<Item> event) 
	{
		 TLCItems.LoadItems(event);
		 TLCBlocks.loadBlockItems(event);
	}
}
