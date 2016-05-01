package com.blakdragan7.TheLostCity;

import com.blakdragan7.TheLostCity.blocks.TLCBlockLoader;
import com.blakdragan7.TheLostCity.items.TLCItemLoader;
import com.blakdragan7.TheLostCity.misc.TLCProxy;
import com.blakdragan7.TheLostCity.misc.TLCProxyClient;
import com.blakdragan7.TheLostCity.misc.TLCProxyServer;
import com.blakdragan7.TheLostCity.misc.VillageComponentTLCLibrary;
import com.blakdragan7.TheLostCity.misc.VillageHandleTLCLibrary;

import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

@Mod(modid = TheLostCityMod.MODID, version = TheLostCityMod.VERSION)
public class TheLostCityMod
{
    public static final String MODID = "tlc";
    public static final String VERSION = "1.0";
    
    public static VillageHandleTLCLibrary villageHandle;
    
    @SidedProxy(clientSide = TLCProxyClient.ClientProxyName , serverSide = TLCProxyServer.ServerProxyName)
    public static TLCProxy sidedProxy;
    
    @EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {
		TLCItemLoader.LoadItems();
		TLCItemLoader.RegisterItems();
		
		TLCBlockLoader.loadBlocks();
		TLCBlockLoader.registerBlocks();
		
		villageHandle = new VillageHandleTLCLibrary();
		
		sidedProxy.SidedEvent();
    }
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
		MapGenStructureIO.registerStructureComponent(VillageComponentTLCLibrary.class, "tlc:libary"); 
		VillagerRegistry.instance().registerVillageCreationHandler(villageHandle);
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		
    }
    
    
}
