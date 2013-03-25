package com.github.assisstion.spaceInvaders.menu.canvas;

public abstract class MSAbstractComponent implements MSComponent{
	protected MSComponent parent;
	
	@Override
	public void setComponentParent(MSComponent component){
		parent = component;
	}
	
	@Override
	public MSComponent getComponentParent(){
		return parent;
	}
}
