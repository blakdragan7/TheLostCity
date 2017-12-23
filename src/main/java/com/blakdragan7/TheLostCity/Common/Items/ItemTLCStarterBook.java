package com.blakdragan7.TheLostCity.Common.Items;

import com.blakdragan7.TheLostCity.TheLostCityMod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBook;

public class ItemTLCStarterBook extends ItemBook
{
	public ItemTLCStarterBook(String unlocalizedName) {
	    super();
	    this.setRegistryName(unlocalizedName);
	    this.setUnlocalizedName(TheLostCityMod.MODID + ":" + unlocalizedName);
	    this.setCreativeTab(CreativeTabs.MISC);
	}
}
