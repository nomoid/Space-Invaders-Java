package com.github.assisstion.spaceInvaders.menu;

import java.awt.Font;

import com.github.assisstion.spaceInvaders.gameObject.Player;
import com.github.assisstion.spaceInvaders.gameObject.Sprite;

public final class CutsceneData{
	public static final class Cutscene1{
		
		private static final String PAGE_1 = "Somewhere deep in Tofite-held space...";
		private static final String PAGE_2 = "Something happened...";
		private static final Font[] FONTLIST =  {new Font("Serif", Font.PLAIN, 40),new Font("Serif", Font.PLAIN, 14)};
		
		private static final char[][] PAGES = {PAGE_1.toCharArray(), PAGE_2.toCharArray()};
		
		private static final Sprite[][] SPRITES = {{new Player(0, "hi")},{}};

		public static final Cutscene SCENE = new Cutscene(PAGES, SPRITES,FONTLIST);
	}
}