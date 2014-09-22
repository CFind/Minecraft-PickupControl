package main.java.pickupcontrol.common.enums;

public enum PickupModes 
{
	AUTO,
	MANUAL,
	INDIVIDUAL;
	
	public PickupModes getNext() {
		return values()[(ordinal() + 1) % values().length];
	}

}
