package com.github.assisstion.spaceInvaders;

import java.awt.image.BufferedImage;

public interface MenuBuilder{
	void build(Menu menu);
	void unBuild(Menu menu);
	
	BufferedImage getReturnImage();
}
