package com.github.assisstion.spaceInvaders;

import java.io.IOException;

public class Player extends Sprite {
	
	private static final String PLAYER_DEFAULT_IMAGE = null;

	//Constructor
	public Player(String name) throws IOException{
		//Calls the superclass constructor to automatically set up the player image
		super(PLAYER_DEFAULT_IMAGE);
		this.name=name;
		
	}
	
	private String name;
	//Made this public so other classes can easily access it
	public int score;
	public Direction currentDirection=null;
	
	public static enum Direction {
		LEFT,
		RIGHT,
	}

	public String getName(){
		return name;
	}
	

}
