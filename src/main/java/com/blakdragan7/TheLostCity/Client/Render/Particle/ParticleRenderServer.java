package com.blakdragan7.TheLostCity.Client.Render.Particle;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.lwjgl.opengl.GL11;

import com.blakdragan7.TheLostCity.Client.Render.Particle.ParticleBase.RenderTarget;
import com.blakdragan7.TheLostCity.Client.Render.Util.GLRenderUtil;

import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class ParticleRenderServer {
	
	private static ParticleRenderServer instance = null;
	private List<ParticleBase> particlesToUpdate;
	private long lastUpdate = -1;
	
	private ParticleRenderServer()
	{
		particlesToUpdate = new LinkedList<ParticleBase>(); 
	}
	
	public static ParticleRenderServer GetDefaultServer()
	{
		if(instance == null)instance = new ParticleRenderServer();
		return instance;
	}
	
	public void AddParticle(ParticleBase p)
	{
		if(particlesToUpdate.contains(p) == false && particlesToUpdate.size() < 100)
		{
			particlesToUpdate.add(p);
		}
	}
	
	@SubscribeEvent
    public void onOverlay(RenderGameOverlayEvent.Post event) {
        float partialTick = event.getPartialTicks();
		if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
        	for(ParticleBase p : particlesToUpdate)
            {
         	   if(p.GetRenderTarget() == RenderTarget.TARGET_UI)
         	   {
         		   p.render(partialTick);
         	   }
            }
        }
    }

    @SubscribeEvent
    public void onDebugText(RenderGameOverlayEvent.Text event) {
    	
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void onRender(RenderWorldLastEvent event) {
    	
    	float partialTick = event.getPartialTicks();
    	
    	 GL11.glPushAttrib(GL11.GL_ALL_ATTRIB_BITS);
         GL11.glEnable(GL11.GL_BLEND);
         GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
         GlStateManager.alphaFunc(GL11.GL_GREATER, 0.0001F);
         GL11.glDisable(GL11.GL_CULL_FACE);

         GL11.glDisable(GL11.GL_TEXTURE_2D);
    	
    	for(ParticleBase p : particlesToUpdate)
        {
     	   if(p.GetRenderTarget() == RenderTarget.TARGET_MAINLOOP)
     	   {
     		   p.render(partialTick);
     	   }
        }
    	
    	GlStateManager.alphaFunc(GL11.GL_GREATER, 0.1F);
        GL11.glPopAttrib();
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
        float deltaTimef = deltaTime / 1000;
        Iterator<ParticleBase> iter = particlesToUpdate.iterator();
        while(iter.hasNext())
        {
        	ParticleBase p = iter.next();
     		  p.Tick(deltaTimef);  
     		  if(p.IsAlive() == false && p.IsRemoveable())
     		  {
     			iter.remove();
     		  }
        }
        
        lastUpdate = currentTime;
    }
}
