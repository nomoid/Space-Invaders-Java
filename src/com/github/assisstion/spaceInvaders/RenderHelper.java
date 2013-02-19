package com.github.assisstion.spaceInvaders;

import java.awt.Graphics2D;

public class RenderHelper{
	public static void renderSprite(Graphics2D g, Sprite s){
		g.drawImage(s.getImage(), s.x, s.y, s.getImage().getWidth(MainCanvas.frame), s.getImage().getHeight(MainCanvas.frame), MainCanvas.frame);
	}
}
