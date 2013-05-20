package com.github.assisstion.spaceInvaders;

import java.awt.Font;

import com.github.assisstion.spaceInvaders.gameObject.Enemy;
import com.github.assisstion.spaceInvaders.gameObject.Powerup;

public final class Data {
	
	//This class should not be instantiated
	private Data(){
		
	}
	
	
	public static final int MOTHERSHIP_CHANCE = 5000;
	public static final int MOTHERSHIP_SPEED = 4;
	public static final Font FONT_SMALL = new Font("Bank Gothic", Font.BOLD, 33);
	public static final Font FONT_LARGE = new Font("Bank Gothic", Font.BOLD, 70);
	public static final Font FONT_HUGE = new Font("Times New Roman", Font.BOLD,
			110);
	public static final Font FONT_MEDIUM = new Font("Bank Gothic", Font.BOLD,
			50);
	public static final int NAME_MAX_LENGTH = 7;

	public static final int[][] LEVELS = { { 7, 4 }, { 9, 5 }, { 12, 6 },
			{ 13, 7 }, { 15, 8 }, { 18, 10 }, {18, 10}};
	public static final Enemy.EnemyType[] LEVEL1DATA = { Enemy.EnemyType.NORMAL,
			Enemy.EnemyType.NORMAL, Enemy.EnemyType.NORMAL,
			Enemy.EnemyType.NORMAL, Enemy.EnemyType.NORMAL };



	public static final Enemy.EnemyType[] LEVEL2DATA = { Enemy.EnemyType.BLUE,
			Enemy.EnemyType.BLUE, Enemy.EnemyType.BLUE, Enemy.EnemyType.BLUE,
			Enemy.EnemyType.NORMAL, Enemy.EnemyType.NORMAL,
			Enemy.EnemyType.NORMAL };
	public static final Enemy.EnemyType[] LEVEL3DATA = { Enemy.EnemyType.RED,
			Enemy.EnemyType.RED, Enemy.EnemyType.BLUE, Enemy.EnemyType.BLUE,
			Enemy.EnemyType.BLUE, Enemy.EnemyType.NORMAL, Enemy.EnemyType.NORMAL,
			Enemy.EnemyType.NORMAL };
	public static final Enemy.EnemyType[] LEVEL4DATA = { Enemy.EnemyType.RED,
			Enemy.EnemyType.RED, Enemy.EnemyType.RED, Enemy.EnemyType.RED,
			Enemy.EnemyType.BLUE, Enemy.EnemyType.BLUE, Enemy.EnemyType.BLUE,
			Enemy.EnemyType.BLUE, Enemy.EnemyType.NORMAL, Enemy.EnemyType.NORMAL };
	public static final Enemy.EnemyType[] LEVEL5DATA = { Enemy.EnemyType.RED,
			Enemy.EnemyType.RED, Enemy.EnemyType.RED, Enemy.EnemyType.RED,
			Enemy.EnemyType.RED, Enemy.EnemyType.RED, Enemy.EnemyType.BLUE,
			Enemy.EnemyType.BLUE, Enemy.EnemyType.BLUE, Enemy.EnemyType.BLUE };
	
	public static final Enemy.EnemyType[] MINIGAMEDATA = { Enemy.EnemyType.RED,
		Enemy.EnemyType.RED, Enemy.EnemyType.RED, Enemy.EnemyType.RED,
		Enemy.EnemyType.RED, Enemy.EnemyType.RED, Enemy.EnemyType.RED,
		Enemy.EnemyType.RED, Enemy.EnemyType.RED, Enemy.EnemyType.RED };
	
	public static final Powerup.PowerupType[] REWARDS_LIST = {
			Powerup.PowerupType.SPEED, Powerup.PowerupType.HEALTH,
			Powerup.PowerupType.BUNKER, Powerup.PowerupType.DAMAGE,
			Powerup.PowerupType.FIRERATE, Powerup.PowerupType.XTRALIFE,
			Powerup.PowerupType.STEROIDS };
	public static final int[][] REWARDS_REQUIREMENTS = {
		{  5, 10, 15, 20, 25, 30, 40 },
		{  4,  8, 12, 16, 20, 25, 30 },
		{  4,  7, 10, 14, 18, 22, 25 },
		{  4,  7, 10, 13, 16, 19, 22 },
		{  3,  6,  9, 12, 15, 18, 21 },
		{  3,  5,  8, 10, 13, 16, 20 },
		};
	
	public static final String BULLET_SOUND = "resources/bulletsound.wav";
	
	public static final String DATA_LOCATION = "";
	public static final String DATA_LOCATION_FOLDER = "";
}
