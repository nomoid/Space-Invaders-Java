package com.github.assisstion.spaceInvaders;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * A sprite class for loading and keeping track of images
 * Subclasses of this class may be used as game objects
 * @author Markus Feng
 * @author Michael Man
 */
public class Sprite {
	
	/*
	 * These are public so they can be easily used or modified from other sources
	 */
	public int x;
	public int y;
	public Box hitBox;
	//These are protected so subclasses can override this
	protected BufferedImage image;
	protected String imageLink;
	
	/*
	 * Creates a sprite from an image link
	 */
	public Sprite(String imageLink) throws IOException{
		this.imageLink = imageLink;
		image = ImageIO.read(new FileInputStream(new File(imageLink)));
		//Temporarily sets the x and y to 0
		x = 0;
		y = 0;
		hitBox = new Box(x, y, image.getWidth(), image.getHeight());
	}
	
	public Sprite(String imageLink, int x, int y) throws IOException{
		//Reads the image provided by the image link file
		this.imageLink = imageLink;
		image = ImageIO.read(new FileInputStream(new File(imageLink)));
		this.x = x;
		this.y = y;
		hitBox = new Box(x, y, image.getWidth(), image.getHeight());
	}
	
	protected Sprite(){
		
	}
	
	public BufferedImage getImage() {
		return image;
	}
	
	public String getImageLink(){
		return imageLink;
	}
}
