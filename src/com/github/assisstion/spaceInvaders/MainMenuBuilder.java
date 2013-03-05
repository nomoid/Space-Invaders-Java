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

	private final static String STARTBUTTON = "resources/startButton.png";
	private final static String HELPBUTTON = "resources/helpButton.png";
	private final static String STORYBUTTON = "resources/storyButton.png";
	private final static String MAINLOGO = "resources/MainLogo.png";
	private MainMenuBuilder instance;
	private Menu parent;
	private JButton startButton;
	private JButton storyButton;
	private JButton helpButton;
	private JLabel logolabel;
	
	public MainMenuBuilder(){
		instance = this;
	}
	
	@Override
	public void build(Menu menu){
		parent = menu;
		parent.setLayout(null);
		BufferedImage startbuttonIcon = null;
		BufferedImage helpbuttonIcon = null;
		BufferedImage storybuttonIcon=null;
		BufferedImage mainLogoIcon = null;
		try {
			startbuttonIcon = ResourceHolder.getImageResource(STARTBUTTON);
			helpbuttonIcon = ResourceHolder.getImageResource(HELPBUTTON);
			storybuttonIcon = ResourceHolder.getImageResource(STORYBUTTON);
			mainLogoIcon = ResourceHolder.getImageResource(MAINLOGO);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Error loading image!");
		}
		
		
		startButton = new JButton(new ImageIcon(startbuttonIcon));
		helpButton = new JButton(new ImageIcon(helpbuttonIcon));
		storyButton = new JButton(new ImageIcon(storybuttonIcon));
		logolabel = new JLabel(new ImageIcon(mainLogoIcon));
		
		startButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
				parent.closeMenu(instance);
				parent.startGame();
			}
		});
		
		helpButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
			}
		});
		
		storyButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("Button pressed");
			}
		});
		
		
		
		startButton.setBounds(960/2-81, 300, 162, 79);
		helpButton.setBounds(960/2-81, 380, 162, 79);
		storyButton.setBounds(960/2-81, 460, 162, 79);
		
		
		logolabel.setBounds(960/2-450,30,900,100);
		
		startButton.setBorder(BorderFactory.createEmptyBorder());
		helpButton.setBorder(BorderFactory.createEmptyBorder());
		storyButton.setBorder(BorderFactory.createEmptyBorder());
		
		helpButton.setContentAreaFilled(false);
		storyButton.setContentAreaFilled(false);
		startButton.setContentAreaFilled(false);
		
		parent.add(storyButton);
		parent.add(helpButton);
		parent.add(startButton);
		parent.add(logolabel);
	}
	
	@Override
	public void unBuild(Menu menu){
		parent = menu;
			parent.remove(storyButton);
			parent.remove(helpButton);
			parent.remove(startButton);
			parent.remove(logolabel);
	}

}
