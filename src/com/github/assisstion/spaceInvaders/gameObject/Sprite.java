package com.github.assisstion.spaceInvaders.gameObject;

import java.awt.image.BufferedImage;
import java.io.IOException;

import com.github.assisstion.spaceInvaders.GameException;
import com.github.assisstion.spaceInvaders.ResourceHolder;

/**
 * A sprite class for loading and keeping track of images
 * Subclasses of this class may be used as game objects
 * @author Markus Feng
 * @author Michael Man
 */
public class Sprite implements Comparable<Sprite>{
	
	private static int entityIDCounter = 0;
	private static Object entityIDLock = new Object();
	
	/*
	 * These are public so they can be easily used or modified from other sources
	 */
	public int x;
	public int y;
	public double rotation;
	public Box hitBox;
	//These are protected so subclasses can override this
	protected BufferedImage image;
	protected String imageLink;
	//Entity id makes it easy to compare entities
	private int entityID;
	
	/*
	 * Creates a sprite from an image link
	 */
	public Sprite(String imageLink) throws GameException{
		this(imageLink, 0, 0);
	}
	
	public Sprite(String imageLink, int x, int y) throws GameException{
		this();
		this.imageLink = imageLink;
		try{
			image = ResourceHolder.getImageResource(imageLink);
		}
		catch(IOException e){
			throw new GameException("Error creating " + getClass().getName() + ": " + getEntityID(), e);
		}
		//Temporarily sets the x and y to 0
		this.x = x;
		this.y = y;
		rotation = 0;
		hitBox = new Box(x, y, image.getWidth(), image.getHeight(), true);
	}
	
	protected Sprite(){
		synchronized(entityIDLock){
			entityID = entityIDCounter++;
		}
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public String getImageLink(){
		return imageLink;
	}
	
	public int getEntityID(){
		return entityID;
	}
	
	@Override
	public boolean equals(Object o){
		if(o instanceof Sprite){
			Sprite s = (Sprite) o;
			if(s.getEntityID() == getEntityID()){
				return true;
			}
		}
		return false;
	}
	
	@Override
	public int hashCode(){
		return getEntityID();
	}

	@Override
	public int compareTo(Sprite s){
		return new Integer(getEntityID()).compareTo(s.getEntityID());
	}
}
