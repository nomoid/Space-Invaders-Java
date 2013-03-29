package com.github.assisstion.MSToolkit;

import java.awt.Graphics;
import java.io.IOException;

public class MSSprite extends MSAbstractBoundedComponent{
	
	protected MSImage image;
	
	protected MSSprite(){
		
	}
	
	public MSSprite(int x, int y, String link) throws IOException{
		this(x, y, new MSImage(MSHelper.getImage(link)));
	}
	
	public MSSprite(int x, int y, MSImage image){
		super(x, y);
		width = image.getImage().getWidth();
		height = image.getImage().getHeight();
		this.image = image;
	}

	@Override
	public boolean addTo(MSContainer c){
		return true;
	}

	@Override
	public boolean removeFrom(MSContainer c){
		return true;
	}

	
	@Override
	public void render(Graphics g, int x, int y){
		image.render(g, x, y);
	}

	@Override
	public void render(Graphics g){
		image.render(g, x, y);
	}

}
