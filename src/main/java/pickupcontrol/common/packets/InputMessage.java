package main.java.pickupcontrol.common.packets;

import java.util.UUID;

import io.netty.buffer.ByteBuf;
import cpw.mods.fml.common.network.ByteBufUtils;
import cpw.mods.fml.common.network.simpleimpl.IMessage;

public class InputMessage implements IMessage 
{
	
	public boolean pickupKeyDown;
	public boolean pickupStateChanged;
	
	public UUID playerUUID;

	public InputMessage() {}
	
	public InputMessage(UUID playerUUID, boolean pickupKeyStateChanged, boolean pickupStateChanged)
	{
		this.pickupKeyDown = pickupKeyStateChanged;
		this.pickupStateChanged = pickupStateChanged;
		this.playerUUID = playerUUID;
	}
	
	@Override
	public void fromBytes(ByteBuf buf) 
	{
		this.pickupKeyDown = buf.readBoolean();
		this.pickupStateChanged = buf.readBoolean();
		
		playerUUID = UUID.fromString(ByteBufUtils.readUTF8String(buf));
		
		System.out.println("Message read.");
	}

	@Override
	public void toBytes(ByteBuf buf) 
	{
		buf.writeBoolean(pickupKeyDown);
		buf.writeBoolean(pickupStateChanged);
		
		ByteBufUtils.writeUTF8String(buf, playerUUID.toString());
		
		System.out.println("Message Encoded");
		

	}

}
