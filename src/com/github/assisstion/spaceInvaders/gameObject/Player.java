package com.github.assisstion.spaceInvaders.gameObject;

import java.util.concurrent.ConcurrentSkipListMap;

import com.github.assisstion.spaceInvaders.GameException;
import com.github.assisstion.spaceInvaders.gameObject.Bullet.BulletType;
import com.github.assisstion.spaceInvaders.gameObject.Powerup.PowerupType;
import com.github.assisstion.spaceInvaders.menu.canvas.UpgradeType;
import com.github.assisstion.spaceInvaders.menu.canvas.Upgrades;

public class Player extends Sprite {
	
	public static String[] PLAYER_DEFAULT_IMAGE = {LinkHolder.player,"resources/EasterEgg.png"};
	public static final int PLAYER_DEFAULT_HEALTH = 500;
	public static final int PLAYER_DEFAULT_FIRING_COOLDOWN = 50;
	//public int credits=0;
	
	protected Player(){
		
	}
	
	//Constructor
	public Player(int type,String name, Upgrades upgrades) throws GameException{
		//Calls the superclass constructor to automatically set up the player image
		super(PLAYER_DEFAULT_IMAGE[type], 432, 680);
		this.name=name.replaceFirst(name.toCharArray()[0] + "", (name.toCharArray()[0] + "").toUpperCase());
		this.powerups = new ConcurrentSkipListMap<PowerupType, Integer>();
		this.upgrades = upgrades;
	}
	
	private String name;
	//Made this public so other classes can easily access it
	public int score;
	public int levelScore;
	public Direction currentDirection=Direction.NONE;
	public int firingCooldown;
	public int health=PLAYER_DEFAULT_HEALTH;
	public int livesRemaining=3;
	public ConcurrentSkipListMap<PowerupType, Integer> powerups;
	public Upgrades upgrades;
	
	public static enum Direction {
		LEFT,
		RIGHT,
		NONE
	}

	public String getName(){
		return name;
	}
	
	public int getFiringCooldown(){
		double d = PLAYER_DEFAULT_FIRING_COOLDOWN;
		if(powerups.containsKey(PowerupType.FIRERATE)){
			d /= 3;
		}
		int i = upgrades.getUpgrade(UpgradeType.PLAYER_FIRERATE);
		d *= Math.pow(0.9, i);
		return (int) d;
	}
	
	public double getBulletMovementSpeed(){
		double d = Bullet.BULLET_MOVEMENT_SPEED[BulletType.PLAYER.ordinal()];
		d *= Math.pow(10.0/9.0, upgrades.getUpgrade(UpgradeType.BULLET_SPEED));
		if(powerups.containsKey(PowerupType.SPEED)){
			d *= 1.75;
		}
		return d;
	}

	public int getBulletDamage(){
		double d = Bullet.BULLET_DAMAGE[BulletType.PLAYER.ordinal()];
		if(powerups.containsKey(PowerupType.DAMAGE)){
			d *= 3;
		}
		int i = upgrades.getUpgrade(UpgradeType.BULLET_DAMAGE);
		d *= Math.pow(1.25, i);
		return (int) d;
	}
	
	public double getMovementSpeed(){
		double d = 1.0;
		if (powerups.containsKey(PowerupType.SPEED)) {
			d *= 3;
		}
		int i = upgrades.getUpgrade(UpgradeType.PLAYER_SPEED);
		d *= Math.pow(10.0/9.0, i);
		return d;
	}
}
