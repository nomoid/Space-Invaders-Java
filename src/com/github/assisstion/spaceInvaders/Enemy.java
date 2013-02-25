package com.github.assisstion.spaceInvaders;

public class Enemy extends Sprite{
	
	private static final int[] ENEMY_HEALTH = {100, 200, 300};
	private static final String[] ENEMY_IMAGE = {"resources/Enemy SpaceShip.png","resources/Enemy SpaceShip Red.png","resources/Enemy SpaceShip Blue.png"};
	//Note the 2D array, it is just an array of int[]'s
	//The first value is the min, the second value is the max
	private static final int[][] ENEMY_SHOOTING_COOLDOWN = {{450, 900}, {300, 600}, {200, 400}};
	
	public int health;
	public int xUpdateCounter;
	public int yUpdateCounter;
	public int shootingCounter;
	public int shootingCooldownMin;
	public int shootingCooldownMax;
	public EnemyType enemytype;
	
	protected Enemy(){
		
	}
	
	public Enemy(EnemyType type,int x, int y) throws GameException{
		super(ENEMY_IMAGE[type.ordinal()],x,y);
		int index = type.ordinal();
		health = ENEMY_HEALTH[index];
		enemytype=type;
		shootingCooldownMin = ENEMY_SHOOTING_COOLDOWN[type.ordinal()][0];
		shootingCooldownMax = ENEMY_SHOOTING_COOLDOWN[type.ordinal()][1];
		shootingCounter = MainCanvas.rand.nextInt(shootingCooldownMax - shootingCooldownMin) + shootingCooldownMin;
	}
	
	public static enum EnemyType{
		NORMAL,
		RED,
		BLUE,
		OTHER
	}
	
}
