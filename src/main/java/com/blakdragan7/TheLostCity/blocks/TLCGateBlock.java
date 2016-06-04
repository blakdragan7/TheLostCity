package com.blakdragan7.TheLostCity.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class TLCGateBlock extends Block{

	public TLCGateBlock(String unlocal) {
		super(Material.fire);
		this.setUnlocalizedName(unlocal);
		this.setCreativeTab(CreativeTabs.tabMisc);
		this.setHardness(2.0f);
        this.setResistance(10.0f);
	}
	
	

}
