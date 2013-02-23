package com.github.assisstion.spaceInvaders;

import java.io.IOException;

public class Bullet extends Sprite {
		private static final String[] BULLET_SHOT = 
			{"resources/placeholder.jpg", "resources/placeholder.jpg"};
		public int x=0;
		public int y=0;
		
		//private boolean isLethal = false;
		
		protected Bullet(){
		}
		
		//Constructor
		//Asks for the type of the bullet and the x&y values of the original launch point of the bullet. Implementation will come later.
		public Bullet(BulletType bulletType, int x, int y) throws IOException{
			//Calls the superclass constructor to automatically set up the player image
			super(BULLET_SHOT[bulletType.ordinal()]);
			this.x=x;
			this.y=y;
		}
		
		public static enum BulletType{
			PLAYER, ENEMY;
		}

}