package com.github.assisstion.spaceInvaders.menu.canvas;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

public abstract class AbstractCanvas extends Canvas implements CContainer{

	private static final long serialVersionUID = -8804355855785678123L;
	private boolean on = true;
	private int delay = 16;
	private LinkedList<CComponent> components;
	private Object listLock = new Object();
	private CollectionSynchronizer<CComponent> cs;
	
	public AbstractCanvas(){
		components = new LinkedList<CComponent>();
		cs = new CollectionSynchronizer<CComponent>(components, listLock);
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
	 * Subclasses of this should call super.paint();
	 */
	@Override
	public void paint(Graphics g){
		for(CComponent component : components){
			component.paint(g);
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
	public Collection<CComponent> getComponentView(){
		synchronized(listLock){
			return Collections.unmodifiableCollection(components);
		}
	}
	
	public void addComponent(CComponent component){
		cs.add(component);
		component.addTo(this);
		requestFocus();
		validate();
		repaint();
	}
	
	public void removeComponent(CComponent component){
		cs.remove(component);
		component.removeFrom(this);
		requestFocus();
		validate();
		repaint();
	}
}
