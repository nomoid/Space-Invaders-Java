package com.github.assisstion.MSToolkit;

import java.awt.Graphics;

import com.github.assisstion.MSToolkit.style.MSStyle;
import com.github.assisstion.MSToolkit.style.MSStyleManager;

public abstract class MSAbstractComponent implements MSComponent{
	protected MSComponent parent;
	protected MSStyle style;
	protected int x;
	protected int y;
	
	protected MSAbstractComponent(){
		
	}
	
	public MSAbstractComponent(int x, int y){
		this.x = x;
		this.y = y;
		style = MSStyleManager.getEmptyStyle();
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
	
	@Override
	public MSStyle getStyle(){
		return style;
	}

}
