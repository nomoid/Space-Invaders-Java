package com.github.assisstion.spaceInvaders;

import java.io.IOException;

public class Player extends Sprite {
	
	private static final String PLAYER_DEFAULT_IMAGE = "resources/placeholder.jpg";
	private static final int DEFAULT_MOVEMENT_UPDATE_TICKS = 3;
	
	protected Player(){
		
	}
	
	//Constructor
	public Player(String name) throws IOException{
		//Calls the superclass constructor to automatically set up the player image
		super(PLAYER_DEFAULT_IMAGE);
		this.name=name;
		
	}
	
	private String name;
	//Made this public so other classes can easily access it
	public int score;
	//This is the amount of ticks (renders) per pixel of movement
	public int movementUpdateTicks = DEFAULT_MOVEMENT_UPDATE_TICKS;
	//These counts the ticks
	public int movementUpdateCounter;
	public Direction currentDirection=Direction.NONE;
	public int firingCooldown;
	
	public static enum Direction {
		LEFT,
		RIGHT,
		NONE
	}

	public String getName(){
		return name;
	}
	

}
