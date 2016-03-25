package com.blakdragan7.TheLostCity.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class TLCLibrarySideBlock extends Block{

	public TLCLibrarySideBlock(String unlocal) {
		super(Material.wood);
		this.setUnlocalizedName(unlocal);
		this.setCreativeTab(CreativeTabs.tabMisc);
		// TODO Auto-generated constructor stub
	}

}
