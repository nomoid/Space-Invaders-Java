package com.github.assisstion.spaceInvaders;

public class Enemy extends Sprite{
	
	private static final int[] ENEMY_HEALTH = {100, 200, 300};
	private static final String[] ENEMY_IMAGE = {"resources/Enemy SpaceShip.png","resources/Enemy SpaceShip Red.png","resources/Enemy SpaceShip Blue.png"};
	
	public int health;
	public int xUpdateCounter;
	public int yUpdateCounter;
	
	protected Enemy(){
		
	}
	
	public Enemy(EnemyType type,int x, int y) throws GameException{
		super(ENEMY_IMAGE[type.ordinal()],x,y);
		int index = type.ordinal();
		health = ENEMY_HEALTH[index];
	}
	
	public static enum EnemyType{
		NORMAL,
		RED,
		BLUE,
		OTHER
	}
}
