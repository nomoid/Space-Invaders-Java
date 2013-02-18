package com.github.assisstion.spaceInvaders;

import java.awt.Image;

import javax.swing.ImageIcon;

public class SpriteTest {
	private String leimage = "bob.jpg";
	private int x;
	private int y;
	private Image image;
	
	public SpriteTest() {
		ImageIcon imageicon = new ImageIcon(this.getClass().getResource(leimage));
		image = imageicon.getImage();
		x=50;
		y=50;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public Image getImage() {
		return image;
	}
}
