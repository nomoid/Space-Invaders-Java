package com.github.assisstion.MSToolkit.style;

import java.awt.Color;

public final class MSClassicStyle{
	private MSClassicStyle(){
		
	}
	
	public static final MSStyle WINDOW_STYLE = MSStyleManager.getStyle(
			0, 0, 0, 0, Color.BLACK, Color.GRAY, Color.WHITE);
	public static final MSStyle BUTTON_STYLE = MSStyleManager.getStyle(
			5, 5, 5, 5, Color.BLACK, Color.GRAY, Color.WHITE);
}
