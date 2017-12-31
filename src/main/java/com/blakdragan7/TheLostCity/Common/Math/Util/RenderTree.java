package com.blakdragan7.TheLostCity.Common.Math.Util;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.Nullable;

import com.blakdragan7.TheLostCity.Common.Block.TLCBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class RenderTree {
	private World world;
	public QuadNode<Vec3d> root;
	public RenderTree(World world,Vec3d renderPosition)
	{
		this.world = world;
		this.root = new QuadNode<Vec3d>(renderPosition);
	}
	
	public RenderTree(World world,BlockPos pos) {
		this.world = world;
		this.root = new QuadNode<Vec3d>(new Vec3d(pos.getX(),pos.getY(),pos.getZ()));
	}

	public RenderTree(World world) {
		this.world = world;
	}

	public List<Vec3d> GetSortedList()
	{
		List<Vec3d> list = new LinkedList();
		QuadNode<Vec3d> Iterator = root;
		RecursivePopulateList(list, root);
		
		return list;
	}
	
	public boolean contains(Vec3d point)
	{
		QuadNode<Vec3d> Iterator = root;
		if(Iterator.value == point)return true;
		return recursContains(Iterator,point);
		
	}
	
	public QuadNode<Vec3d> iterator()
	{
		return root;
	}
	
	public void BuildTreeForBlockTypeAndLocation(Block block,int x,int y,int z)
	{
		QuadNode<Vec3d> iterator = root;
		List<Vec3d> helperList = new LinkedList();
		root = recurseBuildTreeForBlockType(helperList,iterator,block,x,y,z);
	}
	
	private QuadNode<Vec3d> recurseBuildTreeForBlockType(List<Vec3d> helperList,QuadNode<Vec3d> node,Block block,int x,int y,int z)
	{
		IBlockState currentState = world.getBlockState(new BlockPos(x,y,z));
		Vec3d vec = new Vec3d(x+.5,y+.5,z+.5);
		if(currentState.getBlock() == TLCBlocks.crystalAccelerant && helperList.contains(vec) == false)
		{
			helperList.add(vec);
			if(node == null)
			{
				node = new QuadNode<Vec3d>(vec);
			}
			else
			{
				node.value = vec;
			}
			node.East = recurseBuildTreeForBlockType(helperList,node.East,block,x+1,y,z);
			node.West = recurseBuildTreeForBlockType(helperList,node.West,block,x-1,y,z);
			node.South = recurseBuildTreeForBlockType(helperList,node.South,block,x,y,z+1);
			node.North = recurseBuildTreeForBlockType(helperList,node.North,block,x,y,z-1);
			
		}
		return node;
	}
	
	private boolean recursContains(QuadNode<Vec3d> node,Vec3d Point)
	{
		if(node.value == Point)return true;
		boolean contains = false;
		if(node.East != null) contains = recursContains(node.East,Point);
		if(contains) return contains;
		if(node.West != null) contains = contains || recursContains(node.West,Point);
		if(contains) return contains;
		if(node.North != null) contains = contains || recursContains(node.North,Point);
		if(contains) return contains;
		if(node.South != null) contains = contains || recursContains(node.South,Point);
		return contains;
	}
	
	private void RecursivePopulateList(List<Vec3d> list,QuadNode<Vec3d> node)
	{
		if(node.East != null)
		{
			list.add(node.value);
			list.add(node.East.value);
		}
		if(node.West != null)
		{
			list.add(node.value);
			list.add(node.West.value);
		}
		if(node.North != null)
		{
			list.add(node.value);
			list.add(node.North.value);
		}
		if(node.South != null)
		{
			list.add(node.value);
			list.add(node.South.value);
		}
		
		if(node.East != null)RecursivePopulateList(list, node.East);
		if(node.West != null)RecursivePopulateList(list, node.West);
		if(node.North != null)RecursivePopulateList(list, node.North);
		if(node.South != null)RecursivePopulateList(list, node.South);
	}
}
