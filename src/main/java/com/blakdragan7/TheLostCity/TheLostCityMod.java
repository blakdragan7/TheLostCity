package com.blakdragan7.TheLostCity;

import com.blakdragan7.TheLostCity.items.TLCItemLoader;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = TheLostCityMod.MODID, version = TheLostCityMod.VERSION)
public class TheLostCityMod
{
    public static final String MODID = "tlc";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		TLCItemLoader.LoadItems();
		TLCItemLoader.RegisterItems();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		
    }
    
    
}
