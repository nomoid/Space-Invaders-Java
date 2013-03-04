package com.github.assisstion.spaceInvaders;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;


import com.github.assisstion.spaceInvaders.MainCanvas;

public class Menu extends JPanel{
	
	private static final long serialVersionUID = 8162618142692095178L;
	private JButton button1;
	
	public Menu() {
		setLayout(null);
		setBackground(Color.BLACK);
		setPreferredSize(new Dimension(960, 740));
		
		buildMenu();
		
		System.out.println("Menu built");
	}
	
	public void buildMenu(){
		setLayout(null);
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
				closeMenu();
				startGame();
			}
		});
		button1.setBounds(960/2-24, 400, 48, 48);
		button1.setBorder(BorderFactory.createEmptyBorder());
		button1.setContentAreaFilled(false);
		add(button1);
	}
	
	public void closeMenu(){
		remove(button1);
	}
	
	/*
	 * Starts the game engine
	 */
	public void startGame() {
		
		setLayout(new FlowLayout());
		MainCanvas.engine = new Engine();
		add(MainCanvas.engine);
		MainCanvas.frame.pack();
		MainCanvas.isOn = true;
		MainCanvas.rand = new Random();
		MainCanvas.engine.state = "ready";
		System.out.println("Engine starting");
		new Thread(new Clock()).start();
	}
}
