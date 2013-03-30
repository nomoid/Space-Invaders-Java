package com.github.assisstion.MSToolkit;

import java.util.Collection;

import com.github.assisstion.MSToolkit.event.MSMouseHandler;

public interface MSContainer extends MSBoundedComponent, MSMouseHandler{
	
	/*
	 * Returns and unmodifiable version of the components
	 * for other classes to use.
	 */
	Collection<MSComponent> getComponentView();
}
