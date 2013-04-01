package com.github.assisstion.spaceInvaders.menu;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class MenuKeyListener implements KeyListener {

	Menu parent;
	
	public MenuKeyListener(Menu menu){
		parent = menu;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE){
			if (parent.currentMenu.getClass().getAnnotation(ReturnableMenu.class) != null){
				parent.currentMenu.exitMenu();
				
			}	
		}
		/*if(e.getKeyCode() == KeyEvent.VK_M){
			if(!ResourceManager.getMuted()){
				System.out.println("Game Muted");
				ResourceManager.setMuted(true);
			}
			else{
				System.out.println("Game Unmuted");
				ResourceManager.setMuted(false);
			}
		}
		*/
		// no point if options menu is fully implemented
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Empty

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Empty

	}

}
