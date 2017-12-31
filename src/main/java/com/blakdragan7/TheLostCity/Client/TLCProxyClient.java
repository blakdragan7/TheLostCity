package com.blakdragan7.TheLostCity.Client;

import com.blakdragan7.TheLostCity.TheLostCityMod;
import com.blakdragan7.TheLostCity.Client.Render.RenderServers.*;
import com.blakdragan7.TheLostCity.Client.Render.Util.GLRenderUtil;
import com.blakdragan7.TheLostCity.Client.Render.Util.TextureUtil;
import com.blakdragan7.TheLostCity.Common.TLCProxy;
import com.blakdragan7.TheLostCity.Common.Block.TLCBlocks;
import com.blakdragan7.TheLostCity.Common.Items.TLCItems;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.item.Item;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.model.obj.OBJLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(Side.CLIENT)
public class TLCProxyClient extends TLCProxy {

	public static final String ClientProxyName = "com.blakdragan7.TheLostCity.Client.TLCProxyClient";
	@Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
    
        OBJLoader.INSTANCE.addDomain(TheLostCityMod.MODID);
        
        MinecraftForge.EVENT_BUS.register(ParticleRenderServer.GetDefaultServer());
        MinecraftForge.EVENT_BUS.register(ProceduralMeshRenderServer.GetDefaultServer());
        
        ((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(TextureUtil.getReloadInstace());
        
        TextureUtil.LoadAllTextures();
	}

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) 
    {
		TLCItems.registerAllRenders();
		TLCBlocks.registerBlockRenders();
	}
}
