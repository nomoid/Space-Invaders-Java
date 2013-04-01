package com.github.assisstion.MSToolkit;

import com.github.assisstion.MSToolkit.style.MSStyle;

public interface MSComponent extends MSRenderable{
	
	MSStyle getStyle();
	
	void render(MSGraphicalContext g);
	
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
	
	int getX();
	
	int getY();
}
