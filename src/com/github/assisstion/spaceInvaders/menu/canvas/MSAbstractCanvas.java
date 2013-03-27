package com.github.assisstion.spaceInvaders.menu.canvas;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public abstract class MSAbstractCanvas extends Canvas implements MSContainer{

	private static final long serialVersionUID = -8804355855785678123L;
	private boolean on = true;
	private int delay = 16;
	private LinkedList<MSComponent> components;
	private Object listLock = new Object();
	private CollectionSynchronizer<MSComponent> cs;
	private MSComponent parent;
	
	public MSAbstractCanvas(){
		components = new LinkedList<MSComponent>();
		cs = new CollectionSynchronizer<MSComponent>(components, listLock);
	}
	
	public boolean isOn(){
		return on;
	}
	
	public void setOn(boolean b){
		on = b;
	}

	public int getDelay(){
		return delay;
	}
	
	public void setDelay(int i){
		delay = i;
	}
	
	/*
	 * Subclasses of this should call super.paint(g);
	 */
	@Override
	public void paint(Graphics g){
		render(g);
	}
	
	@Override
	public void render(Graphics g){
		render(g, getX(), getY());
	}
	
	/*
	 * Subclasses of this should call super.render(g);
	 */
	@Override
	public void render(Graphics g, int x, int y){
		for(MSComponent component : components){
			component.render(g, x, y);
		}
	}
	
	/*
	 * This must be implemented in the subclass
	 * The subclass should return false if the class wants to
	 * stop the thread whenever an interrupted exception 
	 * is caught.
	 * The subclass should return true if the class
	 * wants to keep the thread going whenever an
	 * interrupted exception is caught.
	 */
	public abstract boolean interrupted(InterruptedException e);
	
	@Override
	public Collection<MSComponent> getComponentView(){
		synchronized(listLock){
			return Collections.unmodifiableCollection(components);
		}
	}
	
	public void addComponent(MSComponent component){
		component.addTo(this);
		cs.add(component);
		component.setComponentParent(this);
		requestFocus();
		validate();
		repaint();
	}
	
	public void removeComponent(MSComponent component){
		if(component.removeFrom(this)){
			cs.remove(component);
			component.setComponentParent(null);
			requestFocus();
			validate();
			repaint();
		}
	}
	
	@Override
	public boolean addTo(MSContainer container){
		return true;
	}
	
	@Override
	public boolean removeFrom(MSContainer container){
		return true;
	}
	
	@Override
	public void setComponentParent(MSComponent component){
		parent = component;
	}
	
	@Override
	public MSComponent getComponentParent(){
		return parent;	
	}
}
