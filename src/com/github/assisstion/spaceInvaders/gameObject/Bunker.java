package com.github.assisstion.spaceInvaders.gameObject;

import java.io.FileNotFoundException;
import java.io.IOException;

import com.github.assisstion.spaceInvaders.GameException;
import com.github.assisstion.spaceInvaders.ResourceManager;

public class Bunker extends Sprite {
		
		public static final int BUNKER_DEFAULT_HEALTH = 300;
		//normal square
		private static final String BUNKER_A = "resources/Bunkers/BunkerA.png";
		private static final String BUNKER_A1 = "resources/Bunkers/BunkerA-1.png";
		private static final String BUNKER_A2 = "resources/Bunkers/BunkerA-2.png";
		//curved side to the left 
		private static final String BUNKER_B = "resources/Bunkers/BunkerB.png";
		private static final String BUNKER_B1 = "resources/Bunkers/BunkerB-1.png";
		private static final String BUNKER_B2 = "resources/Bunkers/BunkerB-2.png";
		//curved side to the right
		private static final String BUNKER_C = "resources/Bunkers/BunkerC.png";
		private static final String BUNKER_C1 = "resources/Bunkers/BunkerC-1.png";
		private static final String BUNKER_C2 = "resources/Bunkers/BunkerC-2.png";
		//curved side to the bottom right 
		private static final String BUNKER_D = "resources/Bunkers/BunkerD.png";
		private static final String BUNKER_D1 = "resources/Bunkers/BunkerD-1.png";
		private static final String BUNKER_D2 = "resources/Bunkers/BunkerD-2.png";
		//curved side to the bottom left
		private static final String BUNKER_E = "resources/Bunkers/BunkerE.png";
		private static final String BUNKER_E1 = "resources/Bunkers/BunkerE-1.png";
		private static final String BUNKER_E2 = "resources/Bunkers/BunkerE-2.png";
		
		public static final int BUNKER_SIZE = 16;
		
		public int health = BUNKER_DEFAULT_HEALTH;
		private int bunkerNum;
		public int originalBunker;
		public int lastImageUpdate = health;
		protected Bunker(){
			
		}
		
		//Constructor
		//Asks for BunkerID, The X value of the Bunker and the Y value. Implementation will come later.
		public Bunker(int bunkerNum, int bunkerX, int bunkerY) throws GameException{
			//Calls the superclass constructor to set up the image for THIS OBJECT
			super(getImageLinkFromBunkerNumber(bunkerNum), bunkerX, bunkerY);
			this.bunkerNum=bunkerNum;
			originalBunker = bunkerNum;
		}
		
		public static String getImageLinkFromBunkerNumber(int bunkerNum){
			switch(bunkerNum){
				case 0:
					return BUNKER_A;
				case 10:
					return BUNKER_A1;
				case 20:
					return BUNKER_A2;
				case 1:
					return BUNKER_B;
				case 11:
					return BUNKER_B1;
				case 21:
					return BUNKER_B2;
				case 2:
					return BUNKER_C;
				case 12:
					return BUNKER_C1;
				case 22:
					return BUNKER_C2;
				case 3:
					return BUNKER_D;
				case 13:
					return BUNKER_D1;
				case 23:
					return BUNKER_D2;
				case 4:
					return BUNKER_E;
				case 14:
					return BUNKER_E1;
				case 24:
					return BUNKER_E2;
				default:
					throw new IllegalArgumentException("Illegal Bunker Number: " + bunkerNum);
			}
		}
		
		public int getBunkerNum(){
			return bunkerNum;
		}
		
		public void setImage(int bunkerNum){
			this.bunkerNum=bunkerNum;
			try {
				image = ResourceManager.getImageResource(getImageLinkFromBunkerNumber(bunkerNum));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

}