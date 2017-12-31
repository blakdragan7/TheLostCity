package com.blakdragan7.TheLostCity.Client.Render.RenderServers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.lwjgl.opengl.GL11;

import com.blakdragan7.TheLostCity.Client.Render.Util.GLRenderUtil;
import com.blakdragan7.TheLostCity.Client.Render.Util.GLRenderUtil.Color;
import com.blakdragan7.TheLostCity.Client.Render.Util.GLRenderUtil.TextureCoords;
import com.blakdragan7.TheLostCity.Client.Render.Util.TextureUtil;
import com.blakdragan7.TheLostCity.Common.Block.TLCBlocks;
import com.blakdragan7.TheLostCity.Common.Math.Util.MathUtil;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class ProceduralMeshRenderServer{
	
	private long lastUpdate = -1;
	
	private Object tickLock;
	private Object renderLock;
	
	private Map<BlockPos,IProceduralMeshUser> muMap;
	private List<ITickableMesh> tmList;
	
	private static ProceduralMeshRenderServer instance = null;
	
	public ProceduralMeshRenderServer()
	{
		muMap = new HashMap<BlockPos,IProceduralMeshUser>();
		tmList = new LinkedList();
		tickLock = new Object();
		renderLock = new Object();
	}
	
	public static ProceduralMeshRenderServer GetDefaultServer()
	{
		if(instance==null)instance = new ProceduralMeshRenderServer();
		return instance;
	}
	
	public ProceduralMeshRenderServer RegisterUser(BlockPos pos,IProceduralMeshUser mu)
	{
		synchronized(renderLock)
		{
			if(muMap.containsKey(pos) == false)
			{
				muMap.put(pos,mu);
				if(mu instanceof ITickableMesh)
				{
					tmList.add((ITickableMesh)mu);
				}
			}
		}
		if(mu instanceof ITickableMesh)
		{
			synchronized(tickLock)
			{
				ITickableMesh tm = (ITickableMesh)mu;
				if(tmList.contains(tm))
				tmList.add(tm);
				
			}
		}
		return this;
	}
	
	public ProceduralMeshRenderServer UnregisterUser(BlockPos pos)
	{
		if(muMap.containsKey(pos) == true)
		{
			IProceduralMeshUser mu = muMap.get(pos);
			synchronized(renderLock)
			{
				muMap.remove(pos);	
			}
			if(mu instanceof ITickableMesh)
			{
				synchronized(tickLock)
				{
					ITickableMesh tm = (ITickableMesh)mu;
					if(tmList.contains(tm))
					tmList.remove(tm);
				}
			}
	}
		return this;
	}
	
	private void renderTriangles(IProceduralMeshUser mu,float partialTicks)
	{
		// does nothing yet
	}
	
	private void renderQuads(IProceduralMeshUser mu,float partialTicks)
	{
		List<Vec3d> CenterPoints = mu.GetCenterPoints();

        Iterator<Vec3d> iter = CenterPoints.iterator();
        
        Tessellator tes = Tessellator.getInstance();
        BufferBuilder buf = tes.getBuffer();
        ITexturedMesh tm = null;
        Color c = null;
        TextureCoords tc = null;
        boolean isTM = false;
        		
        if(mu instanceof ITexturedMesh)
        {
        	tm = (ITexturedMesh)mu;
        	c = tm.GetColor();
        	tc = tm.GetTexturesCoords();
        	tm.bindTexture();
        	buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
        	isTM = true;
        }
        else
        {
        	GL11.glDisable(GL11.GL_TEXTURE_2D);
        	buf.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_COLOR);
        }
        
        while(iter.hasNext())
        {
        	Vec3d currentPos = iter.next();
        	
        	if(iter.hasNext())
        	{
        		Vec3d nextPos = iter.next();
        		
        		Vec3d customSize = mu.GetCustomSize();
        		
        		double sizex = customSize.x == -1.0 ? (nextPos.x - currentPos.x): customSize.x;
        		double sizey = customSize.y == -1.0 ? (nextPos.y - currentPos.y): customSize.y;
        		double sizez = customSize.z == -1.0 ? (nextPos.z - currentPos.z): customSize.z;
        	
        		if(isTM)
        		{
        			GLRenderUtil.GetDefaultRenderer().renderVariableSizeTextureSquare(currentPos, c, tc, buf, 
        											sizex == 0 ? sizez : sizex, sizey, 
        											MathUtil.GetPerpFacingFromVecToVec(currentPos, nextPos), partialTicks);
        		}
        		else
        		{
	        		GLRenderUtil.GetDefaultRenderer().renderVariableSizeSquare(currentPos, buf, sizex == 0 ? 
	        											sizez : sizex, sizey,
	        											MathUtil.GetPerpFacingFromVecToVec(currentPos, nextPos),
	        											partialTicks);
        		}
        	}
    	
        }
        
	        tes.draw();
	}
	
	private void renderQuadStrips(IProceduralMeshUser mu,float partialTicks)
	{
		// does nothing yet
	}

	@SubscribeEvent(priority = EventPriority.LOW)
	public void onRender(RenderWorldLastEvent event)
	{
		if(TextureUtil.hasLoadedAllTextures == false)return;
		
		synchronized(renderLock)
		{
			float partialTicks = event.getPartialTicks();
			
			GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
	        GL11.glEnable(GL11.GL_BLEND);
	        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
	        GlStateManager.alphaFunc(GL11.GL_GREATER, 0.0001F);
	        GL11.glDisable(GL11.GL_CULL_FACE);
			
			Iterator<Map.Entry<BlockPos, IProceduralMeshUser>> iter = muMap.entrySet().iterator();
			while(iter.hasNext())
			{
				IProceduralMeshUser mu = iter.next().getValue();
				
				MeshType mt = mu.GetMeshType();
				
				// only implementing quad strips right now
				
				switch(mt)
				{
				case TRIANGLES:
					renderTriangles(mu,partialTicks);
					break;
				case QUADS:
					renderQuads(mu,partialTicks);
					break;
				case QUADSTRIP:
					renderQuadStrips(mu,partialTicks);
					break;
				}
			}
			
			GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
	        GL11.glPopAttrib();
		}
	}
	
	@SubscribeEvent
    public void onClTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;
        
        long currentTime = System.currentTimeMillis();
        if(lastUpdate == -1)
        {
        	lastUpdate = currentTime;
        	return;
        }
        long deltaTime = currentTime - lastUpdate;
        float deltaTimef = (float)deltaTime / 1000.0F;
        
        synchronized(tickLock)
        {
        	Iterator<ITickableMesh> iter = tmList.iterator();
        	while(iter.hasNext())
        	{
        		ITickableMesh tm = iter.next();
        		tm.onTick(deltaTimef);
        	}
        }
	}
	
	public interface ITickableMesh
	{
		public void onTick(float deltaTime);
	}
	
	public interface IProceduralMeshUser
	{
		public List<Vec3d> GetCenterPoints();
		public MeshType GetMeshType();
		public CameraBehavior GetCameraBehavior();
		public Vec3d GetCustomSize();
	}
	
	public interface ITexturedMesh
	{
		public ITextureObject bindTexture();
		public default GLRenderUtil.Color GetColor() {return new GLRenderUtil.Color(1.0F,1.0F,1.0F,1.0F);};
		public default GLRenderUtil.TextureCoords GetTexturesCoords(){return new GLRenderUtil.TextureCoords(0, 0, 1.0F, 1.0F);}
	}
	
	public enum MeshType
	{
		TRIANGLES,
		QUADS,
		QUADSTRIP
	}
	
	public enum CameraBehavior
	{
		FOLLOWCAMERA,
		IGNORECAMERA
	}
}
