package com.github.assisstion.spaceInvaders;

public class Powerup extends Sprite{
	
	private static final String[] POWERUP_IMAGE = {"resources/HealthBoost.png"};
	
	public PowerupType type;
	public int movementSpeed=3;
	
	public Powerup(PowerupType type){
		super(POWERUP_IMAGE[type.ordinal()]);
		this.type = type;
	}
	
	public enum PowerupType{
		HEALTH,
		FIRERATE,
		DAMAGE,
		SPEED;
	}
}