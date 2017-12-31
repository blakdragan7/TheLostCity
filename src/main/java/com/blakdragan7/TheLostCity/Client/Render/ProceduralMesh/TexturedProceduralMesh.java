package com.blakdragan7.TheLostCity.Client.Render.ProceduralMesh;

import org.lwjgl.opengl.GL11;

import com.blakdragan7.TheLostCity.Client.Render.RenderServers.ProceduralMeshRenderServer;
import com.blakdragan7.TheLostCity.Client.Render.RenderServers.ProceduralMeshRenderServer.IProceduralMeshUser;
import com.blakdragan7.TheLostCity.Client.Render.RenderServers.ProceduralMeshRenderServer.ITexturedMesh;
import com.blakdragan7.TheLostCity.Client.Render.Util.GLRenderUtil.Color;
import com.blakdragan7.TheLostCity.Client.Render.Util.GLRenderUtil.TextureCoords;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.util.ResourceLocation;

public abstract class TexturedProceduralMesh implements ProceduralMeshRenderServer.IProceduralMeshUser,ProceduralMeshRenderServer.ITexturedMesh{
	protected ITextureObject texture;
	protected Color c;
	protected TextureCoords tc;
	
	public TexturedProceduralMesh(ITextureObject texture)
	{
		this.texture = texture;
		c = new Color(1.0F,1.0F,1.0F,1.0F);
		tc = new TextureCoords(0.0F,0.0F,1.0F,1.0F);
	}
	
	@Override
	public ITextureObject bindTexture() {
		// TODO Auto-generated method stub
		GL11.glBindTexture(GL11.GL_TEXTURE_2D, this.texture.getGlTextureId());
		return texture;
	}
	
	@Override
	public Color GetColor()
	{
		return c;
	}
	
	@Override
	public TextureCoords GetTexturesCoords()
	{
		return tc;
	}
}
