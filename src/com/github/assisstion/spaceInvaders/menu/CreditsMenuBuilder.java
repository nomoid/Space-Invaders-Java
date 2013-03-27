package com.github.assisstion.spaceInvaders.menu;


@ReturnableMenu
public class CreditsMenuBuilder implements MenuBuilder{
//will be made later
	private Menu parent;
	private CreditsMenuBuilder instance;
	
	@Override
	public void build(Menu menu) {
		parent = menu;
		instance = this;
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unBuild(Menu menu) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitMenu() {
		parent.closeMenu(instance);
		parent.addMenuBuilder(new MainMenuBuilder());
	}

}
