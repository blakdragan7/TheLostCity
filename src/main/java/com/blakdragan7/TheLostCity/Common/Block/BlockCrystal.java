package com.blakdragan7.TheLostCity.Common.Block;

import com.blakdragan7.TheLostCity.TheLostCityMod;
import com.blakdragan7.TheLostCity.Client.TLCProxyClient;
import com.blakdragan7.TheLostCity.Client.Render.Tile.CrystalTESR;
import com.blakdragan7.TheLostCity.Common.Block.Tile.TileEntityCrystal;

import net.minecraft.block.Block;
import net.minecraft.block.ITileEntityProvider;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumBlockRenderType;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class BlockCrystal extends Block implements ITileEntityProvider {

	public BlockCrystal() {
		super(Material.ROCK);
		setRegistryName("crystalblock");
		setUnlocalizedName(TheLostCityMod.MODID + ":crystalblock");
		setCreativeTab(CreativeTabs.MISC);
	}

	@Override
	public TileEntity createNewTileEntity(World worldIn, int meta) {
		// TODO Auto-generated method stub
		return new TileEntityCrystal();
	}
	
	@SideOnly(Side.CLIENT)
	public void InitModel() {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(this),0,new ModelResourceLocation(getRegistryName(), "inventory"));
		
		ClientRegistry.bindTileEntitySpecialRenderer(TileEntityCrystal.class, new CrystalTESR());
	}
	
	@Override
    @SideOnly(Side.CLIENT)
    public boolean shouldSideBeRendered(IBlockState blockState, IBlockAccess worldIn, BlockPos pos, EnumFacing side) {
        return false;
    }
	
	@Override
	public EnumBlockRenderType getRenderType(IBlockState iBlockState) 
	{
		return EnumBlockRenderType.ENTITYBLOCK_ANIMATED;
	}
	
	@Override
    public boolean isBlockNormalCube(IBlockState blockState) {
        return false;
    }

    @Override
    public boolean isOpaqueCube(IBlockState blockState) {
        return false;
    }

    private TileEntityCrystal getTE(World world, BlockPos pos) {
        return (TileEntityCrystal) world.getTileEntity(pos);
    }
	
}
