package com.github.assisstion.MSToolkit;


public interface MSGraphicalContext{
	void drawString(String str, int x, int y);
	void drawImage(MSImage img, int x, int y);
	MSColor getColor();
	MSFont getFont();
	void setColor(MSColor color);
	void setFont(MSFont font);
	void fillRect(int x, int y, int width, int height);
	void drawRect(int x, int y, int width, int height);
}
