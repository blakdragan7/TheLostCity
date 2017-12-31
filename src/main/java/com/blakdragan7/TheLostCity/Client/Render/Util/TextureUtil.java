package com.blakdragan7.TheLostCity.Client.Render.Util;

import javax.annotation.Nullable;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;

public class TextureUtil implements IResourceManagerReloadListener{
	public static ITextureObject TestTexture;
	public static ITextureObject AccelerantGlowEffectTexture;
	
	public static boolean hasLoadedAllTextures = false;
	private static TextureUtil reloadInstance = null;
	public static TextureUtil getReloadInstace()
	{
		if(reloadInstance == null)reloadInstance = new TextureUtil();
		return reloadInstance;
	}
	
	@Override
	public void onResourceManagerReload(@Nullable IResourceManager resourceManager)
	{
		 // for future optimization
	}

	public static void LoadAllTextures()
	{
		TestTexture = new SimpleTexture(new ResourceLocation("tlc:textures/items/tlcgatekey.png"));
		AccelerantGlowEffectTexture = new SimpleTexture(new ResourceLocation("tlc:textures/effects/effectmask.png"));
		try {
			TestTexture.loadTexture(Minecraft.getMinecraft().getResourceManager());
			AccelerantGlowEffectTexture.loadTexture(Minecraft.getMinecraft().getResourceManager());
		}
		catch(Exception e)
		{
			
		}
		hasLoadedAllTextures = true;
	}
	
}
