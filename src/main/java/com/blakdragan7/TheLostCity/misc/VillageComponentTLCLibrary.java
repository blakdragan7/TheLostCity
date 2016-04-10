package com.blakdragan7.TheLostCity.misc;

import java.util.Random;

import net.minecraft.block.BlockChest;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.gen.structure.StructureBoundingBox;
import net.minecraft.world.gen.structure.StructureVillagePieces;

public class VillageComponentTLCLibrary extends StructureVillagePieces.Village{

	public static VillageComponentTLCLibrary buildComponent(StructureVillagePieces.Start start,int x,int y,int z,EnumFacing facing)
	{
		int sizex = 4;
		int sizey = 3;
		int sizez = 4;
		
		StructureBoundingBox box = new StructureBoundingBox(x,y,z,x+sizex,y+sizey,z+sizez);
		
		VillageComponentTLCLibrary comp = new VillageComponentTLCLibrary(start,box,facing);
		
		return comp;
	}
	
	public VillageComponentTLCLibrary(StructureVillagePieces.Start start,StructureBoundingBox box, EnumFacing facing)
    {
        super(start, 12339);
        this.coordBaseMode = facing;
        this.boundingBox = box;
    }
	
	@Override
	public boolean addComponentParts(World worldIn, Random randomIn, StructureBoundingBox structureBoundingBoxIn) {
		
		if (this.field_143015_k < 0)
        {
            this.field_143015_k = this.getAverageGroundLevel(worldIn, structureBoundingBoxIn);

            if (this.field_143015_k < 0)
            {
                return true;
            }

            this.boundingBox.minY = this.field_143015_k;
        }
		
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 0, 4, 3, 4, Blocks.glass.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.fillWithBlocks(worldIn, structureBoundingBoxIn, 0, 0, 2, 0, 2 , 2 ,Blocks.air.getDefaultState(), Blocks.air.getDefaultState(), false);
		this.setBlockState(worldIn, Blocks.planks.getDefaultState(), 0, 0, 2, structureBoundingBoxIn);
		
		this.placeDoorCurrentPosition(worldIn, structureBoundingBoxIn, randomIn, 0, 1, 2, this.coordBaseMode);
		
		this.setBlockState(worldIn, Blocks.chest.getDefaultState(), 2, 1, 2, structureBoundingBoxIn);
		
		return false;
	}

}
