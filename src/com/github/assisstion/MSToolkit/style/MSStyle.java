package com.github.assisstion.MSToolkit.style;

import com.github.assisstion.MSToolkit.MSColor;
import com.github.assisstion.MSToolkit.MSFont;

public interface MSStyle{
	int getPaddingLeft();
	int getPaddingRight();
	int getPaddingTop();
	int getPaddingBottom();
	MSColor getForeground();
	MSColor getFrontBackground();
	MSColor getBackground();
	MSFont getFont();
}
