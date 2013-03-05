package com.github.assisstion.spaceInvaders;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;

public class MainMenuBuilder implements MenuBuilder{

	private final static String BUTTON1 = "resources/startButton.png";
	private final static String MAINLOGO = "resources/MainLogo.png";
	private MainMenuBuilder instance;
	private Menu parent;
	private JButton button1;
	private JLabel logolabel;
	
	public MainMenuBuilder(){
		instance = this;
	}
	
	@Override
	public void build(Menu menu){
		parent = menu;
		parent.setLayout(null);
		BufferedImage buttonIcon = null;
		BufferedImage mainLogoIcon = null;
		try {
			buttonIcon = ResourceHolder.getImageResource(BUTTON1);
			mainLogoIcon = ResourceHolder.getImageResource(MAINLOGO);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error loading image!");
		}
		
		
		button1 = new JButton(new ImageIcon(buttonIcon));
		logolabel = new JLabel(new ImageIcon(mainLogoIcon));
		
		button1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				parent.startGame();
			}
		});
		
		button1.setBounds(960/2-100, 400, 200, 100);
		logolabel.setBounds(960/2-450,30,900,100);
		button1.setBorder(BorderFactory.createEmptyBorder());
		button1.setContentAreaFilled(false);
		
		parent.add(button1);
		parent.add(logolabel);
	}
	
	@Override
	public void unBuild(Menu menu){
		parent = menu;

			parent.remove(button1);
			parent.remove(logolabel);
	}

}
