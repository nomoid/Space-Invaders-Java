package com.github.assisstion.MSToolkit;

import com.github.assisstion.MSToolkit.style.MSStyle;
import com.github.assisstion.MSToolkit.style.MSStyleManager;

public abstract class MSAbstractComponent implements MSComponent{
	protected MSStyle style;
	protected int x;
	protected int y;
	
	protected MSAbstractComponent(){
		
	}
	
	public MSAbstractComponent(int x, int y){
		this.x = x;
		this.y = y;
		style = MSStyleManager.getDefaultStyleSystem().getEmpty();
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
	public void render(MSGraphicalContext g){
		render(g, getX(), getY());
	}
	
	@Override
	public MSStyle getStyle(){
		return style;
	}
	
	@Override
	public void setStyle(MSStyle style){
		this.style = style;
	}

	@Override
	public String getName(){
		return toString();
	}
}
