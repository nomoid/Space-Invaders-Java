package com.github.assisstion.spaceInvaders.menu;

import java.awt.Canvas;

public abstract class AbstractCanvas extends Canvas{

	private static final long serialVersionUID = -8804355855785678123L;
	private boolean on = true;
	private int delay = 16;
	
	public AbstractCanvas(){
		super();
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
	 * This must be implemented in the subclass
	 * The subclass should return false if the class wants to
	 * stop the thread whenever an interrupted exception 
	 * is caught.
	 * The subclass should return true if the class
	 * wants to keep the thread going whenever an
	 * interrupted exception is caught.
	 */
	public abstract boolean interrupted(InterruptedException e);
	
}
