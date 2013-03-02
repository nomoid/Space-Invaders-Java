package com.github.assisstion.spaceInvaders;

public class Explosion extends Sprite{
	
	private static final String[] EXPLOSION_DEFAULT_IMAGES = {"resources/Explosion.png","resources/Explosion 48*48.png"};
	public static int[] DEFAULT_EXPLOSION_TIMERS= {15,100};
	
	public int explosionTimer;
	
	public Explosion(Sprite sprite,int type,int duration) throws GameException{
		super(EXPLOSION_DEFAULT_IMAGES[type], sprite.x, sprite.y);
		explosionTimer = DEFAULT_EXPLOSION_TIMERS[type];
	}

	protected Explosion(){
		
	}
}
