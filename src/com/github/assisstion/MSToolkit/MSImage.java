package com.github.assisstion.MSToolkit;

import java.awt.image.BufferedImage;

public class MSImage implements MSRenderable{
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
	public void render(MSGraphicalContext g, int x, int y){
		g.drawImage(this, x, y);
	}
}
