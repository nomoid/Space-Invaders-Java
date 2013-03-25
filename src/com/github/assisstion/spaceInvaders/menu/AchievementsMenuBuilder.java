package com.github.assisstion.spaceInvaders.menu;

public class AchievementsMenuBuilder implements MenuBuilder {

	private Menu parent;
	private AchievementsMenuBuilder instance;
	
	public AchievementsMenuBuilder(){
		instance = this;
	}
	
	@Override
	public void build(Menu menu) {
		parent = menu;

		
		
	}

	@Override
	public void unBuild(Menu menu) {
		// TODO Auto-generated method stub

	}

}
