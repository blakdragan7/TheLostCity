package com.blakdragan7.TheLostCity.Common.Block;

import com.blakdragan7.TheLostCity.TheLostCityMod;
import com.blakdragan7.TheLostCity.Common.Items.TLCItems;

import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockCrystalAccelerant extends BlockRedstoneWire{
	
	public BlockCrystalAccelerant()
	{
		super();
		this.setRegistryName("CrystalAccelerant");
		this.setUnlocalizedName(TheLostCityMod.MODID + ":CrystalAccelerant");
		this.setCreativeTab(CreativeTabs.MISC);
	}
	
	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
		
        return true;
    }
}
