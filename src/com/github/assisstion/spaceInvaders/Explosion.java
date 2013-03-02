package com.github.assisstion.spaceInvaders;

public class Explosion extends Sprite{
	
	private static final String EXPLOSION_DEFAULT_IMAGE = "resources/Explosion.png";
	public static final int DEFAULT_EXPLOSION_TIMER = 15;
	
	public int explosionTimer;
	
	public Explosion(Sprite sprite) throws GameException{
		super(EXPLOSION_DEFAULT_IMAGE, sprite.x, sprite.y);
		explosionTimer = DEFAULT_EXPLOSION_TIMER;
	}

	protected Explosion(){
		
	}
}
