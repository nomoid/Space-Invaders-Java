package com.github.assisstion.spaceInvaders;

import java.io.IOException;

public class Bullet extends Sprite {
		private static final String PLAYERSHOT = "resources/placeholder.jpg";
		private static final String ENEMYSHOT = "resources/placeholder.jpg";
		private int x=0;
		private int y=0;
		
		private boolean isLethal = false;
		
		protected Bullet(){
		}
		
		//Constructor
		//Asks for the type of the bullet and the x&y values of the original launch point of the bullet. Implementation will come later.
		public Bullet(int bullettype, int x, int y) throws IOException{
			//Calls the superclass constructor to automatically set up the player image
			super(leimage);
			this.x=x;
			this.y=y;
			String leimage=null;
			if (bullettype == 0){
				leimage = PLAYERSHOT;
			}
			else if (bullettype == 1){
				leimage = ENEMYSHOT;
			}
			//HOW TO FIX THIS 
		}

}