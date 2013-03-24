package com.github.assisstion.spaceInvaders.menu;

import com.github.assisstion.spaceInvaders.gameObject.Sprite;

public class Cutscene{
	
	public static final int DEFAULT_DELAY = 45;
	
	private Sprite[][] sprites;
	
	private CutsceneTextResource[] textRes;
	
	public Cutscene(Sprite[][] sprites, CutsceneTextResource[] textRes){
		this.sprites = sprites;
		this.textRes = textRes;
	}

	public static int getDefaultDelay() {
		return DEFAULT_DELAY;
	}

	public Sprite[][] getSprites() {
		return sprites;
	}

	public CutsceneTextResource[] getTextRes() {
		return textRes;
	}

}
