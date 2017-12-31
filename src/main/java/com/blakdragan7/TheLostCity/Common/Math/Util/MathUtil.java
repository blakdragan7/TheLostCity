package com.blakdragan7.TheLostCity.Common.Math.Util;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;

public class MathUtil{

	public static Vec3d getMiddleVec(Vec3d first, Vec3d second)
	{
		double x = (first.x + second.x) / 2.0;
		double y = (first.y + second.y) / 2.0;
		double z = (first.z + second.z) / 2.0;
		
		return new Vec3d(x,y,z);
	}

	public static EnumFacing DirectionFromVecToVec(Vec3d first,Vec3d second)
	{
		Vec3d diff = first.subtract(second);
		
		
		if(diff.x > 0 && diff.y ==0 && diff.z == 0)
		{
			return EnumFacing.EAST;
		}
		if(diff.x < 0 && diff.y ==0 && diff.z == 0)
		{
			return EnumFacing.WEST;
		}
		if(diff.x == 0 && diff.y ==0 && diff.z > 0)
		{
			return EnumFacing.SOUTH;
		}
		if(diff.x == 0 && diff.y ==0 && diff.z < 0)
		{
			return EnumFacing.NORTH;
		}
		if(diff.x == 0 && diff.y < 0 && diff.z == 0)
		{
			return EnumFacing.DOWN;
		}
		if(diff.x == 0 && diff.y > 0 && diff.z == 0)
		{
			return EnumFacing.UP;
		}
		
		return EnumFacing.DOWN;
	}
	
	public static EnumFacing DirectionFromPosToPos(BlockPos first,BlockPos second)
	{
		BlockPos diff = first.subtract(second);
		
		
		if(diff.getX() > 0 && diff.getY() ==0 && diff.getZ() == 0)
		{
			return EnumFacing.WEST;
		}
		if(diff.getX() < 0 && diff.getY() ==0 && diff.getZ() == 0)
		{
			return EnumFacing.EAST;
		}
		if(diff.getX() == 0 && diff.getY() ==0 && diff.getZ() > 0)
		{
			return EnumFacing.SOUTH;
		}
		if(diff.getX() == 0 && diff.getY() ==0 && diff.getZ() < 0)
		{
			return EnumFacing.NORTH;
		}
		if(diff.getX() == 0 && diff.getY() < 0 && diff.getZ() == 0)
		{
			return EnumFacing.DOWN;
		}
		if(diff.getX() == 0 && diff.getY() > 0 && diff.getZ() == 0)
		{
			return EnumFacing.UP;
		}
		
		return EnumFacing.DOWN;
	}
	
	public static EnumFacing GetPerpFacingFromVecToVec(Vec3d first,Vec3d second)
	{
		EnumFacing facing = DirectionFromVecToVec(first, second);
		switch(facing)
		{
		case WEST:
		case EAST:
			return EnumFacing.SOUTH;
		case SOUTH:
		case NORTH:
			return EnumFacing.EAST;
		default:
				return EnumFacing.DOWN;
		}
	}
	
	public static EnumFacing GetPerpFacingFromPosToPos(BlockPos first,BlockPos second)
	{
		EnumFacing facing = DirectionFromPosToPos(first, second);
		switch(facing)
		{
		case WEST:
		case EAST:
			return EnumFacing.SOUTH;
		case SOUTH:
		case NORTH:
			return EnumFacing.EAST;
		default:
				return EnumFacing.DOWN;
		}
	}
	
}
