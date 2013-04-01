package com.github.assisstion.spaceInvaders.menu;

import com.github.assisstion.spaceInvaders.AchievementMethods;
import com.github.assisstion.spaceInvaders.gameObject.Achievement;


@ReturnableMenu
public class CreditsMenuBuilder implements MenuBuilder{
//will be made later
	private Menu parent;
	private CreditsMenuBuilder instance;
	
	@Override
	public void build(Menu menu) {
		parent = menu;
		instance = this;
		AchievementMethods.redeemAchievement(new Achievement("Givin' Credit")); 

		
	}

	@Override
	public void unBuild(Menu menu) {

		
	}

	@Override
	public void exitMenu() {
		parent.closeMenu(instance);
		parent.addMenuBuilder(new MainMenuBuilder());
	}

}
