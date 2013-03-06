package com.github.assisstion.spaceInvaders.menu;

import com.github.assisstion.spaceInvaders.gameObject.Sprite;

public class Cutscene{
	
	public static final int DEFAULT_DELAY = 2000;
	
	public String[] pages;
	public Sprite[][] sprites;
	public int[] delays;
	
	public Cutscene(String[] pages, Sprite[][] sprites){
		this(pages, sprites, null);
	}
	
	public Cutscene(String[] pages, Sprite[][] sprites, int[] delays){
		this.pages = pages;
		this.sprites = sprites;
		this.delays = delays;
	}
	
	protected Cutscene(){
		
	}
}
