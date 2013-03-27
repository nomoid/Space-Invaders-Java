package com.github.assisstion.spaceInvaders.menu.canvas;

import java.awt.Graphics;
import java.io.IOException;

import com.github.assisstion.spaceInvaders.ResourceManager;

public class MSSprite extends MSAbstractComponent{
	
	protected int x;
	protected int y;
	protected MSImage image;
	
	protected MSSprite(){
		
	}
	
	public MSSprite(int x, int y, String link) throws IOException{
		this(x, y, new MSImage(ResourceManager.getImageResource(link)));
	}
	
	public MSSprite(int x, int y, MSImage image){
		super(x, y);
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
