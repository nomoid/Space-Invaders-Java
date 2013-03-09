package com.github.assisstion.spaceInvaders.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import com.github.assisstion.spaceInvaders.MainCanvas;
import com.github.assisstion.spaceInvaders.ResourceHolder;


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
		nextLevelButton = new JButton(new ImageIcon(getImage()));
		nextLevelButton.setBounds(0,540,960,200);
		
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

	private BufferedImage getImage(){
		BufferedImage returnIcon = null;
		try {
			returnIcon = ResourceHolder.getImageResource("resources/nextlevelButton.png");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error loading image!");
		}
		return returnIcon;
	}
}
