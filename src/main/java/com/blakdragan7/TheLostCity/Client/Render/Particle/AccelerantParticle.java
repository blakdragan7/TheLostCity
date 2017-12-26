package com.blakdragan7.TheLostCity.Client.Render.Particle;

import net.minecraft.client.particle.Particle;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;



public class AccelerantParticle extends Particle{

	//public ResourceLocation texture = new ResourceLocation("tlc:particles/accelerantparticle");
	
	protected AccelerantParticle(World worldIn, double posXIn, double posYIn, double posZIn) {
		super(worldIn, posXIn, posYIn, posZIn);
		// TODO Auto-generated constructor stub
	}
	public AccelerantParticle(World worldIn, double xCoordIn, double yCoordIn, double zCoordIn, double xSpeedIn,
			double ySpeedIn, double zSpeedIn) {
		super(worldIn, xCoordIn, yCoordIn, zCoordIn, xSpeedIn, ySpeedIn, zSpeedIn);
		// TODO Auto-generated constructor stub
	}
	@Override
	public int getFXLayer()
    {
        return 0;
    }
	
	@Override
	public void onUpdate()
	{
		super.onUpdate();
		
		this.particleScale = 1 + (float) Math.abs(Math.cos((System.currentTimeMillis() / 10) % 360)); 
	}
	
	@Override
	public void renderParticle(BufferBuilder buffer, Entity entityIn, float partialTicks, float rotationX, float rotationZ, float rotationYZ, float rotationXY, float rotationXZ)
    {
		 float f = (float)this.particleTextureIndexX / 16.0F;
	        float f1 = f + 0.0624375F;
	        float f2 = (float)this.particleTextureIndexY / 16.0F;
	        float f3 = f2 + 0.0624375F;
	        float f4 = 0.1F * this.particleScale;

	        if (this.particleTexture != null)
	        {
	            f = this.particleTexture.getMinU();
	            f1 = this.particleTexture.getMaxU();
	            f2 = this.particleTexture.getMinV();
	            f3 = this.particleTexture.getMaxV();
	        }

	        float f5 = (float)(this.prevPosX + (this.posX - this.prevPosX) * (double)partialTicks - interpPosX);
	        float f6 = (float)(this.prevPosY + (this.posY - this.prevPosY) * (double)partialTicks - interpPosY);
	        float f7 = (float)(this.prevPosZ + (this.posZ - this.prevPosZ) * (double)partialTicks - interpPosZ);
	        int i = this.getBrightnessForRender(partialTicks);
	        int j = i >> 16 & 65535;
	        int k = i & 65535;
	        Vec3d[] avec3d = new Vec3d[] {new Vec3d((double)(-rotationX * f4 - rotationXY * f4), (double)(-rotationZ * f4), (double)(-rotationYZ * f4 - rotationXZ * f4)), new Vec3d((double)(-rotationX * f4 + rotationXY * f4), (double)(rotationZ * f4), (double)(-rotationYZ * f4 + rotationXZ * f4)), new Vec3d((double)(rotationX * f4 + rotationXY * f4), (double)(rotationZ * f4), (double)(rotationYZ * f4 + rotationXZ * f4)), new Vec3d((double)(rotationX * f4 - rotationXY * f4), (double)(-rotationZ * f4), (double)(rotationYZ * f4 - rotationXZ * f4))};

	        if (this.particleAngle != 0.0F)
	        {
	            float f8 = this.particleAngle + (this.particleAngle - this.prevParticleAngle) * partialTicks;
	            float f9 = MathHelper.cos(f8 * 0.5F);
	            float f10 = MathHelper.sin(f8 * 0.5F) * (float)cameraViewDir.x;
	            float f11 = MathHelper.sin(f8 * 0.5F) * (float)cameraViewDir.y;
	            float f12 = MathHelper.sin(f8 * 0.5F) * (float)cameraViewDir.z;
	            Vec3d vec3d = new Vec3d((double)f10, (double)f11, (double)f12);

	            for (int l = 0; l < 4; ++l)
	            {
	                avec3d[l] = vec3d.scale(2.0D * avec3d[l].dotProduct(vec3d)).add(avec3d[l].scale((double)(f9 * f9) - vec3d.dotProduct(vec3d))).add(vec3d.crossProduct(avec3d[l]).scale((double)(2.0F * f9)));
	            }
	        }

	        buffer.pos((double)f5 + avec3d[0].x, (double)f6 + avec3d[0].y, (double)f7 + avec3d[0].z).tex((double)f1, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
	        buffer.pos((double)f5 + avec3d[1].x, (double)f6 + avec3d[1].y, (double)f7 + avec3d[1].z).tex((double)f1, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
	        buffer.pos((double)f5 + avec3d[2].x, (double)f6 + avec3d[2].y, (double)f7 + avec3d[2].z).tex((double)f, (double)f2).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
	        buffer.pos((double)f5 + avec3d[3].x, (double)f6 + avec3d[3].y, (double)f7 + avec3d[3].z).tex((double)f, (double)f3).color(this.particleRed, this.particleGreen, this.particleBlue, this.particleAlpha).lightmap(j, k).endVertex();
    }
}