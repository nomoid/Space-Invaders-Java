package com.github.assisstion.spaceInvaders;

public class Bullet extends Sprite {
		private static final String[] BULLET_SHOT = 
			{"resources/Bullet.png", "resources/Bullet.png"};
		
		private static final boolean[] BULLET_MOVEMENT_MODE = 
			{true, true};
		private static final int[] BULLET_MOVEMENT_SPEED = 
			{2, 1};
		private static final BulletDirection[] BULLET_DIRECTION = 
			{BulletDirection.UP, BulletDirection.DOWN};
		//True means moves one pixel EVERY movementSpeed seconds
		//False means moves movementSpeed pixels EVERY second
		public boolean movementMode;
		public int movementSpeed;
		public int movementCounter;
		public BulletDirection direction;
		
		//private boolean isLethal = false;
		
		protected Bullet(){
		}
		
		//Constructor
		//Asks for the type of the bullet and the x&y values of the original launch point of the bullet. Implementation will come later.
		public Bullet(BulletType bulletType, int x, int y) throws GameException{
			//Calls the superclass constructor to automatically set up the player image
			super(BULLET_SHOT[bulletType.ordinal()]);
			this.x=x;
			this.y=y;
			movementMode = BULLET_MOVEMENT_MODE[bulletType.ordinal()];
			movementSpeed = BULLET_MOVEMENT_SPEED[bulletType.ordinal()];
			direction = BULLET_DIRECTION[bulletType.ordinal()];
		}
		
		public static enum BulletType{
			PLAYER, ENEMY;
		}
		
		public static enum BulletDirection{
			UP, DOWN;
		}
}