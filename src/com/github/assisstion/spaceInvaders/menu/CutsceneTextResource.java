package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;

public class CutsceneTextResource {
	private char[] text;
	private Font font;
	private Color color;
	private double delay;
	private double delayNormal = delay;
	
	public void speedUp(){
		delay = delay/5;
	}
	public void speedNormal(){
		delay = delayNormal;
	}
	public CutsceneTextResource(char[] text, Font font, Color color, double delay) {
		this.text = text;
		this.font = font;
		this.color = color;
		this.delay = delay;		
	}

	public char[] getText() {
		return text;
	}

	public Font getFont() {
		return font;
	}

	public Color getColor() {
		return color;
	}

	public double getDelay() {
		return delay;
	}
	
	
}
