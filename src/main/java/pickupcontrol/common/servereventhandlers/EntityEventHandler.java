package main.java.pickupcontrol.common.servereventhandlers;

import main.java.pickupcontrol.common.players.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraftforge.event.entity.EntityEvent.EntityConstructing;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class EntityEventHandler 
{
	@SubscribeEvent
	public void onEntityConstructing(EntityConstructing event)
	{
		if ((event.entity instanceof EntityPlayer) && !ExtendedPlayer.propertiesExist((EntityPlayer)event.entity))
		{
			ExtendedPlayer.register((EntityPlayer) event.entity);
		}
	}

}
