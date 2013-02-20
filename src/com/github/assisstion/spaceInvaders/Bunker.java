package com.github.assisstion.spaceInvaders;

import java.awt.Point;
import java.io.IOException;

public class Bunker extends Sprite {
		private static final String PLAYER_DEFAULT_IMAGE = "resources/placeholder.jpg";
		
		//normal square
		private static final String BunkerA = "resources/placeholder.jpg";
		//curved side to the left 
		private static final String BunkerB = "resources/placeholder.jpg";
		//curved side to the right
		private static final String BunkerC = "resources/placeholder.jpg";
		//curved side to the bottom right 
		private static final String BunkerD = "resources/placeholder.jpg";
		//curved side to the bottom left
		private static final String BunkerE = "resources/placeholder.jpg";
		
		
		private int BunkerNum;
		private int BunkerX;
		private int BunkerY;
		protected Bunker(){
		
			
		}
		
		//Constructor
		//Asks for BunkerID, The X value of the Bunker and the Y value. Implementation will come later.
		public Bunker(int BunkerNum, int BunkerX, int BunkerY) throws IOException{
			//Calls the superclass constructor to automatically set up the player image
			super(PLAYER_DEFAULT_IMAGE);
			this.BunkerNum=BunkerNum;
			
		}

}