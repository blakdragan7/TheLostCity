package com.blakdragan7.TheLostCity.Client.Render.Util;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ActiveRenderInfo;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;

public class GLRenderUtil {
	
	private static GLRenderUtil instance = null;
	
	private GLRenderUtil(){}
	
	public static GLRenderUtil GetDefaultRenderer()
	{
		if(instance == null)instance = new GLRenderUtil();
		return instance;
	}
	private double InterpDouble(double Start, double End, double alpha)
	{
		return Start + (End - Start) * alpha;
	}
	private float InterpFloat(float Start, float End, float alpha)
	{
		return Start + (End - Start) * alpha;
	}
	private Vec3d InterpVec(Vec3d Start,Vec3d End,float alpha)
	{
		return new Vec3d(Start.x + (End.x - Start.x) * alpha, Start.y + (End.y - Start.y) * alpha, Start.z + (End.z - Start.z) * alpha);
	}
	
	public void renderSquare(Vec3d position,float sizeh,float sizev, EnumFacing facing,float partialTicks)
	{	
//		GL11.glPushMatrix();
		
//		GL11.glTranslated(position.x, position.y, position.z);
//		GL11.glScaled(scale.x, scale.y, scale.z);
//		GL11.glRotated(360.0D, rotation.x, rotation.y, rotation.z);
		
		GlStateManager.disableCull();
		
		Tessellator tes = Tessellator.getInstance();
		BufferBuilder buf = tes.getBuffer();
		
		Entity e = Minecraft.getMinecraft().getRenderViewEntity();
        if(e == null) {
            e = Minecraft.getMinecraft().player;
        }
				
        double EntityPositionX = InterpDouble(e.lastTickPosX,e.posX,partialTicks); 
        double EntityPositionY = InterpDouble(e.lastTickPosY,e.posY,partialTicks);
        double EntityPositionZ = InterpDouble(e.lastTickPosZ,e.posZ,partialTicks);
		
        double deltaPosX = position.x - EntityPositionX;
        double deltaPosY = position.y - EntityPositionY;
        double deltaPosZ = position.z - EntityPositionZ;
        
		buf.begin(GL11.GL_QUADS,DefaultVertexFormats.POSITION_COLOR);
		
		switch(facing)
		{
		case WEST:
		case EAST:
			buf.pos(deltaPosX, deltaPosY + sizev, deltaPosZ).tex(0,1).endVertex();
			buf.pos(deltaPosX, deltaPosY + sizev, deltaPosZ+sizeh).tex(1,1).endVertex();
			buf.pos(deltaPosX, deltaPosY, deltaPosZ+sizeh).tex(1,0).endVertex();
			buf.pos(deltaPosX, deltaPosY, deltaPosZ).tex(0,0).endVertex();
			break;
		case SOUTH:
		case NORTH:
			buf.pos(deltaPosX, deltaPosY + sizev, deltaPosZ).color(1.0F,0.0F,0.0F,1.0F).endVertex();
			buf.pos(deltaPosX+sizeh, deltaPosY + sizev, deltaPosZ).color(1.0F,0.0F,0.0F,1.0F).endVertex();
			buf.pos(deltaPosX+sizeh, deltaPosY, deltaPosZ).color(1.0F,0.0F,0.0F,1.0F).endVertex();
			buf.pos(deltaPosX, deltaPosY, deltaPosZ).color(1.0F,0.0F,0.0F,1.0F).endVertex();
			break;
		case UP:
		case DOWN:
			buf.pos(deltaPosX, deltaPosY, deltaPosZ + sizev).tex(0,1).endVertex();
			buf.pos(deltaPosX+sizeh, deltaPosY, deltaPosZ + sizev).tex(1,1).endVertex();
			buf.pos(deltaPosX+sizeh, deltaPosY, deltaPosZ).tex(1,0).endVertex();
			buf.pos(deltaPosX, deltaPosY, deltaPosZ).tex(0,0).endVertex();
			break;
		}
		tes.draw();
		
		GlStateManager.enableCull();
		
//		GL11.glPopMatrix();
	}
	
	public void renderSquareFacingCamera(Vec3d position,Vec3d scalev, Vec3d rotation,float partialTicks)
	{	
		float arX =  ActiveRenderInfo.getRotationX();
        float arZ =  ActiveRenderInfo.getRotationZ();
        float arYZ = ActiveRenderInfo.getRotationYZ();
        float arXY = ActiveRenderInfo.getRotationXY();
        float arXZ = ActiveRenderInfo.getRotationXZ();

        Entity e = Minecraft.getMinecraft().getRenderViewEntity();
        if(e == null) {
            e = Minecraft.getMinecraft().player;
        }
        double iPX = e.prevPosX + (e.posX - e.prevPosX) * partialTicks;
        double iPY = e.prevPosY + (e.posY - e.prevPosY) * partialTicks;
        double iPZ = e.prevPosZ + (e.posZ - e.prevPosZ) * partialTicks;

        float scale = (float) scalev.x;
        
        Vec3d v1 = new Vec3d(-arX * scale - arYZ * scale, -arXZ * scale, -arZ * scale - arXY * scale);
        Vec3d v2 = new Vec3d(-arX * scale + arYZ * scale,  arXZ * scale, -arZ * scale + arXY * scale);
        Vec3d v3 = new Vec3d( arX * scale + arYZ * scale,  arXZ * scale,  arZ * scale + arXY * scale);
        Vec3d v4 = new Vec3d( arX * scale - arYZ * scale, -arXZ * scale,  arZ * scale - arXY * scale);

        double v1x = position.x + v1.x - iPX;
        double v2x = position.x + v2.x - iPX;
        double v3x = position.x + v3.x - iPX;
        double v4x = position.x + v4.x - iPX;
        
        double v1y = position.y + v1.y - iPY;
        double v2y = position.y + v2.y - iPY;
        double v3y = position.y + v3.y - iPY;
        double v4y = position.y + v4.y - iPY;
        
        double v1z = position.z + v1.z - iPZ;
        double v2z = position.z + v2.z - iPZ;
        double v3z = position.z + v3.z - iPZ;
        double v4z = position.z + v4.z - iPZ;
        
        Tessellator t = Tessellator.getInstance();
        BufferBuilder vb = t.getBuffer();
        vb.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
        vb.pos(v1x, v1y, v1z).tex(0,1).endVertex();
        vb.pos(v2x, v2y, v2z).tex(1,1).endVertex();
        vb.pos(v3x, v3y, v3z).tex(1,0).endVertex();
        vb.pos(v4x, v4y, v4z).tex(0,0).endVertex();
        t.draw();
	}
}
