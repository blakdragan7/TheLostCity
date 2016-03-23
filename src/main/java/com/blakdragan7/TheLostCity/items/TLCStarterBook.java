package com.blakdragan7.TheLostCity.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBook;

public class TLCStarterBook extends ItemBook
{
	public TLCStarterBook(String unlocalizedName) {
	    super();

	    this.setUnlocalizedName(unlocalizedName);
	    this.setCreativeTab(CreativeTabs.tabMisc);
	}
}
