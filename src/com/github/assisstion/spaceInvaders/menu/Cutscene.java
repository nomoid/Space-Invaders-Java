package com.github.assisstion.spaceInvaders.menu;

import java.awt.Color;
import java.awt.Font;

import com.github.assisstion.spaceInvaders.gameObject.Sprite;

public class Cutscene{
	
	public static final int DEFAULT_DELAY = 45;
	
	public char[][] pages;
	public Sprite[][] sprites;
	public double[] delays;
	public Font[] fonts;
	public Color[] colors;
	
	public Cutscene(char[][] pages, Sprite[][] sprites, Font[] fontlist,Color[] colorlist){
		this(pages, sprites, null,fontlist,colorlist);
	}
	
	public Cutscene(char[][] pages, Sprite[][] sprites, double[] delays, Font[] fontlist, Color[] colorlist){
		this.pages = pages;
		this.sprites = sprites;
		this.delays = delays;
		this.fonts = fontlist;
		this.colors = colorlist;
	}
	
	protected Cutscene(){
		
	}
}
