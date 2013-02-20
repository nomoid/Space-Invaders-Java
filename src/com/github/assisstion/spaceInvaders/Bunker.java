package com.github.assisstion.spaceInvaders;

import java.io.IOException;

public class Bunker extends Sprite {
		
		//normal square
		private static final String BUNKER_A = "resources/placeholder.jpg";
		//curved side to the left 
		private static final String BUNKER_B = "resources/placeholder.jpg";
		//curved side to the right
		private static final String BUNKER_C = "resources/placeholder.jpg";
		//curved side to the bottom right 
		private static final String BUNKER_D = "resources/placeholder.jpg";
		//curved side to the bottom left
		private static final String BUNKER_E = "resources/placeholder.jpg";
		
		private int bunkerNum;
		
		protected Bunker(){
		
			
		}
		
		//Constructor
		//Asks for BunkerID, The X value of the Bunker and the Y value. Implementation will come later.
		public Bunker(int bunkerNum, int bunkerX, int bunkerY) throws IOException{
			//Calls the superclass constructor to set up the image for THIS OBJECT
			super(getImageLinkFromBunkerNumber(bunkerNum), bunkerX, bunkerY);
			this.bunkerNum=bunkerNum;
		}
		
		public static String getImageLinkFromBunkerNumber(int bunkerNum){
			switch(bunkerNum){
				case 0:
					return BUNKER_A;
				case 1:
					return BUNKER_B;
				case 2:
					return BUNKER_C;
				case 3:
					return BUNKER_D;
				case 4:
					return BUNKER_E;
				default:
					throw new IllegalArgumentException("Illegal Bunker Number");
			}
		}
		
		public int getBunkerNum(){
			return bunkerNum;
		}

}