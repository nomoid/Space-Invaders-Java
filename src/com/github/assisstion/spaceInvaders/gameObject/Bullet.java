package com.github.assisstion.spaceInvaders.gameObject;

import com.github.assisstion.spaceInvaders.GameException;

public class Bullet extends Sprite {
		private static final String[] BULLET_SHOT = 
			{"resources/Bullet.png", "resources/GrayShot.png","resources/RedShot.png","resources/BlueShot.png","resources/EggBullet.png"};
		
		public static final int[] BULLET_MOVEMENT_SPEED = 
			{8, 4,4,4,8};
		public static final double[] BULLET_DIRECTION = 
			{0, 180, 180, 180,0};
		public static final int[] BULLET_DAMAGE = 
			{100, 100,250,150,100};
		//True means moves one pixel EVERY movementSpeed ticks
		//False means moves movementSpeed pixels EVERY tick
		public boolean movementMode;
		public int movementSpeed;
		public int movementCounter;
		public int damage;
		public double tempX;
		public double tempY;
		public Sprite owner;
		
		//private boolean isLethal = false;
		
		protected Bullet(){
		}
		
		//Constructor
		//Asks for the type of the bullet and the x&y values of the original launch point of the bullet.
		public Bullet(BulletType bulletType, int x, int y) throws GameException{
			//Calls the superclass constructor to automatically set up the player image
			super(BULLET_SHOT[bulletType.ordinal()]);
			this.x=x;
			this.y=y;
			tempX = x;
			tempY = y;
			movementSpeed = BULLET_MOVEMENT_SPEED[bulletType.ordinal()];
			rotation = BULLET_DIRECTION[bulletType.ordinal()];
			damage = BULLET_DAMAGE[bulletType.ordinal()];
		}
		
		
		public Bullet(BulletType bulletType, int x, int y, int damage, int movementSpeed) throws GameException{
			//Calls the superclass constructor to automatically set up the player image
			super(BULLET_SHOT[bulletType.ordinal()]);
			this.x=x;
			this.y=y;
			tempX = x;
			tempY = y;
			this.movementSpeed = movementSpeed;
			rotation = BULLET_DIRECTION[bulletType.ordinal()];
			this.damage = damage;
		}
		
		public static enum BulletType{
			PLAYER, NORMAL,RED,BLUE,EGG,PIXAR;
		}
		
		public void updateLocation(){
			x = (int) tempX;
			y = (int) tempY;
		}
}