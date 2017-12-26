package com.blakdragan7.TheLostCity.Client.Render.Particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.Particle;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class TLCParticleEffects {
	
	@SideOnly(Side.CLIENT)
	public static Particle SpawnAccelParticleAtPos(World WorldIn, BlockPos posIn)
	{
		
		AccelerantParticle part = new AccelerantParticle(WorldIn,posIn.getX(),posIn.getY(),posIn.getZ(),0,1.0F,0);
		
		Minecraft.getMinecraft().effectRenderer.addEffect(part);
		
		return part;
	}
	
}
