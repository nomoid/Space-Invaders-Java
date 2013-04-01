package com.github.assisstion.MSToolkit;

public interface MSColor{
	int getRed();
	int getGreen();
	int getBlue();
	int getAlpha();
	boolean hasAlpha();
	MSColor darker();
	MSColor brighter();
}
