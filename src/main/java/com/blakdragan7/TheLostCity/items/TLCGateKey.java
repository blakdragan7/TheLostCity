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
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import scala.Console;

public class TLCGateKey extends Item{
	public TLCGateKey(){
		super();
		this.setUnlocalizedName(TheLostCityMod.MODID + "_GateKey_necklace");
		this.setCreativeTab(CreativeTabs.tabMisc);
	}
	
	@Override
    public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if(isSelected && !worldIn.isRemote){
			BlockPos currentPos = entityIn.getPosition();
			// for now this is the origin, but later can be the gate
			BlockPos pointOfInterest = worldIn.getSpawnPoint();
			
			BlockPos distance = pointOfInterest.subtract(currentPos);
			double mag = Math.sqrt( Math.pow(distance.getX(), 2) + Math.pow(distance.getY(), 2) + Math.pow(distance.getZ(), 2));
			double dirx,diry,dirz;

			dirx = distance.getX()/mag;
			diry = distance.getY()/mag;
			dirz = distance.getZ()/mag;
			
			double posx = currentPos.getX() + dirx;
			double posy = currentPos.getY() + diry;
			double posz = currentPos.getZ() + dirz;
			
			worldIn.spawnParticle(EnumParticleTypes.PORTAL, posx, posy, posz, Math.random(), Math.random(), Math.random(), new int[0]);
			
		}
    }
}
