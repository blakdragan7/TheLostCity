package com.blakdragan7.TheLostCity.Client.Render.Particle;

import com.blakdragan7.TheLostCity.Client.Render.Util.GLRenderUtil;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;

public abstract class ParticleBase {
	public RenderTarget renderTarget;
	protected float timeToLive;
	protected float timeAlive;
	private boolean hasBeenRemoved;
	protected boolean removeFlag;
	
	protected Vec3d position;
	protected Vec3d scale;
	protected Vec3d rotation;
	
	public ParticleBase(RenderTarget target,float MaxAliveTime,Vec3d position)
	{
		this.timeToLive = MaxAliveTime;
		this.timeAlive = 0;
		
		this.removeFlag = false;
		this.hasBeenRemoved = false;
		this.renderTarget = RenderTarget.TARGET_MAINLOOP;
		
		this.position = position;
		this.scale = new Vec3d(1,1,1);
		this.rotation = new Vec3d(0,0,0);
	}
	
	public ParticleBase(RenderTarget target,float MaxAliveTime,Vec3d position,Vec3d scale)
	{
		this.timeToLive = MaxAliveTime;
		this.timeAlive = 0;
		
		this.removeFlag = false;
		this.hasBeenRemoved = false;
		this.renderTarget = RenderTarget.TARGET_MAINLOOP;
		
		this.position = position;
		this.scale = scale;
		this.rotation = new Vec3d(0,0,0);
	}
	
	public ParticleBase(RenderTarget target,float MaxAliveTime,Vec3d position,Vec3d scale,Vec3d rotation)
	{
		this.timeToLive = MaxAliveTime;
		this.timeAlive = 0;
		
		this.removeFlag = false;
		this.hasBeenRemoved = false;
		this.renderTarget = RenderTarget.TARGET_MAINLOOP;
		
		this.position = position;
		this.scale = scale;
		this.rotation = rotation;
	}
	
	public boolean IsRemoveable()
	{
		return !this.hasBeenRemoved;
	}
	
	public boolean IsAlive()
	{
		return timeToLive > 0 && !removeFlag; 
	}
	
	public float TimeAlive()
	{
		return timeAlive;
	}
	
	public float TimeLeftAlive()
	{
		return timeToLive;
	}
	
	public void SetShouldBeRemoved()
	{
		removeFlag = true;
	}
	
	public void RemoveShouldBeRemoved()
	{
		removeFlag = false;
	}
	
	public abstract void render(float deltaTime);
	
	public RenderTarget GetRenderTarget()
	{
		return renderTarget;
	}
	
	public void Tick(float deltaTime)
	{
		timeAlive += deltaTime;
		timeToLive -= deltaTime;
		
		if(timeToLive <= 0)this.SetShouldBeRemoved();
			
	}
	// rendering order, 0 is rendered below everything else 1 above 0 etc ...
	public int GetZOrder()
	{
		return 0;
	}
	
	public static enum RenderTarget{
		TARGET_UI,
		TARGET_MAINLOOP
	}
}
