package com.github.assisstion.MSToolkit.style;

import com.github.assisstion.MSToolkit.MSColor;
import com.github.assisstion.MSToolkit.MSFont;

public interface MSMutableStyle extends MSStyle{
	void setPaddingLeft(int i);
	void setPaddingRight(int i);	
	void setPaddingTop(int i);	
	void setPaddingBottom(int i);
	void setForeground(MSColor c);
	void setFrontBackground(MSColor c);
	void setBackground(MSColor c);
	void setFont(MSFont f);
}
