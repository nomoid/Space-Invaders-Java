package com.github.assisstion.spaceInvaders.menu.canvas;

import java.awt.Graphics;

public interface MSComponent{
	void paint(Graphics g);
	
	/*
	 * Note:
	 * This method should only be called by MSContainers
	 * This method should return true if the MSComponent is actually added
	 */
	boolean addTo(MSContainer c);
	
	/*
	 * Note:
	 * This method should only be called by MSContainers
	 * This method should return true if the MSComponent is actually removed
	 */
	boolean removeFrom(MSContainer c);
	
	/*
	 * Note:
	 * This method should only be called by MSContainers
	 */
	void setComponentParent(MSComponent c);
	
	MSComponent getComponentParent();
	
	int getX();
	
	int getY();
}
