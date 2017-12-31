package com.blakdragan7.TheLostCity.Client.Render.Effect;

import java.text.Collator;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import com.blakdragan7.TheLostCity.Client.Render.ProceduralMesh.TexturedProceduralMesh;
import com.blakdragan7.TheLostCity.Client.Render.RenderServers.ProceduralMeshRenderServer;
import com.blakdragan7.TheLostCity.Client.Render.Util.TextureUtil;
import com.blakdragan7.TheLostCity.Client.Render.RenderServers.ProceduralMeshRenderServer.CameraBehavior;
import com.blakdragan7.TheLostCity.Client.Render.RenderServers.ProceduralMeshRenderServer.ITickableMesh;
import com.blakdragan7.TheLostCity.Client.Render.RenderServers.ProceduralMeshRenderServer.MeshType;
import com.blakdragan7.TheLostCity.Common.Block.BlockCrystalAccelerant;
import com.blakdragan7.TheLostCity.Common.Block.TLCBlocks;

import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.texture.SimpleTexture;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.minecraftforge.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class AccelerantGlowEffect extends TexturedProceduralMesh implements ITickableMesh{

	private List<Vec3d> AllPositions;
	private World world;
	private BlockPos pos;
	private float currentTime;
	
	private boolean OnlyOneTrue(boolean first,boolean second,boolean third,boolean fourth)
	{
		if( first && !(second || third || fourth))
			return true;
		if(second && !(first || third || fourth))
			return true;
		if(third && !(first || second || fourth))
			return true;
		if(fourth && !(first || second || third))
			return true;
		
		return false;
	}
	
	public AccelerantGlowEffect(World world,BlockPos pos)
	{
		super(TextureUtil.AccelerantGlowEffectTexture);
		this.world = world;
		this.pos = pos;
		this.AllPositions = new LinkedList();
		this.GenerateListFromStartingPos(pos);
		this.currentTime = Math.abs((float)Math.sin(pos.getX())+pos.getZ());
		
		c.r = 0;
		c.b = 0;
		c.g = 0;
	}
	
	public void GenerateListFromStartingPos(BlockPos pos)
	{
		// currently reders double quads needs to be fixed
		 IBlockState state = world.getBlockState(pos);
		 state = TLCBlocks.crystalAccelerant.getActualState(state, world, pos);
		 if(state.getBlock() == TLCBlocks.crystalAccelerant)
		 {
			 Vec3d CenterPos = new Vec3d(pos.getX()+0.5,pos.getY(),pos.getZ()+0.5);
			 AllPositions.clear();
			 boolean EastConnection = state.getValue(BlockCrystalAccelerant.EAST) != BlockCrystalAccelerant.EnumAttachPosition.NONE;
			 boolean WestConnection = state.getValue(BlockCrystalAccelerant.WEST) != BlockCrystalAccelerant.EnumAttachPosition.NONE;
			 boolean NorthConnection = state.getValue(BlockCrystalAccelerant.NORTH) != BlockCrystalAccelerant.EnumAttachPosition.NONE;
			 boolean SouthConnection = state.getValue(BlockCrystalAccelerant.SOUTH) != BlockCrystalAccelerant.EnumAttachPosition.NONE;
			 if(EastConnection)
			 {
				 if(OnlyOneTrue(EastConnection,WestConnection,NorthConnection,SouthConnection))
					 AllPositions.add(new Vec3d(CenterPos.x-0.5,CenterPos.y,CenterPos.z));
				 else  AllPositions.add(CenterPos);
				 AllPositions.add(new Vec3d(CenterPos.x+1,CenterPos.y,CenterPos.z));
			 }
			 if(WestConnection)
			 {
				 if(OnlyOneTrue(EastConnection,WestConnection,NorthConnection,SouthConnection))
					 AllPositions.add(new Vec3d(CenterPos.x+0.5,CenterPos.y,CenterPos.z));
				 else  AllPositions.add(CenterPos);
				 AllPositions.add(new Vec3d(CenterPos.x-1,CenterPos.y,CenterPos.z));
			 }
			 if(NorthConnection)
			 {
				 if(OnlyOneTrue(EastConnection,WestConnection,NorthConnection,SouthConnection))
					 AllPositions.add(new Vec3d(CenterPos.x,CenterPos.y,CenterPos.z+0.5));
				 else  AllPositions.add(CenterPos);
				 AllPositions.add(new Vec3d(CenterPos.x,CenterPos.y,CenterPos.z-1));
			 }
			 if(SouthConnection)
			 {
				 if(OnlyOneTrue(EastConnection,WestConnection,NorthConnection,SouthConnection))
					 AllPositions.add(new Vec3d(CenterPos.x,CenterPos.y,CenterPos.z-0.5));
				 else  AllPositions.add(CenterPos);
				 AllPositions.add(new Vec3d(CenterPos.x,CenterPos.y,CenterPos.z+1));
			 }
		 }
	}
	
	@Override
	public List<Vec3d> GetCenterPoints() {
		// TODO Auto-generated method stub
		return AllPositions;
	}

	@Override
	public MeshType GetMeshType() {
		// TODO Auto-generated method stub
		return MeshType.QUADS;
	}

	@Override
	public CameraBehavior GetCameraBehavior() {
		// TODO Auto-generated method stub
		return CameraBehavior.IGNORECAMERA;
	}

	@Override
	public Vec3d GetCustomSize() {
		// TODO Auto-generated method stub
		return new Vec3d(-1,0.5,-1);
	}

	@Override
	public void onTick(float deltaTime) {
		// TODO Auto-generated method stub
		this.currentTime += deltaTime;
		c.r = 0.3F + (Math.abs((float)Math.cos(this.currentTime*Math.PI))*0.7F);
		tc.vmax = Math.abs((float)Math.sin(this.currentTime*Math.PI));
		if(this.currentTime >= 1.0F)
		{
			this.currentTime = 0.0F;
		}
	}
	
}
