package main.java.pickupcontrol.client.proxy;

import main.java.pickupcontrol.client.controls.PickupControlsHandler;
import main.java.pickupcontrol.common.proxy.CommonProxy;
import cpw.mods.fml.common.FMLCommonHandler;

public class ClientProxy extends CommonProxy 
{

	@Override
	public void initialize()
	{
		FMLCommonHandler.instance().bus().register(new PickupControlsHandler());
		System.out.println("Client initialized");
	}
	
}
