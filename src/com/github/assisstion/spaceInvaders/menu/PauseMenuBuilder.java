package com.github.assisstion.spaceInvaders.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.github.assisstion.spaceInvaders.MainCanvas;

public class PauseMenuBuilder implements MenuBuilder{

	private PauseMenuBuilder instance;
	private Menu parent;
	
	private JButton pauseButton;

	public PauseMenuBuilder(){
		instance = this;
	}
	
	@Override
	public void build(Menu menu){
		parent = menu;
		
		pauseButton = new JButton("hi");
		pauseButton.setBounds(960/2,100,100,100);
		
		pauseButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				MainCanvas.menu.add(MainCanvas.engine);
				MainCanvas.engine.state="main";
				MainCanvas.frame.pack();
			}
		});
		
		parent.add(pauseButton);
	}

	@Override
	public void unBuild(Menu menu){
		parent.remove(pauseButton);
	}

}
