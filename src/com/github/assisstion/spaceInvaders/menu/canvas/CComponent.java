package com.github.assisstion.spaceInvaders.menu.canvas;

import java.awt.Graphics;

public abstract class CComponent{
	public abstract void paint(Graphics g);
	
	/*
	 * Note:
	 * This method should only be called by CContainers
	 */
	public abstract void addTo(CContainer c);
	
	/*
	 * Note:
	 * This method should only be called by CContainers
	 */
	public abstract void removeFrom(CContainer c);
}
