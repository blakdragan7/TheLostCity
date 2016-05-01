package com.blakdragan7.TheLostCity.misc;

import java.util.List;
import java.util.Random;

import com.blakdragan7.TheLostCity.blocks.TLCBlockLoader;

import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureComponent;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class VillageComponentTLCLibrary extends StructureVillagePieces.Village
{
	static final int sizex = 7;
	static final int sizey = 8;
	static final int sizez = 10;
	
	public EnumFacing facing;
	
	public static VillageComponentTLCLibrary buildComponent(StructureVillagePieces.Start start,@SuppressWarnings("rawtypes") List pieces,int x,int y,int z,int type,EnumFacing facing)
	{	
		StructureBoundingBox _boundingBox = StructureBoundingBox.getComponentToAddBoundingBox(x, y, z, 0, 0, 0, sizex, sizey, sizez, facing);
		//StructureBoundingBox _boundingBox = new StructureBoundingBox(x,y,z,x+sizex,y+sizey,z+sizez);
		_boundingBox.maxZ = _boundingBox.minZ + sizez;
		if(canVillageGoDeeper(_boundingBox)){ 
	       	if(StructureComponent.findIntersecting(pieces, _boundingBox) == null){
	       		return new VillageComponentTLCLibrary(start,_boundingBox,type,facing);
	       	}
	       }
		
		return null;
	}
	
	public VillageComponentTLCLibrary(StructureVillagePieces.Start start,StructureBoundingBox box, int type,EnumFacing facing)
    {
        super(start, type);
        this.func_186164_a(facing);
        this.facing = facing;
        this.boundingBox = box;
    }
	
	private void SetStairsFacingInward(World worldIn,EnumFacing front,int minx,int minz,int maxx,int maxz,int y, StructureBoundingBox structureBoundingBoxIn)
	{
		IBlockState northFacing = Blocks.brick_stairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.NORTH);
		IBlockState westFacing = Blocks.brick_stairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.WEST);
		IBlockState eastFacing = Blocks.brick_stairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.EAST);
		IBlockState southFacing = Blocks.brick_stairs.getDefaultState().withProperty(BlockStairs.FACING, EnumFacing.SOUTH);
		
		IBlockState right,left,back,forward;
		
		switch(front)
		{
		case EAST:
			left = eastFacing;
			right = westFacing;
			back = southFacing;
			forward = northFacing;
			break;
		case WEST:
			left = eastFacing;
			right = westFacing;
			back = southFacing;
			forward = northFacing;
			break;
		case SOUTH:
			left = eastFacing;
			right = westFacing;
			forward = northFacing;
			back = southFacing;
			break;
		case NORTH:
			left = eastFacing;
			right = westFacing;
			forward = northFacing;
			back = southFacing;
			break;
		default:
			left = eastFacing;
			right = westFacing;
			forward = northFacing;
			back = southFacing;
			break;
		};
		
		
		for(int x=minx;x<=maxx;x++)
		{
			for(int z=minz;z<=maxz;z++)
			{
				 if(z == minz)
				 {
					 this.setBlockState(worldIn, forward, x, y, z, structureBoundingBoxIn);
				 }
				 else if(z == maxz)
				 {
					 this.setBlockState(worldIn, back, x, y, z, structureBoundingBoxIn);
				 }
				 else if(x == minx)
				 {
					 this.setBlockState(worldIn, left, x, y, z, structureBoundingBoxIn);
				 }
				 else if(x == maxx)
				 {
					 this.setBlockState(worldIn, right, x, y, z, structureBoundingBoxIn);
				 }
			}
		}
	}
	
	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) 
	{	
		if (this.field_143015_k < 0)
        {
            this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

            if (this.field_143015_k < 0)
            {
                return true;
            }

            this.boundingBox.minY = this.field_143015_k -1;
        }
		
		EnumFacing LeftFacing;
		EnumFacing RightFacing;
		
		switch(this.facing)
		{
		case EAST:
		case WEST:
			RightFacing = EnumFacing.NORTH;
			LeftFacing = EnumFacing.SOUTH;
			break;
		case SOUTH:
		case NORTH:
		default:
			LeftFacing = EnumFacing.EAST;
			RightFacing = EnumFacing.WEST;
			break;
		};
		
		structureBoundingBoxIn = this.boundingBox;
		
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 6, 7, 8,  Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 6, 5, 8,  Blocks.cobblestone.getDefaultState(), Blocks.cobblestone.getDefaultState(), false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 5, 7, 7,  Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 0, 1, 5, 0, 7,  Blocks.planks.getDefaultState(), Blocks.planks.getDefaultState(), false);
		this.SetStairsFacingInward(worldIn, this.facing, 0, 0, 6, 8, 5,structureBoundingBoxIn);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 5, 1, 5, 5, 7,  Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.SetStairsFacingInward(worldIn, this.facing, 1, 1, 5, 7, 6,structureBoundingBoxIn);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 2, 6, 2, 4, 6, 6,  Blocks.brick_block.getDefaultState(), Blocks.brick_block.getDefaultState(), false);
		
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 2, 2, 6, 3, 6,  Blocks.glass_pane.getDefaultState(), Blocks.glass_pane.getDefaultState(), true);
		
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 0, 4, 0,  Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), true);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 8, 0, 4, 8,  Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), true);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 8, 6, 4, 8,  Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), true);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 6, 0, 0, 6, 4, 0,  Blocks.log.getDefaultState(), Blocks.log.getDefaultState(), true);
		
		IBlockState shelfState = TLCBlockLoader.libraryShelf.getDefaultState();
		
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 1, 1, 1, 1, 2, 7, shelfState, shelfState, false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 5, 1, 1, 5, 2, 7, shelfState, shelfState, false);
		
		this.setBlockState(worldIn, Blocks.oak_fence.getDefaultState(), 2, 1, 3, structureBoundingBoxIn);
		this.setBlockState(worldIn, Blocks.oak_fence.getDefaultState(), 4, 1, 5, structureBoundingBoxIn);
		this.setBlockState(worldIn, Blocks.oak_fence.getDefaultState(), 2, 1, 7, structureBoundingBoxIn);

		this.setBlockState(worldIn, Blocks.wooden_pressure_plate.getDefaultState(), 2, 2, 3, structureBoundingBoxIn);
		this.setBlockState(worldIn, Blocks.wooden_pressure_plate.getDefaultState(), 4, 2, 5, structureBoundingBoxIn);
		this.setBlockState(worldIn, Blocks.wooden_pressure_plate.getDefaultState(), 2, 2, 7, structureBoundingBoxIn);
		
		IBlockState rightStairs = Blocks.oak_stairs.getDefaultState().withProperty(BlockStairs.FACING, RightFacing);
		IBlockState leftStairs = Blocks.oak_stairs.getDefaultState().withProperty(BlockStairs.FACING, LeftFacing);;
		
		this.setBlockState(worldIn, rightStairs, 1, 1, 3, structureBoundingBoxIn);
		this.setBlockState(worldIn, leftStairs, 5, 1, 5, structureBoundingBoxIn);
		this.setBlockState(worldIn, rightStairs, 1, 1, 7, structureBoundingBoxIn);
		
		this.setBlockState(worldIn, Blocks.air.getDefaultState(), 1, 2, 3, structureBoundingBoxIn);
		this.setBlockState(worldIn, Blocks.air.getDefaultState(), 5, 2, 5, structureBoundingBoxIn);
		this.setBlockState(worldIn, Blocks.air.getDefaultState(), 1, 2, 7, structureBoundingBoxIn);
		
		this.placeDoorCurrentPosition(worldIn, structureBoundingBoxIn, randomIn, 3, 1, 0, this.facing == EnumFacing.WEST ||this.facing == EnumFacing.EAST ? EnumFacing.SOUTH : EnumFacing.WEST );
		this.setBlockState(worldIn, Blocks.stone_stairs.getDefaultState().withProperty(BlockStairs.FACING, this.facing), 3, 0, -1, structureBoundingBoxIn);
		
		return false;
	}

}
