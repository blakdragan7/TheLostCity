package com.blakdragan7.TheLostCity.blocks;

import com.blakdragan7.TheLostCity.items.TLCItemLoader;

import net.minecraft.block.BlockBookshelf;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;

public class TLCLibraryShelf extends BlockBookshelf{

	public TLCLibraryShelf(String unlocal) {
		super();
		this.setUnlocalizedName(unlocal);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumFacing side, float hitX, float hitY, float hitZ)
    {
		if(!worldIn.isRemote)
		{
			Entity entityIn = new EntityItem(worldIn,playerIn.posX,playerIn.posY,playerIn.posZ,new ItemStack(TLCItemLoader.tlcStarterBook,1));
			worldIn.spawnEntityInWorld(entityIn);
		}
		
        return true;
    }

}
