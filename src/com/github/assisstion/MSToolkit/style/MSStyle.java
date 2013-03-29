package com.github.assisstion.MSToolkit.style;

import java.awt.Color;

public interface MSStyle{
	int getPaddingLeft();
	int getPaddingRight();
	int getPaddingTop();
	int getPaddingBottom();
	Color getForeground();
	Color getFrontBackground();
	Color getBackground();
	void setPaddingLeft(int i);
	void setPaddingRight(int i);	
	void setPaddingTop(int i);	
	void setPaddingBottom(int i);
	void setForeground(Color c);
	void setFrontBackground(Color c);
	void setBackground(Color c);
}
