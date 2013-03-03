package com.github.assisstion.spaceInvaders;

public class Enemy extends Sprite{
	
	private static final int[] ENEMY_HEALTH = {100, 200, 300, 100};
	private static final String[] ENEMY_IMAGE = {"resources/Enemy SpaceShip.png","resources/Enemy SpaceShip Blue.png","resources/Enemy SpaceShip Red.png","resources/Mothership.png"};
	//Note the 2D array, it is just an array of int[]'s
	//The first value is the min, the second value is the max
	private static final int[][] ENEMY_SHOOTING_COOLDOWN = {{1600, 2400}, {1800, 2600}, {2000, 2800}};
	private static final int[] ENEMY_DEATH_VALUES = {100,200,400,4000};
	public int health;
	public int shootingCounter;
	public int shootingCooldownMin;
	public int shootingCooldownMax;
	public EnemyType enemytype;
	public EnemySquad squad;
	public int scoreReward;
	public int startingX;
	public int startingY;
	
	protected Enemy(){
		
	}
	
	public Enemy(EnemyType type,int x, int y) throws GameException{
		super(ENEMY_IMAGE[type.ordinal()],x,y);
		enemytype=type;
		int index = type.ordinal();
		health = ENEMY_HEALTH[index];
		
		if (!enemytype.equals(EnemyType.MOTHERSHIP)){
		
		startingX=x;
		startingY=y;
		shootingCooldownMin = ENEMY_SHOOTING_COOLDOWN[index][0];
		shootingCooldownMax = ENEMY_SHOOTING_COOLDOWN[index][1];
		scoreReward = ENEMY_DEATH_VALUES[index];
		shootingCounter = MainCanvas.rand.nextInt(shootingCooldownMax);
		}
	}
	
	public static enum EnemyType{
		NORMAL,
		BLUE,
		RED,
		MOTHERSHIP
	}
}
