package com.github.assisstion.spaceInvaders.menu;

import java.awt.Font;

import com.github.assisstion.spaceInvaders.gameObject.Sprite;

public class Cutscene{
	
	public static final int DEFAULT_DELAY = 50;
	
	public char[][] pages;
	public Sprite[][] sprites;
	public int[] delays;
	public Font[] fonts;
	
	public Cutscene(char[][] pages, Sprite[][] sprites, Font[] fontlist){
		this(pages, sprites, null,fontlist);
	}
	
	public Cutscene(char[][] pages, Sprite[][] sprites, int[] delays, Font[] fontlist){
		this.pages = pages;
		this.sprites = sprites;
		this.delays = delays;
		this.fonts = fontlist;
	}
	
	protected Cutscene(){
		
	}
}
