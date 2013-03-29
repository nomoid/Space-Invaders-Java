package com.github.assisstion.MSToolkit;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class MSImage implements MSRenderable, ImageObserver{
	protected BufferedImage image;
	
	protected MSImage(){
		
	}
	
	public MSImage(BufferedImage bi){
		image = bi;
	}
	
	public BufferedImage getImage(){
		return image;
	}

	@Override
	public void render(Graphics g, int x, int y){
		g.drawImage(getImage(), x, y, getImage().getWidth(), getImage().getHeight(), this);
	}

	@Override
	public final boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5){
		return false;
	}
}
