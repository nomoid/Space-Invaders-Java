package com.github.assisstion.MSToolkit;

import java.awt.Graphics;

import com.github.assisstion.MSToolkit.style.MSStyle;

public interface MSComponent extends MSRenderable{
	
	MSStyle getStyle();
	
	void render(Graphics g);
	
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
