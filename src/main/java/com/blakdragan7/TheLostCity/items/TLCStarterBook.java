package com.blakdragan7.TheLostCity.items;

import com.blakdragan7.TheLostCity.TheLostCityMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBook;

public class TLCStarterBook extends ItemBook
{
	public TLCStarterBook(String unlocalizedName) {
	    super();
	    this.setRegistryName(unlocalizedName);
	    this.setUnlocalizedName(TheLostCityMod.MODID + ":" + unlocalizedName);
	    this.setCreativeTab(CreativeTabs.MISC);
	}
}
