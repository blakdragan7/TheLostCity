package com.blakdragan7.TheLostCity.Common.Items;

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
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import scala.Console;

public class TLCGateKey extends Item{
	public TLCGateKey(){
		super();
		this.setRegistryName("_GateKey_necklace");
		this.setUnlocalizedName(TheLostCityMod.MODID + ":_GateKey_necklace");
		this.setCreativeTab(CreativeTabs.MISC);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void onUpdate(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected)
    {
		if(isSelected){
			BlockPos currentPos = entityIn.getPosition();
			Vec3d forward = entityIn.getForward();
			// for now this is the origin, but later can be the gate
			BlockPos pointOfInterest = worldIn.getSpawnPoint();
			
			BlockPos distance = pointOfInterest.subtract(currentPos);
			double mag = Math.sqrt( Math.pow(distance.getX(), 2) + Math.pow(distance.getY(), 2) + Math.pow(distance.getZ(), 2));
			double dirx,diry,dirz;

			dirx = distance.getX()/mag;
			diry = distance.getY()/mag;
			dirz = distance.getZ()/mag;
			
			double posx = currentPos.getX() + (forward.x * 2);
			double posy = currentPos.getY();
			double posz = currentPos.getZ() + (forward.z * 2);
			
			worldIn.spawnParticle(EnumParticleTypes.PORTAL, posx, posy, posz, dirx, diry, dirz, new int[0]);
			
		}
    }
}
