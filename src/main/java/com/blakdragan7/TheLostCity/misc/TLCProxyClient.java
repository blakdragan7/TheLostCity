package com.blakdragan7.TheLostCity.misc;

import com.blakdragan7.TheLostCity.TheLostCityMod;
import com.blakdragan7.TheLostCity.blocks.TLCBlocks;
import com.blakdragan7.TheLostCity.items.TLCItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class TLCProxyClient extends TLCProxy {

	public static final String ClientProxyName = "com.blakdragan7.TheLostCity.misc.TLCProxyClient";
	@Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) 
    {
		TLCItems.registerAllRenders();
		TLCBlocks.registerBlockRenders();
	}
}
