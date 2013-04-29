package com.github.assisstion.spaceInvaders.gameObject;

import com.github.assisstion.spaceInvaders.gameObject.Enemy.EnemyType;

public class Powerup extends Sprite{
	
	public static final int[] POWERUP_FRAMES = 
		{1000, 1200, 1400, 1600, 1800, 2000};
	private static String[] POWERUP_IMAGE = {LinkHolder.healthBoost,LinkHolder.firerateBoost,LinkHolder.powerBoost,LinkHolder.speedBoost};
	public static final int[][] POWERUP_CHANCES = {
		{40, 22, 32, 37, 40},
		{80, 45, 65, 72, 80},
		{160, 90, 130, 150, 160},
		{1500, 300, 600, 1000, 1500},
	};
	public static final int[] POWERUP_DROP_CHANCE = 
		{1500, 1300, 1100, 900, 700, 500};
	
	public static final EnemyType[] ENEMY_POWERUP_TABLE = {
		EnemyType.NORMAL, EnemyType.BLUE, EnemyType.RED, EnemyType.MOTHERSHIP
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