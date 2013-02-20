package com.github.assisstion.spaceInvaders;

import java.awt.Graphics2D;

public class RenderHelper{
	public static void renderSprite(Graphics2D g, Sprite s){
		//Draws the Sprite s to the Graphics2D g
		g.drawImage(s.getImage(), s.x, s.y, s.getImage().getWidth(MainCanvas.frame), s.getImage().getHeight(MainCanvas.frame), MainCanvas.frame);
	}
}
