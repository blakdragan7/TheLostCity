package com.blakdragan7.TheLostCity.Client.Render.Particle;

import com.blakdragan7.TheLostCity.Client.Render.Particle.ParticleBase.RenderTarget;
import com.blakdragan7.TheLostCity.Client.Render.Util.GLRenderUtil;

import net.minecraft.util.math.Vec3d;

public class CameraFacingParticle extends ParticleBase{

	public CameraFacingParticle(RenderTarget target, float MaxAliveTime, Vec3d position) {
		super(target, MaxAliveTime, position);
		// TODO Auto-generated constructor stub
	}
	public CameraFacingParticle(RenderTarget target,float MaxAliveTime,Vec3d position,Vec3d scale)
	{
		super(target,MaxAliveTime,position,scale);
	}
	
	public CameraFacingParticle(RenderTarget target,float MaxAliveTime,Vec3d position,Vec3d scale,Vec3d rotation)
	{
		super(target,MaxAliveTime,position,scale,rotation);
	}

	@Override
	public void render(float deltaTime)
	{
		GLRenderUtil.GetDefaultRenderer().renderSquareFacingCamera(position, scale, rotation,deltaTime);
	}
}
