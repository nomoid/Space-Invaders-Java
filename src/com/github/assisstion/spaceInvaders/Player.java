package com.github.assisstion.spaceInvaders;

import java.util.concurrent.ConcurrentSkipListMap;

import com.github.assisstion.spaceInvaders.Powerup.PowerupType;

public class Player extends Sprite {
	
	private static final String PLAYER_DEFAULT_IMAGE = "resources/SpaceShip.png";
	public static final int PLAYER_DEFAULT_HEALTH = 500;
	public static final int PLAYER_DEFAULT_FIRING_COOLDOWN = 30;
	
	protected Player(){
		
	}
	
	//Constructor
	public Player(String name) throws GameException{
		//Calls the superclass constructor to automatically set up the player image
		super(PLAYER_DEFAULT_IMAGE, 432, 680);
		this.name=name;
		this.powerups = new ConcurrentSkipListMap<PowerupType, Integer>();
	}
	
	private String name;
	//Made this public so other classes can easily access it
	public int score;
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
