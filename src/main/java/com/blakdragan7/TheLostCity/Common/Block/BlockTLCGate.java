package com.blakdragan7.TheLostCity.Common.Block;

import com.blakdragan7.TheLostCity.TheLostCityMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class BlockTLCGate extends Block{

	public BlockTLCGate() {
		super(Material.FIRE);
		this.setRegistryName("TLCGate");
		this.setUnlocalizedName(TheLostCityMod.MODID + ":TLCGate");
		this.setCreativeTab(CreativeTabs.MISC);
		this.setHardness(2.0f);
        this.setResistance(10.0f);
	}
}
