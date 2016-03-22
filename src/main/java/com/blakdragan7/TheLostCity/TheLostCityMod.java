package com.blakdragan7.TheLostCity;

import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

@Mod(modid = TheLostCityMod.MODID, version = TheLostCityMod.VERSION)
public class TheLostCityMod
{
    public static final String MODID = "tlc";
    public static final String VERSION = "1.0";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		// some example code
        System.out.println("DIRT BLOCK >> "+Blocks.dirt.getUnlocalizedName());
    }
    
    
}
