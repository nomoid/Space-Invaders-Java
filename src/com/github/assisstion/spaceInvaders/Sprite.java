package com.github.assisstion.spaceInvaders;

import java.awt.Image;
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
	//These are protected so subclasses can override this
	protected Image image;
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
	}
	
	public Sprite(String imageLink, int x, int y) throws IOException{
		//Reads the image provided by the image link file
		this.imageLink = imageLink;
		image = ImageIO.read(new FileInputStream(new File(imageLink)));
		this.x = x;
		this.y = y;
	}
	
	protected Sprite(){
		
	}
	
	public Image getImage() {
		return image;
	}
	
	public String getImageLink(){
		return imageLink;
	}
}
