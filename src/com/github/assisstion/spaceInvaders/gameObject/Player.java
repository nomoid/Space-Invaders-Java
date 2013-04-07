package com.github.assisstion.spaceInvaders.gameObject;

import java.util.concurrent.ConcurrentSkipListMap;

import com.github.assisstion.spaceInvaders.GameException;
import com.github.assisstion.spaceInvaders.gameObject.Powerup.PowerupType;

public class Player extends Sprite {
	
	private static final String[] PLAYER_DEFAULT_IMAGE = {"resources/Spaceship.png","resources/EasterEgg.png"};
	public static final int PLAYER_DEFAULT_HEALTH = 500;
	public static final int PLAYER_DEFAULT_FIRING_COOLDOWN = 50;
	public int credits=0;
	
	protected Player(){
		
	}
	
	//Constructor
	public Player(int type,String name) throws GameException{
		//Calls the superclass constructor to automatically set up the player image
		super(PLAYER_DEFAULT_IMAGE[type], 432, 680);
		this.name=name.replaceFirst(name.toCharArray()[0] + "", (name.toCharArray()[0] + "").toUpperCase());
		this.powerups = new ConcurrentSkipListMap<PowerupType, Integer>();
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
	
	public static enum Direction {
		LEFT,
		RIGHT,
		NONE
	}

	public String getName(){
		return name;
	}
	
	
}
