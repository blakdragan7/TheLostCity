package com.blakdragan7.TheLostCity.blocks;

import com.blakdragan7.TheLostCity.TheLostCityMod;
import com.blakdragan7.TheLostCity.items.TLCItems;

import net.minecraft.block.BlockBookshelf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.world.World;

public class TLCLibraryShelf extends BlockBookshelf{

	public TLCLibraryShelf() {
		super();
		this.setRegistryName("LibraryShelf");
		this.setUnlocalizedName(TheLostCityMod.MODID + ":LibraryShelf");
	}
	
	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		if(!worldIn.isRemote)
		{
			Entity entityIn = new EntityItem(worldIn,playerIn.posX,playerIn.posY,playerIn.posZ,new ItemStack(TLCItems.tlcStarterBook,1));
			worldIn.spawnEntity(entityIn);
		}
		
        return true;
    }

}
