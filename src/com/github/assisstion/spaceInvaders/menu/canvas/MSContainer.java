package com.github.assisstion.spaceInvaders.menu.canvas;

import java.util.Collection;

public interface MSContainer extends MSComponent{
	
	/*
	 * Returns and unmodifiable version of the components
	 * for other classes to use.
	 */
	Collection<MSComponent> getComponentView();
}