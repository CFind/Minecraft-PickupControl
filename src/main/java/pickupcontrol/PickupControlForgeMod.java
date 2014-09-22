package main.java.pickupcontrol;

import main.java.pickupcontrol.common.packetHandler.ServerMessageHandler;
import main.java.pickupcontrol.common.packets.InputMessage;
import main.java.pickupcontrol.common.proxy.CommonProxy;
import main.java.pickupcontrol.common.servereventhandlers.EntityEventHandler;
import main.java.pickupcontrol.common.servereventhandlers.PickupEventHandler;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.relauncher.Side;

@Mod(modid="pickupcontrol",name="Pickup Control",version="0.1")

public class PickupControlForgeMod 
{
	public static final String MODID = "pickupcontrol";
	public static final String MODNAME = "Pickup Control";
	public static final String MODVERSION = "0.1";
	
	public static SimpleNetworkWrapper networkWrapper;
	
	@Mod.Instance(MODID)
	public static PickupControlForgeMod modInstance;
	
	@SidedProxy(clientSide="main.java.pickupcontrol.client.proxy.ClientProxy",serverSide="main.java.pickupcontrol.common.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event)
	{
		networkWrapper = NetworkRegistry.INSTANCE.newSimpleChannel(MODID);
		networkWrapper.registerMessage(ServerMessageHandler.class, InputMessage.class, 0, Side.SERVER);
		
		MinecraftForge.EVENT_BUS.register(new PickupEventHandler());
		MinecraftForge.EVENT_BUS.register(new EntityEventHandler());
		
		proxy.initialize();
	}
}
