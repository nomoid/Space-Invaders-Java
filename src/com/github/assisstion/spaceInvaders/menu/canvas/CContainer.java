package com.github.assisstion.spaceInvaders.menu.canvas;

import java.util.Collection;

public interface CContainer{
	
	/*
	 * Returns and unmodifiable version of the components
	 * for other classes to use.
	 */
	Collection<CComponent> getComponentView();
}
