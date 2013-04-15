package com.github.assisstion.spaceInvaders.gameObject;

import com.github.assisstion.spaceInvaders.GameException;

public class Explosion extends Sprite{
	
	public static String[] EXPLOSION_DEFAULT_IMAGES = {LinkHolder.explosion,LinkHolder.playerExplosion};
	public static int[] DEFAULT_EXPLOSION_TIMERS= {15,150};
	
	public int explosionTimer;
	
	public Explosion(Sprite sprite,int type) throws GameException{
		super(EXPLOSION_DEFAULT_IMAGES[type], sprite.x, sprite.y);
		explosionTimer = DEFAULT_EXPLOSION_TIMERS[type];
	}

	protected Explosion(){
		
	}
}
