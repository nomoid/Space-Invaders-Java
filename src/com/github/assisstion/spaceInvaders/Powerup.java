package com.github.assisstion.spaceInvaders;

public class Powerup extends Sprite{
	
	public static final int DEFAULT_POWERUP_FRAMES = 625;
	private static final String[] POWERUP_IMAGE = {"resources/HealthBoost.png","resources/FirerateBoost.png","resources/PowerBoost.png","resources/SpeedBoost.png"};
	
	public PowerupType type;
	public int movementSpeed=3;
	
	public Powerup(PowerupType type, int x, int y){
		super(POWERUP_IMAGE[type.ordinal()]);
		this.type = type;
		this.x=x;
		this.y=y;
	}
	
	public enum PowerupType{
		HEALTH,
		FIRERATE,
		DAMAGE,
		SPEED;
	}
}