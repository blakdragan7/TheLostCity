package com.blakdragan7.TheLostCity.Client.Render.Particle;

import com.blakdragan7.TheLostCity.Client.Render.Particle.ParticleBase.RenderTarget;
import com.blakdragan7.TheLostCity.Client.Render.Util.GLRenderUtil;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;

public class FacingParticle extends ParticleBase{
	
	private EnumFacing facing;
	
	public FacingParticle(RenderTarget target, EnumFacing facing, float MaxAliveTime, Vec3d position) {
		super(target, MaxAliveTime, position);
		// TODO Auto-generated constructor stub
		this.facing = facing; 
	}
	public FacingParticle(RenderTarget target,EnumFacing facing, float MaxAliveTime,Vec3d position,Vec3d scale)
	{
		super(target,MaxAliveTime,position,scale);
		this.facing = facing;
	}
	
	public FacingParticle(RenderTarget target,EnumFacing facing, float MaxAliveTime,Vec3d position,Vec3d scale,Vec3d rotation)
	{
		super(target,MaxAliveTime,position,scale,rotation);
		this.facing = facing;
	}
	
	public void setFacing(EnumFacing newFacing)
	{
		this.facing = newFacing;
	}

	@Override
	public void render(float deltaTime)
	{
		GLRenderUtil.GetDefaultRenderer().renderSquare(position, 1,1, this.facing, deltaTime);
	}
}
