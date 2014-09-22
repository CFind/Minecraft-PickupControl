package main.java.pickupcontrol.common.players;

import main.java.pickupcontrol.common.enums.PickupModes;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class ExtendedPlayer implements IExtendedEntityProperties 
{
	
	EntityPlayer player;
	
	public boolean keyDown;
	public PickupModes mode;
	
	static final String PROP_NAME = "pickupControl";
	static final String KEY_DOWN_PROP = "keyDown";
	static final String MODE_PROP = "mode";
	
	public ExtendedPlayer(EntityPlayer player) 
	{
		this.player = player;
		keyDown = false;
		mode = PickupModes.MANUAL;
	}

	@Override
	public void saveNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound properties = new NBTTagCompound();
		
		properties.setBoolean(KEY_DOWN_PROP, keyDown);
		properties.setString(MODE_PROP, mode.name());
		
		compound.setTag(PROP_NAME, properties);
		
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) 
	{
		NBTTagCompound properties = compound.getCompoundTag(PROP_NAME);
		
		this.keyDown = properties.getBoolean(KEY_DOWN_PROP);
		this.mode = PickupModes.valueOf(properties.getString(MODE_PROP));
		
	}

	@Override
	public void init(Entity entity, World world) 
	{

	}
	
	public static boolean propertiesExist(EntityPlayer player)
	{
		if (player.getExtendedProperties(PROP_NAME) != null)
		{
			return true;
		}
		return false;
	}
	
	public static void register(EntityPlayer player)
	{
		player.registerExtendedProperties(PROP_NAME, new ExtendedPlayer(player));
	}
	
	public static boolean getPickupKeyDown(EntityPlayer player)
	{
		ExtendedPlayer playerProps = (ExtendedPlayer) player.getExtendedProperties(PROP_NAME);
		if (playerProps == null)
		{
			System.out.println("Player properties are null! Re-registering player.");
			ExtendedPlayer.register(player);
		}
		if (playerProps.keyDown)
		{
			return true;
		}
		else 
		{
			return false;
		}
	}
	
	public static PickupModes getPickupMode(EntityPlayer player)
	{
		return (((ExtendedPlayer) player.getExtendedProperties(PROP_NAME)).mode);
	}
	
	public static void setPickupKeyDown(EntityPlayer player, boolean keyDown)
	{
		((ExtendedPlayer)player.getExtendedProperties(PROP_NAME)).keyDown = keyDown;
	}
	
	public static void setPickupMode(EntityPlayer player, PickupModes mode)
	{
		((ExtendedPlayer)player.getExtendedProperties(PROP_NAME)).mode = mode;
	}
	
	public static void cycleMode(EntityPlayer player)
	{
		((ExtendedPlayer) player.getExtendedProperties(PROP_NAME)).mode = ((ExtendedPlayer) player.getExtendedProperties(PROP_NAME)).mode.getNext();
	}
	
	public static void togglePickupKey(EntityPlayer player)
	{
		((ExtendedPlayer) player.getExtendedProperties(PROP_NAME)).keyDown = !((ExtendedPlayer) player.getExtendedProperties(PROP_NAME)).keyDown;;
	}
}
