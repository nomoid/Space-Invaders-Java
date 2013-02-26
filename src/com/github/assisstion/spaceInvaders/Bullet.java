package com.github.assisstion.spaceInvaders;

public class Bullet extends Sprite {
		private static final String[] BULLET_SHOT = 
			{"resources/Bullet.png", "resources/GrayShot.png","resources/RedShot.png","resources/BlueShot.png"};
		
		private static final int[] BULLET_MOVEMENT_SPEED = 
			{8, 4,4,4};
		private static final BulletDirection[] BULLET_DIRECTION = 
			{BulletDirection.UP, BulletDirection.DOWN,BulletDirection.DOWN,BulletDirection.DOWN};
		private static final int[] BULLET_DAMAGE = 
			{100, 100,200,150};
		//True means moves one pixel EVERY movementSpeed ticks
		//False means moves movementSpeed pixels EVERY tick
		public boolean movementMode;
		public int movementSpeed;
		public int movementCounter;
		public int damage;
		public BulletDirection direction;
		
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
			movementSpeed = BULLET_MOVEMENT_SPEED[bulletType.ordinal()];
			direction = BULLET_DIRECTION[bulletType.ordinal()];
			damage = BULLET_DAMAGE[bulletType.ordinal()];
		}
		
		public static enum BulletType{
			PLAYER, NORMAL,RED,BLUE;
		}
		
		public static enum BulletDirection{
			UP, DOWN;
		}
}