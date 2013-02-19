package com.github.assisstion.spaceInvaders;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteTest {
	private String leimage = "resources/bob.jpg";
	private int x;
	private int y;
	private Image image;
	
	public SpriteTest() throws IOException{
		image = ImageIO.read(new FileInputStream(new File(leimage)));
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
