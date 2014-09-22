package main.java.pickupcontrol.common.packetHandler;



import java.util.List;

import main.java.pickupcontrol.common.packets.InputMessage;
import main.java.pickupcontrol.common.players.ExtendedPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ChatComponentText;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;

public class ServerMessageHandler implements IMessageHandler<InputMessage, IMessage> 
{
	@Override
	public IMessage onMessage(InputMessage message, MessageContext ctx) 
	{
		List<EntityPlayer> playerList = FMLCommonHandler.instance().getMinecraftServerInstance().getEntityWorld().playerEntities;
		
		System.out.println("Message recieved");
		
		for (EntityPlayer player : playerList)
		{
			if (player.getUniqueID().equals(message.playerUUID))
			{
				ExtendedPlayer.setPickupKeyDown(player, message.pickupKeyDown);
				if (message.pickupStateChanged)
				{
					ExtendedPlayer.cycleMode(player);
					player.addChatMessage(new ChatComponentText("Pickup mode changed to " + ExtendedPlayer.getPickupMode(player).toString().toLowerCase()));
				}
			}

		}


			
	
		return null;
	}

}
