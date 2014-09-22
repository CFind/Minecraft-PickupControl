package main.java.pickupcontrol.common.servereventhandlers;

import main.java.pickupcontrol.common.enums.PickupModes;
import main.java.pickupcontrol.common.players.ExtendedPlayer;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Vec3;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;




public class PickupEventHandler 
{
	@SubscribeEvent
	public void OnPickupTrigger(EntityItemPickupEvent event)
	{
		EntityPlayer player = event.entityPlayer;
		EntityItem item = event.item;
		
		boolean shouldPickup = ExtendedPlayer.getPickupKeyDown(player);
		PickupModes mode = ExtendedPlayer.getPickupMode(player);
	
		switch (mode) 
		{
			case AUTO:
			{
				return;
			}
			case MANUAL:
			{
				if (checkPlayerHasItem(item, player))
				{
					return;
				}
				if (shouldPickup)
				{
					return;
				}
			}
			case INDIVIDUAL:
			{
				if (checkPlayerHasItem(item, player))
				{
					return;
				}
				if (shouldPickup && isPlayerLookingAt(item, player))
				{
					return;
				}
			}
		}
		event.setCanceled(true);
	}
	
	

	static boolean isPlayerLookingAt(EntityItem item, EntityPlayer player) 
	{
		Vec3 vec3 = player.getLook(1.0F).normalize();
		Vec3 vec31 =  Vec3.createVectorHelper(item.posX - item.posX, item.boundingBox.minY + (double)(item.height) - (item.posY + (double)player.getEyeHeight()), item.posZ - player.posZ);
		double d0 = vec31.lengthVector();
		vec31 = vec31.normalize();
		double d1 = vec3.dotProduct(vec31);
		return d1 > 1.0D - 0.025D / d0 ? true : false;
	}

	static boolean checkPlayerHasItem(EntityItem item, EntityPlayer player) 
	{
		InventoryPlayer pInventory = player.inventory;

		for (ItemStack iStack : pInventory.mainInventory) 
		{
			if (iStack != null)
			{
				if (iStack.isItemEqual(item.getEntityItem())&& iStack.getMaxStackSize() > iStack.stackSize && iStack.stackSize + item.getEntityItem().stackSize <= iStack.getMaxStackSize()) 
				{
					return true;
				}
			}
		}
		return false;
	}

}
