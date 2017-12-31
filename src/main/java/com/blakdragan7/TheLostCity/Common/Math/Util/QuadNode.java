package com.blakdragan7.TheLostCity.Common.Math.Util;

import net.minecraft.util.math.Vec3d;

public class QuadNode<T> {
	public QuadNode<T> West;
	public QuadNode<T> East;
	public QuadNode<T> North;
	public QuadNode<T> South;
	
	public T value;
	
	public QuadNode(T value)
	{
		this.value = value;
	}
	
	public boolean contains(T value)
	{
		return recursContains(this,value);
	}
	
	private boolean recursContains(QuadNode<T> node,T value)
	{
		if(node.value == value)return true;
		boolean contains = false;
		if(node.East != null) contains = recursContains(node.East,value);
		if(contains) return contains;
		if(node.West != null) contains = contains || recursContains(node.West,value);
		if(contains) return contains;
		if(node.North != null) contains = contains || recursContains(node.North,value);
		if(contains) return contains;
		if(node.South != null) contains = contains || recursContains(node.South,value);
		return contains;
	}
}
