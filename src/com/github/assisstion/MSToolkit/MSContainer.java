package com.github.assisstion.MSToolkit;

import java.util.Collection;

public interface MSContainer extends MSBoundedComponent{
	
	/*
	 * Returns and unmodifiable version of the components
	 * for other classes to use.
	 */
	Collection<MSComponent> getComponentView();
}
