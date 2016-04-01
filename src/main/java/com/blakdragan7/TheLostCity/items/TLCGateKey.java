package com.blakdragan7.TheLostCity.items;

import com.blakdragan7.TheLostCity.TheLostCityMod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockFire;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemCoal;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import scala.Console;

public class TLCGateKey extends Item{
	public TLCGateKey(){
		super();
		this.setUnlocalizedName(TheLostCityMod.MODID + "_GateKey_necklace");
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if(isSelected){
			BlockPos currentPos = entityIn.getPosition();
			// for now this is the origin, but later can be the gate
			BlockPos pointOfInterest = worldIn.getSpawnPoint();
			
			BlockPos distance = pointOfInterest.subtract(currentPos);
			double mag = Math.sqrt( Math.pow(distance.getX(), 2) + Math.pow(distance.getY(), 2) + Math.pow(distance.getZ(), 2));
			BlockPos direction = new BlockPos(distance.getX()/mag,distance.getY()/mag,distance.getZ()/mag);
			

			BlockPos dirToInterest = currentPos.add(new BlockPos(direction.getX()*4,direction.getY()*4,direction.getZ()*4));
			
			worldIn.spawnParticle(EnumParticleTypes.PORTAL, dirToInterest.getX(), dirToInterest.getY(), dirToInterest.getZ(), Math.random(), Math.random(), Math.random(), new int[0]);
			
		}
    }
}
