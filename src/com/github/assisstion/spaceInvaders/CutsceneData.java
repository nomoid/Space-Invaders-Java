package com.github.assisstion.spaceInvaders;

public final class CutsceneData{
	public static final class Cutscene1{
		
		private static final String PAGE_1 = "Somewhere deep in Tofite-held space...";
		private static final String PAGE_2 = "Something happened...";
		
		private static final String[] PAGES = {PAGE_1, PAGE_2};
		
		private static final Sprite[][] SPRITES = {{new Player(0, "hi")},{}};

		public static final Cutscene SCENE = new Cutscene(PAGES, SPRITES);
	}
}