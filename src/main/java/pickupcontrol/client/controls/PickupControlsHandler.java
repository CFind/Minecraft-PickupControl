package main.java.pickupcontrol.client.controls;

import main.java.pickupcontrol.PickupControlForgeMod;
import main.java.pickupcontrol.common.packets.InputMessage;
import net.minecraft.client.settings.KeyBinding;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent.KeyInputEvent;
import cpw.mods.fml.common.gameevent.TickEvent;


public class PickupControlsHandler 
{
	
	boolean pickupKeyPressed;
	
	public static KeyBinding pickupKeyBinding = new KeyBinding("key.pickup", Keyboard.KEY_F, "key.categories.inventory");
	public static KeyBinding toggleModeKeyBinding = new KeyBinding("key.togglemode", Keyboard.KEY_GRAVE, "key.categories.inventory");
	
	public PickupControlsHandler()
	{
		ClientRegistry.registerKeyBinding(pickupKeyBinding);
		ClientRegistry.registerKeyBinding(toggleModeKeyBinding);
		
	}
	
	
	@SubscribeEvent
	public void keyInput(KeyInputEvent event)
	{
		if (toggleModeKeyBinding.getIsKeyPressed())
		{
			PickupControlForgeMod.networkWrapper.sendToServer(new InputMessage(FMLClientHandler.instance().getClientPlayerEntity().getUniqueID(), pickupKeyPressed, true));
		}
	}
	
	@SubscribeEvent
	public void keyWatcher(TickEvent.ClientTickEvent event)
	{
		if (pickupKeyPressed)
		{
			if (!pickupKeyBinding.getIsKeyPressed())
			{
				pickupKeyPressed = false;
				PickupControlForgeMod.networkWrapper.sendToServer(new InputMessage(FMLClientHandler.instance().getClientPlayerEntity().getUniqueID(),pickupKeyPressed,false));
			}
		}
		else 
		{
			if (pickupKeyBinding.getIsKeyPressed())
			{
				pickupKeyPressed = true;
				PickupControlForgeMod.networkWrapper.sendToServer(new InputMessage(FMLClientHandler.instance().getClientPlayerEntity().getUniqueID(),pickupKeyPressed,false));
			}
		}
	}
	
}
