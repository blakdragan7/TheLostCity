package com.blakdragan7.TheLostCity.blocks;

import com.blakdragan7.TheLostCity.TheLostCityMod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;

public class TLCGateBlock extends Block{

	public TLCGateBlock() {
		super(Material.FIRE);
		this.setRegistryName("TLCGate");
		this.setUnlocalizedName(TheLostCityMod.MODID + ":TLCGate");
		this.setCreativeTab(CreativeTabs.MISC);
		this.setHardness(2.0f);
        this.setResistance(10.0f);
	}
}
