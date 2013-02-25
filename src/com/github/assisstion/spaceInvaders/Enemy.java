package com.github.assisstion.spaceInvaders;

public class Enemy extends Sprite{
	
	private static final int[] ENEMY_HEALTH = {};
	private static final String[] ENEMY_IMAGE = {};
	
	public int health;
	public int xUpdateCounter;
	public int yUpdateCounter;
	
	protected Enemy(){
		
	}
	
	public Enemy(EnemyType type) throws GameException{
		super(ENEMY_IMAGE[type.ordinal()]);
		int index = type.ordinal();
		health = ENEMY_HEALTH[index];
	}
	
	public static enum EnemyType{
		
	}
}
