package com.github.assisstion.spaceInvaders.menu.canvas;

import java.awt.Graphics;

public class MSButton extends MSAbstractBoundedComponent{
	
	private String text;
	private boolean fixedSize = false;
	
	protected MSButton(){
		
	}
	
	public MSButton(String text, int x, int y, int width, int height){
		this(text, x, y);
		this.width = width;
		this.height = height;
		fixedSize = true;
	}
	
	public MSButton(String text, int x, int y){
		super(x, y);
		this.text = text;
	}

	@Override
	public boolean addTo(MSContainer c){
		return false;
	}

	@Override
	public boolean removeFrom(MSContainer c){
		return false;
	}

	@Override
	public void render(Graphics g, int x, int y){
		g.drawRect(x, y, getWidth(), getHeight());
	}
	
	@Override
	public int getWidth(){
		if(fixedSize){
			return width;
		}
		return MSHelper.getTextWidth(text);
		
	}
	
	@Override
	public int getHeight(){
		if(fixedSize){
			return height;
		}
		return MSHelper.getTextHeight(text);
	}
}
