package com.blakdragan7.TheLostCity.Common.Block;

import java.util.Random;

import com.blakdragan7.TheLostCity.TheLostCityMod;
import com.blakdragan7.TheLostCity.Client.Render.Particle.ParticleBase;
import com.blakdragan7.TheLostCity.Client.Render.Particle.ParticleRenderServer;
import com.blakdragan7.TheLostCity.Client.Render.Particle.TLCParticleEffects;
import com.blakdragan7.TheLostCity.Client.Render.Particle.ParticleBase.RenderTarget;
import com.blakdragan7.TheLostCity.Common.Items.TLCItems;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRedstoneWire;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFlintAndSteel;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrystalAccelerant extends BlockRedstoneWire{
	
	public static final PropertyBool IS_ACTIVATED = PropertyBool.create("is_activated");
	public BlockCrystalAccelerant()
	{
		super();
		this.setRegistryName("CrystalAccelerant");
		this.setUnlocalizedName(TheLostCityMod.MODID + ":CrystalAccelerant");
		this.setCreativeTab(CreativeTabs.MISC);
		IBlockState state = this.getDefaultState();
		state = state.withProperty(IS_ACTIVATED, false);
		this.setDefaultState(state);
	}
	
	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state)
    {
        return new ItemStack(Item.getItemFromBlock(this));
    }
	
	public Item getItemDropped(IBlockState state, Random rand, int fortune)
    {
        return Item.getItemFromBlock(this);
    }
	
	@Override
	public IBlockState getActualState(IBlockState state, IBlockAccess worldIn, BlockPos pos)
    {
        state = super.getActualState(state, worldIn, pos);
        state = state.withProperty(IS_ACTIVATED,false);
        return state;
    }
	
	@Override
	protected BlockStateContainer createBlockState()
    {
        return new BlockStateContainer(this, new IProperty[] {NORTH, EAST, SOUTH, WEST, POWER,IS_ACTIVATED});
    }
	
	@Override
	public void neighborChanged(IBlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos)
    {
		// only spreading one way 
		if (!worldIn.isRemote)
        {
			if (this.canPlaceBlockAt(worldIn, pos))
			{
				IBlockState TheirState = worldIn.getBlockState(fromPos);
				if(TheirState.getBlock() == this)
				{
		            boolean active = TheirState.getValue(IS_ACTIVATED);
		        	if (active)
		            {
		        		IBlockState myState = worldIn.getBlockState(pos).withProperty(IS_ACTIVATED, active);
		            	worldIn.setBlockState(pos, myState,2);
		            	for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
		                {
		            		BlockPos nextPos = pos.offset(enumfacing);
		            		IBlockState localState = worldIn.getBlockState(nextPos);
		            		if(localState.getBlock() == this && localState.getValue(IS_ACTIVATED) == false)
		            		{
		            			worldIn.setBlockState(nextPos, localState.withProperty(IS_ACTIVATED, active),2);
		            			worldIn.notifyNeighborsOfStateChange(nextPos, this, false);
		            		}
		            	}
		            }
				}
				super.neighborChanged(state, worldIn, pos, blockIn, fromPos);
			}
			else
	        {
	            this.dropBlockAsItem(worldIn, pos, state, 0);
	            worldIn.setBlockToAir(pos);
	        }
        }
    }
	
	@Override
    public boolean onBlockActivated(World worldIn, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ)
    {
		super.onBlockActivated(worldIn, pos, state, playerIn, hand, facing, hitX, hitY, hitZ);
		if(!worldIn.isRemote && playerIn.inventory.getCurrentItem() != null)
		{
			if(Item.getIdFromItem(playerIn.inventory.getCurrentItem().getItem()) == Item.getIdFromItem(Items.FLINT_AND_STEEL))
			{
				state = state.withProperty(IS_ACTIVATED, true);
				worldIn.setBlockState(pos, state,2);
				worldIn.notifyNeighborsOfStateChange(pos, this, false);
			}
		}
        return true;
    }
	
	@Override
	@SideOnly(Side.CLIENT)
    public void randomDisplayTick(IBlockState stateIn, World worldIn, BlockPos pos, Random rand)
    {
        int i = ((Integer)stateIn.getValue(POWER)).intValue();
        boolean activated = stateIn.getValue(IS_ACTIVATED).booleanValue();

        if (i != 0)
        {
            double d0 = (double)pos.getX() + 0.5D + ((double)rand.nextFloat() - 0.5D) * 0.2D;
            double d1 = (double)((float)pos.getY() + 0.0625F);
            double d2 = (double)pos.getZ() + 0.5D + ((double)rand.nextFloat() - 0.5D) * 0.2D;
            float f = (float)i / 15.0F;
            float f1 = f * 0.6F + 0.4F;
            float f2 = Math.max(0.0F, f * f * 0.7F - 0.5F);
            float f3 = Math.max(0.0F, f * f * 0.6F - 0.7F);
            worldIn.spawnParticle(EnumParticleTypes.REDSTONE, d0, d1, d2, (double)f1, (double)f2, (double)f3);
        }
        if(activated)
        {
        	double d0 = (double)pos.getX() + 0.5D + ((double)rand.nextFloat() - 0.5D) * 0.2D;
            double d1 = (double)((float)pos.getY() + 0.0625F);
            double d2 = (double)pos.getZ() + 0.5D + ((double)rand.nextFloat() - 0.5D) * 0.2D;
            float f = (float)15.0F / 15.0F;
            float f1 = f * 0.6F + 0.4F;
            float f2 = Math.max(0.0F, f * f * 0.7F - 0.5F);
            float f3 = Math.max(0.0F, f * f * 0.6F - 0.7F);
            ParticleBase p = new ParticleBase(RenderTarget.TARGET_MAINLOOP, 1.0F,new Vec3d(d0,d1+2.0,d2),new Vec3d(1,1,1));
            ParticleRenderServer.GetDefaultServer().AddParticle(p);
        }
    }
}
