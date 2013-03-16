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
				parent.closeMenu(parent.currentMenu);
				System.out.println("affhjasdlkfha");
			}
			
		}
		System.out.println("nongescjasdlkfha");
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

}
