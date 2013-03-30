package com.github.assisstion.MSToolkit.style;

import java.awt.Color;
import java.awt.Font;

public interface MSMutableStyle extends MSStyle{
	void setPaddingLeft(int i);
	void setPaddingRight(int i);	
	void setPaddingTop(int i);	
	void setPaddingBottom(int i);
	void setForeground(Color c);
	void setFrontBackground(Color c);
	void setBackground(Color c);
	void setFont(Font f);
}
