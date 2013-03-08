package com.github.assisstion.spaceInvaders.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.github.assisstion.spaceInvaders.MainCanvas;


public class LevelMenuBuilder implements MenuBuilder {
	private LevelMenuBuilder instance;
	private Menu parent;
	private JButton nextLevelButton;

	public LevelMenuBuilder(){
		instance = this;
	}
	@Override
	public void build(Menu menu) {
		parent = menu;
		nextLevelButton = new JButton("Next Level");
		nextLevelButton.setBounds(960/2,100,100,100);
		
		nextLevelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				MainCanvas.menu.add(MainCanvas.engine);
				MainCanvas.engine.state="main";
				MainCanvas.frame.pack();
			}
		});
		
		parent.add(nextLevelButton);

	}

	@Override
	public void unBuild(Menu menu) {
		parent.remove(nextLevelButton);
	
	}

}
