package com.github.assisstion.spaceInvaders;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class MainMenuBuilder implements MenuBuilder{

	private MainMenuBuilder instance;
	private Menu parent;
	private JButton button1;
	
	public MainMenuBuilder(){
		instance = this;
	}
	
	@Override
	public void build(Menu menu){
		parent = menu;
		parent.setLayout(null);
		BufferedImage buttonIcon = null;
		try {
			buttonIcon = ResourceHolder.getImageResource("resources/Spaceship.png");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error loading image!");
		}
		
		button1 = new JButton(new ImageIcon(buttonIcon));
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				parent.startGame();
			}
		});
		button1.setBounds(960/2-24, 400, 48, 48);
		button1.setBorder(BorderFactory.createEmptyBorder());
		button1.setContentAreaFilled(false);
		parent.add(button1);
	}
	
	@Override
	public void unBuild(Menu menu){
		parent = menu;
		parent.remove(button1);
	}

}
