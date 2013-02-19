package com.github.assisstion.spaceInvaders;

import java.awt.Image;
import java.io.IOException;

public class Player extends Sprite {

	//Constructor
	public Player(String name){
		this.name=name;
	}
	
	private String name;
	private int score;
	private Image playerSprite;
	public Direction currentDirection=null;
	
	public static enum Direction {
		LEFT,
		RIGHT,
	}

	public String getName(){
		return name;
	}
	

}
