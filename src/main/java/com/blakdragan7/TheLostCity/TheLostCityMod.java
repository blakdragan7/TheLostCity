package com.blakdragan7.TheLostCity;

import com.blakdragan7.TheLostCity.Client.TLCProxyClient;
import com.blakdragan7.TheLostCity.Common.VillageComponentTLCLibrary;
import com.blakdragan7.TheLostCity.Common.VillageHandleTLCLibrary;
import com.blakdragan7.TheLostCity.Common.Block.TLCBlocks;
import com.blakdragan7.TheLostCity.Common.Items.TLCItems;
import com.blakdragan7.TheLostCity.Core.TLCProxyServer;
import com.blakdragan7.TheLostCity.Common.TLCProxy;

import net.minecraft.entity.EnumCreatureType;
import net.minecraft.entity.passive.EntitySquid;
import net.minecraft.init.Biomes;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.registry.EntityRegistry;
import net.minecraftforge.fml.common.registry.VillagerRegistry;

@Mod(modid = TheLostCityMod.MODID,name = TheLostCityMod.MODNAME ,version = TheLostCityMod.VERSION)
public class TheLostCityMod
{
    public static final String MODID = "tlc";
    public static final String VERSION = "0.0.1";
    public static final String MODNAME = "The Lost City";
    
    public static VillageHandleTLCLibrary villageHandle;
    
    @Mod.Instance
    public static TheLostCityMod tlcMod;
    
    @SidedProxy(clientSide = TLCProxyClient.ClientProxyName , serverSide = TLCProxyServer.ServerProxyName)
    public static TLCProxy  sidedProxy;
    
    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event)
    {	
		sidedProxy.preInit(event);
		villageHandle = new VillageHandleTLCLibrary();
    }
    
    @Mod.EventHandler
    public void init(FMLInitializationEvent event)
    {
		MapGenStructureIO.registerStructureComponent(VillageComponentTLCLibrary.class, "tlc:libary"); 
		VillagerRegistry.instance().registerVillageCreationHandler(villageHandle);
		sidedProxy.init(event);
    }
    
    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent event)
    {
		sidedProxy.postInit(event);
		EntityRegistry.removeSpawn(EntitySquid.class, EnumCreatureType.WATER_CREATURE, Biomes.DEEP_OCEAN, Biomes.OCEAN);
		EntityRegistry.addSpawn(EntitySquid.class,10,0,1, EnumCreatureType.WATER_CREATURE, Biomes.DEEP_OCEAN, Biomes.OCEAN);
    }
}
