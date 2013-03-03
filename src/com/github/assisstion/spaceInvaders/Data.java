package com.github.assisstion.spaceInvaders;

import java.awt.Font;

public final class Data{
	public static final int MOTHERSHIP_CHANCE = 3000;
	public static final int MOTHERSHIP_SPEED = 4;
	public static final Font FONT_SMALL = new Font("Bank Gothic", Font.BOLD,
			33);
	public static final Font FONT_LARGE = new Font("Bank Gothic", Font.BOLD,
			70);
	public static final Font FONT_HUGE = new Font("Times New Roman",
			Font.BOLD, 110);
	public static final Font FONT_MEDIUM = new Font("Bank Gothic", Font.BOLD,
			50);
	public static final int NAME_MAX_LENGTH = 7;

	public static final int[][] LEVELS = { { 7, 4 }, { 9, 5 }, { 12, 6 },
			{ 13, 7 }, { 15, 8 } };
	public static final Enemy.EnemyType[] LEVEL1DATA = { Enemy.EnemyType.RED,
			Enemy.EnemyType.BLUE, Enemy.EnemyType.NORMAL,
			Enemy.EnemyType.NORMAL, Enemy.EnemyType.NORMAL };
	public static final Enemy.EnemyType[] LEVEL2DATA = { Enemy.EnemyType.RED,
			Enemy.EnemyType.RED, Enemy.EnemyType.BLUE, Enemy.EnemyType.BLUE,
			Enemy.EnemyType.BLUE, Enemy.EnemyType.NORMAL,
			Enemy.EnemyType.NORMAL };
	public static final Enemy.EnemyType[] LEVEL3DATA = { Enemy.EnemyType.RED,
			Enemy.EnemyType.RED, Enemy.EnemyType.RED, Enemy.EnemyType.BLUE,
			Enemy.EnemyType.BLUE, Enemy.EnemyType.BLUE, Enemy.EnemyType.NORMAL,
			Enemy.EnemyType.NORMAL };
	public static final Enemy.EnemyType[] LEVEL4DATA = { Enemy.EnemyType.RED,
			Enemy.EnemyType.RED, Enemy.EnemyType.RED, Enemy.EnemyType.RED,
			Enemy.EnemyType.BLUE, Enemy.EnemyType.BLUE, Enemy.EnemyType.BLUE,
			Enemy.EnemyType.BLUE, Enemy.EnemyType.BLUE, Enemy.EnemyType.NORMAL };
	public static final Enemy.EnemyType[] LEVEL5DATA = { Enemy.EnemyType.RED,
			Enemy.EnemyType.RED, Enemy.EnemyType.RED, Enemy.EnemyType.RED,
			Enemy.EnemyType.RED, Enemy.EnemyType.RED, Enemy.EnemyType.RED,
			Enemy.EnemyType.RED, Enemy.EnemyType.RED, Enemy.EnemyType.RED };
	public static final Powerup.PowerupType[] REWARDS_LIST = {
			Powerup.PowerupType.SPEED, Powerup.PowerupType.HEALTH,
			Powerup.PowerupType.BUNKER, Powerup.PowerupType.DAMAGE,
			Powerup.PowerupType.FIRERATE, Powerup.PowerupType.XTRALIFE,
			Powerup.PowerupType.STEROIDS };
	public static final int[] REWARDS_REQUIREMENTS = { 3, 6, 9, 12, 15, 18,
		20 };
}
