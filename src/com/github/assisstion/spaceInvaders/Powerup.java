package com.github.assisstion.spaceInvaders;

import com.github.assisstion.spaceInvaders.Enemy.EnemyType;

public class Powerup extends Sprite{
	
	public static final int DEFAULT_POWERUP_FRAMES = 1250;
	private static final String[] POWERUP_IMAGE = {"resources/HealthBoost.png","resources/FirerateBoost.png","resources/PowerBoost.png","resources/SpeedBoost.png"};
	public static final int[][] POWERUP_CHANCES = {
		{40, 20, 30, 35, 40},
		{80, 40, 60, 70, 80},
		{160, 90, 120, 145, 160},
	};
	
	
	public static final EnemyType[] ENEMY_POWERUP_TABLE = {
		EnemyType.NORMAL, EnemyType.BLUE, EnemyType.RED
	};
	
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
		SPEED,
		BUNKER,
		XTRALIFE,
		STEROIDS,
	}
}