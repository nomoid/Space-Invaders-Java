package com.github.assisstion.spaceInvaders.menu.canvas;

import java.awt.Graphics;

public abstract class MSAbstractComponent implements MSComponent{
	protected MSComponent parent;
	protected int x;
	protected int y;
	
	protected MSAbstractComponent(){
		
	}
	
	public MSAbstractComponent(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	@Override
	public void setComponentParent(MSComponent component){
		parent = component;
	}
	
	@Override
	public MSComponent getComponentParent(){
		return parent;
	}
	
	@Override
	public int getX(){
		return x;
	}

	@Override
	public int getY(){
		return y;
	}
	
	@Override
	public void render(Graphics g){
		render(g, getX(), getY());
	}

}
