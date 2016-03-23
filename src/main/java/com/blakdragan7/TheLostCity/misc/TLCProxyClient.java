package com.blakdragan7.TheLostCity.misc;

import com.blakdragan7.TheLostCity.TheLostCityMod;
import com.blakdragan7.TheLostCity.items.TLCItemLoader;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.item.Item;

public class TLCProxyClient extends TLCProxy{

	public static final String ClientProxyName = "com.blakdragan7.TheLostCity.misc.TLCProxyClient";
	@Override
	public void SidedEvent() {
		TLCItemLoader.registerAllRenders();
	}
}
